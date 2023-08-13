package com.fastproject.service;

import static com.fastproject.model.response.AuditResponse.fromAudit;

import cn.hutool.core.collection.CollectionUtil;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.AuditMapper;
import com.fastproject.mapper.AuditUserMapper;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.AuditType;
import com.fastproject.model.FieldType;
import com.fastproject.model.RelationAuditUser;
import com.fastproject.model.Template;
import com.fastproject.model.request.query.AuditQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.ApplyProgressResponse;
import com.fastproject.model.response.AuditDetailResponse;
import com.fastproject.model.response.AuditResponse;
import com.fastproject.satoken.SaTokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
  private final CustomerService customerService;
  private final EnvConfigService envConfigService;
  private final UserService userService;

  public PageInfo<AuditResponse> list(AuditQuery query) {
    List<RelationAuditUser> relationAuditUsers = auditUserMapper.selectList(
        new LambdaQueryWrapperX<RelationAuditUser>().eq(RelationAuditUser::getAuditBy,
            SaTokenUtil.getUserId()));
    Map<Long, Boolean> auditDoneMap = relationAuditUsers.stream()
        .collect(Collectors.toMap(RelationAuditUser::getAuditId,
            t -> AuditStatus.APPROVED.equals(t.getStatus()) || AuditStatus.REJECTION.equals(
                t.getStatus())));
    List<Long> auditIds = auditUserMapper.selectList(
            new LambdaQueryWrapperX<RelationAuditUser>().eq(RelationAuditUser::getAuditBy,
                SaTokenUtil.getUserId()))
        .stream().map(RelationAuditUser::getAuditId).collect(
            Collectors.toList());
    if (CollectionUtil.isEmpty(auditIds)) {
      return new PageInfo<>();
    }
    PageHelper.startPage(query.getPage(), query.getLimit());
    List<AuditResponse> audits = auditMapper.selectList(
            new LambdaQueryWrapperX<Audit>().inIfPresent(Audit::getId, auditIds)
                .eqIfPresent(Audit::getStatus, query.getStatus())
                .eqIfPresent(Audit::getType, query.getType()).orderByDesc(Audit::getCreateAt))
        .stream().map(t -> fromAudit(t, auditDoneMap)).collect(Collectors.toList());
    return new PageInfo<>(audits);
  }

  public List<AuditDetailResponse> detail(Long auditId) {
    Map<Long, Template> templateMap = templateService.getTemplateMap();
    return auditMapper.selectById(auditId).getContent().stream()
        .map(entity -> {
          AuditDetailResponse detail = new AuditDetailResponse();
          Template template = templateMap.get(entity.getFiledId());
          detail.setFiledName(template.getFieldName());
          if (FieldType.SELECT.equals(template.getType())) {
            String before = dictService.getData(template.getDictTypeCode(), entity.getBefore());
            String after = dictService.getData(template.getDictTypeCode(), entity.getAfter());
            detail.setBefore(before);
            detail.setAfter(after);
          } else {
            detail.setBefore(entity.getBefore());
            detail.setAfter(entity.getAfter());
          }
          return detail;
        }).collect(Collectors.toList());
  }

  public AjaxResult approve(Long auditId) {
    Audit audit = auditMapper.selectById(auditId);
    updateRelAuditUsers(audit, AuditStatus.APPROVED);

    if (audit.getStatus().equals(AuditStatus.WAITING)) {
      if (envConfigService.isMultiLevelAudit()) {
        RelationAuditUser auditUser = RelationAuditUser.builder()
            .auditId(audit.getId())
            .auditBy(userService.getSalesManager())
            .status(AuditStatus.WAITING_SECOND).build();
        auditUserMapper.insert(auditUser);
        audit.setStatus(AuditStatus.WAITING_SECOND);
        auditMapper.updateById(audit);
        return AjaxResult.success();
      }
    }
    if (AuditType.ADD_CUSTOMER.equals(audit.getType())) {
      customerService.add(audit.getContent(), audit.getEntityId());
    } else if (AuditType.DELETE_CUSTOMER.equals(audit.getType())) {
      customerService.remove(audit.getEntityId());
    } else if (AuditType.UPDATE_CUSTOMER.equals(audit.getType())) {
      customerService.update(audit.getContent(), audit.getEntityId());
    }
    audit.setStatus(AuditStatus.APPROVED);
    auditMapper.updateById(audit);
    return AjaxResult.success();
  }

  private void updateRelAuditUsers(Audit audit, AuditStatus status) {
    List<RelationAuditUser> relationAuditUsers = auditUserMapper.selectList(
        new LambdaQueryWrapperX<RelationAuditUser>()
            .eq(RelationAuditUser::getAuditId, audit.getId())
            .eq(RelationAuditUser::getAuditBy, SaTokenUtil.getUserId()));
    if (CollectionUtils.isNotEmpty(relationAuditUsers)) {
      RelationAuditUser relationAuditUser = relationAuditUsers.get(0);
      relationAuditUser.setStatus(status);
      auditUserMapper.updateById(relationAuditUser);
    }
  }

  public AjaxResult reject(Long auditId) {
    Audit audit = auditMapper.selectById(auditId);
    if (audit.getStatus().equals(AuditStatus.APPROVED)) {
      return AjaxResult.error("审批已通过，不能再修改");
    }
    updateRelAuditUsers(audit, AuditStatus.REJECTION);
    audit.setStatus(AuditStatus.REJECTION);
    auditMapper.updateById(audit);
    return AjaxResult.success();
  }

  public PageInfo<AuditResponse> getApplyList(AuditQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    List<AuditResponse> auditResponses = auditMapper.selectList(
            new LambdaQueryWrapperX<Audit>().eq(Audit::getCreateBy, SaTokenUtil.getUserId())
                .eqIfPresent(Audit::getStatus, query.getStatus())
                .eqIfPresent(Audit::getType, query.getType()).orderByDesc(Audit::getCreateAt))
        .stream().map(AuditResponse::fromAudit).collect(Collectors.toList());
    return new PageInfo<>(auditResponses);
  }

  public List<ApplyProgressResponse> getApplyProgress(Long auditId) {
    return auditUserMapper.selectList(new LambdaQueryWrapperX<RelationAuditUser>()
            .eq(RelationAuditUser::getAuditId, auditId)).stream()
        .map(ApplyProgressResponse::fromRelationAuditUser).collect(
            Collectors.toList());
  }
}
