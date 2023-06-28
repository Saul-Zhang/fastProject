package com.fastproject.service;

import cn.hutool.core.collection.CollectionUtil;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.AuditMapper;
import com.fastproject.mapper.AuditUserMapper;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditUser;
import com.fastproject.model.FieldType;
import com.fastproject.model.Template;
import com.fastproject.model.request.query.AuditQuery;
import com.fastproject.model.response.AuditDetailResponse;
import com.fastproject.model.response.AuditResponse;
import com.fastproject.satoken.SaTokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author fastProject
 * @date 2023/6/18 22:55
 */
@Service
@RequiredArgsConstructor
public class AuditService {

  private final AuditMapper auditMapper;
  private final AuditUserMapper auditUserMapper;
  private final TemplateService templateService;
  private final DictService dictService;


  public PageInfo<AuditResponse> list(AuditQuery query) {
    List<Long> auditIds = auditUserMapper.selectList(
            new LambdaQueryWrapperX<AuditUser>().eq(AuditUser::getApprovedBy, SaTokenUtil.getUserId()))
        .stream().map(AuditUser::getAuditId).collect(
            Collectors.toList());
    if (CollectionUtil.isEmpty(auditIds)) {
      return new PageInfo<>();
    }
    PageHelper.startPage(query.getPage(), query.getLimit());
    List<AuditResponse> audits = auditMapper.selectList(
        new LambdaQueryWrapperX<Audit>().inIfPresent(Audit::getId, auditIds))
        .stream().map(AuditResponse::fromAudit).collect(Collectors.toList());
    return new PageInfo<>(audits);
  }

  public List<AuditDetailResponse> detail(Long auditId) {
    Map<Long, Template> templateMap = templateService.getTemplateMap();
    return auditMapper.selectById(auditId).getContent().stream()
        .map(entity -> {
          AuditDetailResponse detail = new AuditDetailResponse();
          Template template = templateMap.get(entity.getFiledId());
          detail.setFiledName(template.getFieldName());
          if (FieldType.SELECT.equals(template.getType())){
            String before = dictService.getData(template.getDictTypeCode(), entity.getBefore());
            String after = dictService.getData(template.getDictTypeCode(), entity.getAfter());
            detail.setBefore(before);
            detail.setAfter(after);
          }else {
            detail.setBefore(entity.getBefore());
            detail.setAfter(entity.getAfter());
          }
          return detail;
        }).collect(Collectors.toList());
  }

//  public AjaxResult add(Template template) {
//    templateMapper.insert(template);
//    return AjaxResult.success();
//  }
}
