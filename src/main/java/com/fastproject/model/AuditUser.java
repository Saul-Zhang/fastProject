package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/24 22:30
 */
@TableName(value = "rel_audit_user")
@Data
@Builder
public class AuditUser extends DefinedField {

  private Long id;

  private AuditStatus status;

  private Long approvedBy;

  private Long auditId;
}
