package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/24 22:30
 */
@TableName(value = "rel_audit_user")
@Data
@Builder
public class RelationAuditUser extends DefinedField {

  private Long id;

  private AuditStatus status;

  private Long auditBy;

  private Long auditId;

  @TableField(fill = FieldFill.UPDATE)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateAt;
}
