package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fastproject.common.mybatis.AuditContentListHandler;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fastProject
 * @date 2023/6/24 22:30
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "def_audit", autoResultMap = true)
@Data
@Builder
public class Audit extends DefinedField {

  private Long id;

  private AuditType type;

  private Long entityId;

  @TableField(typeHandler = AuditContentListHandler.class)
  private List<AuditContent> content;

  private AuditStatus status;

  private String description;

  private Boolean unread;
}
