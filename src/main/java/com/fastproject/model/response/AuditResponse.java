package com.fastproject.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.AuditType;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/28 22:12
 */
@Data
public class AuditResponse {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  private AuditType type;

  private Long entityId;

  private AuditStatus status;

  private String description;

  public static AuditResponse fromAudit(Audit audit) {
    AuditResponse response = new AuditResponse();
    response.setId(audit.getId());
    response.setType(audit.getType());
    response.setEntityId(audit.getEntityId());
    response.setStatus(audit.getStatus());
    response.setDescription(audit.getDescription());
    return response;
  }
}
