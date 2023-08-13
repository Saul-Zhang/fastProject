package com.fastproject.model.response;

import com.fastproject.common.annotation.Dict;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.RelationAuditUser;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fastProject
 * @date 2023/8/7 22:00
 */
@Data
@Accessors(chain = true)
public class ApplyProgressResponse {

  @Dict(dictCode = "audit_status")
  private AuditStatus status;

  @Dict(dictCode = "user")
  private Long auditBy;

  private LocalDateTime updateAt;

  public static ApplyProgressResponse fromRelationAuditUser(RelationAuditUser auditUser) {
   return new ApplyProgressResponse().setStatus(auditUser.getStatus())
        .setAuditBy(auditUser.getAuditBy())
        .setUpdateAt(auditUser.getUpdateAt());
  }
}
