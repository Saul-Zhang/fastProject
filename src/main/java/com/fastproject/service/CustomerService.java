package com.fastproject.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.mybatis.QueryWrapperX;
import com.fastproject.mapper.AuditMapper;
import com.fastproject.mapper.AuditUserMapper;
import com.fastproject.mapper.CustomerMapper;
import com.fastproject.mapper.TemplateMapper;
import com.fastproject.mapper.UserMapper;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditContent;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.AuditType;
import com.fastproject.model.Customer;
import com.fastproject.model.FieldType;
import com.fastproject.model.RelationAuditUser;
import com.fastproject.model.Template;
import com.fastproject.model.User;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.ColsResponse;
import com.fastproject.model.response.CustomerEditResponse;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.SnowflakeIdWorker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fastProject
 * @date 2023/6/18 22:55
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

  private final TemplateMapper templateMapper;
  private final CustomerMapper customerMapper;
  private final TemplateService templateService;
  private final DictService dictService;
  private final AuditMapper auditMapper;
  private final AuditUserMapper auditUserMapper;
  private final UserService userService;
  private final RoleService roleService;
  private final EnvConfigService envConfigService;
  private final UserMapper userMapper;

  public List<ColsResponse> cols() {
    return templateService.getTemplateList().stream().map(template -> {
      ColsResponse response = ColsResponse.fromTemplate(template);
      if (StrUtil.isNotBlank(template.getDictTypeCode())) {
        // 处理上级销售经理
        if ("sales_manager".equals(template.getDictTypeCode())) {
          response.setDictDataMap(dictService.getSalesManager());
        } else {
          response.setDictDataMap(dictService.getDict(template.getDictTypeCode()));
        }
      }
      return response;
    }).collect(Collectors.toList());
  }

  public List<Map<String, String>> list(Map<String, String> map) {

    Map<String, Template> templateMap = templateService.getTemplateList().stream()
        .collect(Collectors.toMap(template -> String.valueOf(template.getId()), t -> t));
    List<Long> ids = null;

    if (!roleService.isAdmin(SaTokenUtil.getUserId())) {
      ids = customerMapper.selectList(new LambdaQueryWrapperX<Customer>()
              .eq(Customer::getFieldId, "1672265362920390658")
              .eq(Customer::getValue, SaTokenUtil.getUserId())
              .or()
              .eq(Customer::getFieldId, "1672265408768327681")
              .eq(Customer::getValue, SaTokenUtil.getUserId())).stream()
          .map(Customer::getCustomerId).collect(Collectors.toList());

      if (CollectionUtils.isEmpty(ids)){
        return null;
      }
    }


    if (CollectionUtil.isNotEmpty(map)) {
      for (Entry<String, String> entry : map.entrySet()) {
        if (StrUtil.isBlank(entry.getValue()) || !templateMap.containsKey(entry.getKey())) {
          continue;
        }
        LambdaQueryWrapperX<Customer> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(Customer::getFieldId, entry.getKey())
            .eqIfPresent(Customer::getValue, entry.getValue());
        List<Long> customerIds = customerMapper.selectList(queryWrapperX).stream()
            .map(Customer::getCustomerId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(customerIds)) {
          return Collections.emptyList();
        }

        if (ids == null) {
          ids = customerIds;
        } else {
          // 求交集
          ids.retainAll(customerIds);
        }
      }
    }

    List<Customer> customers = customerMapper.selectList(
        new LambdaQueryWrapperX<Customer>().inIfPresent(Customer::getCustomerId, ids));
    // 将数据转化为第二份数据的格式
    return customers.stream()
        .collect(Collectors.groupingBy(Customer::getCustomerId))
        .values().stream()
        .map(customerGroup -> {
          Map<String, String> customerData = new HashMap<>();
          customerData.put("customerId", String.valueOf(customerGroup.get(0).getCustomerId()));
          customerGroup.forEach(customer -> {
            Template template = templateMap.get(String.valueOf(customer.getFieldId()));
            if (template != null && FieldType.SELECT.equals(template.getType())) {
              String data = dictService.getData(template.getDictTypeCode(), customer.getValue());
              customerData.put(String.valueOf(customer.getFieldId()), data);
            } else {
              customerData.put(String.valueOf(customer.getFieldId()), customer.getValue());
            }
          });
          return customerData;
        })
        .collect(Collectors.toList());
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult applyAuditAddCustomer(Map<String, String> map) {

    List<AuditContent> contentList = new ArrayList<>();
    String description = null;
    Long auditor = -1L;
    for (Entry<String, String> entry : map.entrySet()) {
      if ("description".equals(entry.getKey())) {
        description = entry.getValue();
        continue;
      }
      // 获取所属销售
      if ("1672265362920390658".equals(entry.getKey())) {
        auditor = Long.valueOf(entry.getValue());
      }
      AuditContent auditContent = new AuditContent();
      auditContent.setFiledId(Long.valueOf(entry.getKey()));
      auditContent.setAfter(entry.getValue());
      auditContent.setBefore(null);
      contentList.add(auditContent);
    }

    if (roleService.isAdmin(SaTokenUtil.getUserId())) {
      return add(contentList,SnowflakeIdWorker.getId());
    }

    Audit audit = Audit.builder()
        .id(SnowflakeIdWorker.getId())
        .entityId(SnowflakeIdWorker.getId())
        .content(contentList)
        .status(AuditStatus.WAITING)
        .description(description)
        .type(AuditType.ADD_CUSTOMER).build();
    auditMapper.insert(audit);

    if (!envConfigService.isMultiLevelAudit()) {
      auditor = userService.getSalesManager();
    }
    RelationAuditUser auditUser = RelationAuditUser.builder()
        .auditId(audit.getId())
        .auditBy(auditor)
        .status(AuditStatus.WAITING).build();
    auditUserMapper.insert(auditUser);
    return AjaxResult.success();
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult add(Map<Long, String> map, Long customerId) {
//    Long customerId = SnowflakeIdWorker.getId();
    map.entrySet().stream()
        .filter(entry -> StrUtil.isNotBlank(entry.getValue()))
        .forEach(entry -> {
          Customer customer = Customer.builder().customerId(customerId)
              .fieldId(entry.getKey()).value(entry.getValue()).build();
          customerMapper.insert(customer);
        });
    return AjaxResult.success();
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult add(List<AuditContent> contentList, Long customerId) {
    contentList
//        .filter(content -> StrUtil.isNotBlank(content.getAfter()))
        .forEach(content -> {
          Customer customer = Customer.builder().customerId(customerId)
              .fieldId(content.getFiledId()).value(content.getAfter()).build();
          customerMapper.insert(customer);
        });
    return AjaxResult.success();
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult update(List<AuditContent> contentList, Long customerId) {
    contentList
        .forEach(content -> {
          if (!StringUtils.equals(content.getAfter(), content.getBefore())) {
            Customer customer = Customer.builder().customerId(customerId)
                .fieldId(content.getFiledId()).value(content.getAfter()).build();
            customerMapper.update(customer, new LambdaQueryWrapperX<Customer>().eq(Customer::getCustomerId, customerId)
                .eq(Customer::getFieldId,content.getFiledId()).eq(Customer::getValue, content.getBefore()));
          }

        });
    return AjaxResult.success();
  }

  public List<CustomerEditResponse> selectByCustomerId(Long customerId) {
    Map<Long, Template> templateMap = templateService.getTemplateList().stream()
        .collect(Collectors.toMap(Template::getId, Function.identity()));

    return customerMapper.selectList(
            new LambdaQueryWrapperX<Customer>().eq(Customer::getCustomerId, customerId)).stream()
        .map(customer ->
        {
          Template template = templateMap.get(customer.getFieldId());
          CustomerEditResponse customerEditResponse = CustomerEditResponse.builder()
              .fieldId(String.valueOf(customer.getFieldId()))
              .fieldName(template.getFieldName())
              .value(customer.getValue())
              .required(template.getRequired()).build();
          if (FieldType.SELECT.equals(template.getType())) {
            // 处理上级销售经理
            if ("sales_manager".equals(template.getDictTypeCode())) {
              customerEditResponse.setDictDataMap(dictService.getSalesManager());
            } else {
              customerEditResponse.setDictDataMap(dictService.getDict(template.getDictTypeCode()));
            }
          }
          return customerEditResponse;
        })
        .filter(customer -> customer.getFieldName() != null)
        .collect(Collectors.toList());

  }

  @Transactional
  public AjaxResult applyAuditUpdateCustomer(Map<String, String> map, Long customerId) {
    Map<Long, String> customMap = getCustomMap(customerId);
    List<AuditContent> contentList = new ArrayList<>();
    String description = null;
    Long auditor = -1L;
    for (Entry<String, String> entry : map.entrySet()) {
      if ("description".equals(entry.getKey())) {
        description = entry.getValue();
        continue;
      }
      // 获取所属销售
      if ("1672265362920390658".equals(entry.getKey())) {
        auditor = Long.valueOf(entry.getValue());
      }
      Long fieldId = Long.valueOf(entry.getKey());
      AuditContent auditContent = new AuditContent();
      auditContent.setFiledId(fieldId);
      auditContent.setAfter(entry.getValue());
      auditContent.setBefore(customMap.get(fieldId));
      contentList.add(auditContent);
    }
    if (roleService.isAdmin(SaTokenUtil.getUserId())) {
      return update(contentList,customerId);
    }

    Audit audit = Audit.builder()
        .id(SnowflakeIdWorker.getId())
        .entityId(customerId)
        .content(contentList)
        .status(AuditStatus.WAITING)
        .description(description)
        .type(AuditType.UPDATE_CUSTOMER).build();
    auditMapper.insert(audit);

    if (!envConfigService.isMultiLevelAudit()) {
      auditor = userService.getSalesManager();
    }
    RelationAuditUser auditUser = RelationAuditUser.builder()
        .auditId(audit.getId())
        .auditBy(auditor)
        .status(AuditStatus.WAITING).build();
    auditUserMapper.insert(auditUser);
    return AjaxResult.success();
  }

  private Map<Long, String> getCustomMap(Long customerId) {
    return customerMapper.selectList(
            new LambdaQueryWrapperX<Customer>().eq(Customer::getCustomerId, customerId))
        .stream().collect(Collectors.toMap(Customer::getFieldId, Customer::getValue));
  }

  public AjaxResult applyAuditRemoveCustomer(List<Long> ids, String description) {
    if (roleService.isAdmin(SaTokenUtil.getUserId())) {
      ids.forEach(this::remove);
      return AjaxResult.success();
    }
    List<AuditContent> contentList = new ArrayList<>();
    ids.forEach(id -> {
      AtomicLong auditBy = new AtomicLong();
      customerMapper.selectList(new LambdaQueryWrapperX<Customer>().eq(Customer::getCustomerId, id))
          .forEach(customer -> {
            AuditContent auditContent = new AuditContent();
            auditContent.setFiledId(customer.getFieldId());
            auditContent.setBefore(customer.getValue());
            contentList.add(auditContent);
            // 1672265362920390658是“所属销售”这个字段的id
            if (1672265362920390658L == customer.getFieldId()) {
              auditBy.set(Long.parseLong(customer.getValue()));
            }
          });
      Audit audit = Audit.builder()
          .id(SnowflakeIdWorker.getId())
          .entityId(id)
          .content(contentList)
          .status(AuditStatus.WAITING)
          .description(description)
          .type(AuditType.DELETE_CUSTOMER).build();
      auditMapper.insert(audit);

      if (!envConfigService.isMultiLevelAudit()) {
        auditBy.set(userService.getSalesManager());
      }
      RelationAuditUser auditUser = RelationAuditUser.builder()
          .auditId(audit.getId())
          .auditBy(auditBy.get())
          .status(AuditStatus.WAITING).build();
      auditUserMapper.insert(auditUser);
    });
    return AjaxResult.success();
  }

  public void remove(Long entityId) {
    customerMapper.delete(new LambdaQueryWrapperX<Customer>()
        .eq(Customer::getCustomerId, entityId));
  }


  @Transactional
  public AjaxResult applyAuditUpload(MultipartFile multipartFile) throws IOException {
    ExcelReader reader = ExcelUtil.getReader(multipartFile.getInputStream());
    List<Map<String, Object>> all = reader.readAll();
    if (CollectionUtil.isEmpty(all)) {
      return AjaxResult.error("上传的是空文件");
    }
    Map<String, Template> templateNameMap = templateService.getTemplateNameMap();
    List<Map<String, String>> customerList = new ArrayList<>();
    int line = 0;
    for (Map<String, Object> map : all) {
      line++;
      Map<String, String> customerMap = new LinkedHashMap<>();
      for (Entry<String, Object> entry : map.entrySet()) {
        String key = entry.getKey();
        Template template = templateNameMap.get(key);
        String value = String.valueOf(entry.getValue());
        if (template == null) {
          return AjaxResult.error("模板中不存在属性:" + entry.getKey());
        }
        if (template.getRequired() && StringUtils.isBlank(value)) {
          return AjaxResult.error("第"+ line +"行，"+key + "不能为空");
        }
        if (StringUtils.isNotBlank(template.getDictTypeCode())) {
          if (dictService.isUser(template.getDictTypeCode()) || "所属销售".equals(key)) {
            User user = userMapper.selectOne(
                new LambdaQueryWrapperX<User>().eq(User::getRealName, value));
            if (user == null) {
              return AjaxResult.error("第"+ line +"行，用户" + value + "不存在");
            }
            value = String.valueOf(user.getId());
          } else {
            Map<String, String> dict = dictService.getDict(template.getDictTypeCode());
            boolean found = false;
            for (String s : dict.keySet()) {
              if (dict.get(s).equals(value)) {
                value = s;
                found = true;
              }
            }
            if (!found) {
              return AjaxResult.error("第"+ line +"行，"+key + "中不存在字典值：" + value);
            }
          }
        }
        customerMap.put(template.getId().toString(), value);
      }
      customerMap.put("description", "批量导入");
      customerList.add(customerMap);
    }
    customerList.forEach(this::applyAuditAddCustomer);
    return AjaxResult.success();
  }
}
