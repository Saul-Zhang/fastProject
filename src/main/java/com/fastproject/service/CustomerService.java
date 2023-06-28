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
import com.fastproject.model.AuditUser;
import com.fastproject.model.Customer;
import com.fastproject.model.FieldType;
import com.fastproject.model.Template;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.ColsResponse;
import com.fastproject.model.response.CustomerResponse;
import com.fastproject.util.SnowflakeIdWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        response.setDictDataMap(dictService.getDict(template.getDictTypeCode()));
      }
      return response;
    }).collect(Collectors.toList());
  }

  public List<Map<Object, Object>> list(Map<String, String> map) {
    Map<String, Template> templateMap = templateService.getTemplateList().stream()
        .collect(Collectors.toMap(template -> String.valueOf(template.getId()), t->t));
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
          Map<Object, Object> customerData = new HashMap<>();
          customerData.put("customerId", customerGroup.get(0).getCustomerId());
          customerGroup.forEach(customer -> {
            Template template = templateMap.get(String.valueOf(customer.getFieldId()));
            if (template != null && FieldType.SELECT.equals(template.getType())) {
              String data = dictService.getData(template.getDictTypeCode(), customer.getValue());
              customerData.put(customer.getFieldId(),data);
            }else {
              customerData.put(customer.getFieldId(), customer.getValue());
            }
          });
          return customerData;
        })
        .collect(Collectors.toList());
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult applyAuditAddCustomer(Map<Long, String> map) {
    List<AuditContent> contentList = new ArrayList<>();
    map.forEach((key, value) -> {
      AuditContent auditContent = new AuditContent();
      auditContent.setFiledId(key);
      auditContent.setAfter(value);
      auditContent.setBefore(null);
      contentList.add(auditContent);
    });
    Audit audit = Audit.builder()
        .id(SnowflakeIdWorker.getId())
        .entityId(SnowflakeIdWorker.getId())
        .content(contentList)
        .status(AuditStatus.WAITING)
        .type(AuditType.ADD_CUSTOMER).build();
    auditMapper.insert(audit);

    AuditUser auditUser = AuditUser.builder()
        .auditId(audit.getId())
        .approvedBy(1L)
        .status(AuditStatus.WAITING).build();
    auditUserMapper.insert(auditUser);
    return AjaxResult.success();
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult add(Map<Long, String> map) {
    Long customerId = SnowflakeIdWorker.getId();
    map.entrySet().stream()
        .filter(entry -> StrUtil.isNotBlank(entry.getValue()))
        .forEach(entry -> {
          Customer customer = Customer.builder().customerId(customerId)
              .fieldId(entry.getKey()).value(entry.getValue()).build();
          customerMapper.insert(customer);
        });
    return AjaxResult.success();
  }

  public List<CustomerResponse> selectByCustomerId(Long customerId) {
    Map<Long, String> templateMap = templateService.getTemplateList().stream()
        .collect(Collectors.toMap(Template::getId, Template::getFieldName));

    return customerMapper.selectList(
            new LambdaQueryWrapperX<Customer>().eq(Customer::getCustomerId, customerId)).stream()
        .map(customer -> CustomerResponse.builder()
            .fieldId(String.valueOf(customer.getFieldId()))
            .fieldName(templateMap.get(customer.getFieldId()))
            .value(customer.getValue()).build())
        .filter(customer -> customer.getFieldName() != null)
        .collect(Collectors.toList());

  }

  public AjaxResult update(Map<Long, String> map) {
    return null;
  }
}
