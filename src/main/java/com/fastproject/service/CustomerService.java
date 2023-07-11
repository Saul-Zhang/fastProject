package com.fastproject.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.AuditMapper;
import com.fastproject.mapper.AuditUserMapper;
import com.fastproject.mapper.CustomerMapper;
import com.fastproject.mapper.TemplateMapper;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditContent;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.AuditType;
import com.fastproject.model.Customer;
import com.fastproject.model.FieldType;
import com.fastproject.model.RelationAuditUser;
import com.fastproject.model.Template;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.ColsResponse;
import com.fastproject.model.response.CustomerEditResponse;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.SnowflakeIdWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public List<ColsResponse> cols() {
    return templateService.getTemplateList().stream().map(template -> {
      ColsResponse response = ColsResponse.fromTemplate(template);
      if (StrUtil.isNotBlank(template.getDictTypeCode())) {
        // 处理上级销售经理
        if ("sales_manager".equals(template.getDictTypeCode())){
          response.setDictDataMap(dictService.getDict(SaTokenUtil.getUser().getEmployeeId()));
        }else {
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
    for (Entry<String, String> entry : map.entrySet()) {
      if ("description".equals(entry.getKey())) {
        description = entry.getValue();
        continue;
      }
      AuditContent auditContent = new AuditContent();
      auditContent.setFiledId(Long.valueOf(entry.getKey()));
      auditContent.setAfter(entry.getValue());
      auditContent.setBefore(null);
      contentList.add(auditContent);
    }
    Audit audit = Audit.builder()
        .id(SnowflakeIdWorker.getId())
        .entityId(SnowflakeIdWorker.getId())
        .content(contentList)
        .status(AuditStatus.WAITING)
        .description(description)
        .type(AuditType.ADD_CUSTOMER).build();
    auditMapper.insert(audit);

    RelationAuditUser auditUser = RelationAuditUser.builder()
        .auditId(audit.getId())
        .auditBy(1L)
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
            if ("sales_manager".equals(template.getDictTypeCode())){
              customerEditResponse.setDictDataMap(dictService.getDict(SaTokenUtil.getUser().getEmployeeId()));
            }else {
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
    for (Entry<String, String> entry : map.entrySet()) {
      if ("description".equals(entry.getKey())) {
        description = entry.getValue();
        continue;
      }
      Long fieldId = Long.valueOf(entry.getKey());
      AuditContent auditContent = new AuditContent();
      auditContent.setFiledId(fieldId);
      auditContent.setAfter(entry.getValue());
      auditContent.setBefore(customMap.get(fieldId));
      contentList.add(auditContent);
    }
    Audit audit = Audit.builder()
        .id(SnowflakeIdWorker.getId())
        .entityId(SnowflakeIdWorker.getId())
        .content(contentList)
        .status(AuditStatus.WAITING)
        .description(description)
        .type(AuditType.UPDATE_CUSTOMER).build();
    auditMapper.insert(audit);

    RelationAuditUser auditUser = RelationAuditUser.builder()
        .auditId(audit.getId())
        .auditBy(1L)
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
          .entityId(SnowflakeIdWorker.getId())
          .content(contentList)
          .status(AuditStatus.WAITING)
          .description(description)
          .type(AuditType.DELETE_CUSTOMER).build();
      auditMapper.insert(audit);

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
}
