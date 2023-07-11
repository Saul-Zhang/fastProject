package com.fastproject.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fastproject.common.annotation.Dict;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.AuditType;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/28 22:12
 */
@Data
public class AuditResponse {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Dict(dictCode = "audit_type")
  private AuditType type;

//  private Long entityId;

  @Dict(dictCode = "audit_status")
  private AuditStatus status;

  private String description;

  @Dict(dictCode = "user")
  private Long createBy;
  private LocalDateTime createAt;

  public static AuditResponse fromAudit(Audit audit) {
    AuditResponse response = new AuditResponse();
    response.setId(audit.getId());
    response.setType(audit.getType());
//    response.setEntityId(audit.getEntityId());
    response.setStatus(audit.getStatus());
    response.setDescription(audit.getDescription());
    response.setCreateAt(audit.getCreateAt());
    response.setCreateBy(audit.getCreateBy());
    return response;
  }
}
