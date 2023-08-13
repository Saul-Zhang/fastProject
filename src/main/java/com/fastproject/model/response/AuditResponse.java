package com.fastproject.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fastproject.common.annotation.Dict;
import com.fastproject.model.Audit;
import com.fastproject.model.AuditStatus;
import com.fastproject.model.AuditType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/28 22:12
 */
@Data
public class AuditResponse implements Serializable {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Dict(dictCode = "audit_type")
  private AuditType type;

  @Dict(dictCode = "audit_status")
  private AuditStatus status;

  private String description;

  @Dict(dictCode = "user")
  private Long createBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createAt;

  private boolean done;

  public static AuditResponse fromAudit(Audit audit,  Map<Long, Boolean> auditDoneMap) {
    AuditResponse response = fromAudit(audit);
    response.setDone(auditDoneMap.get(audit.getId()));
    return response;
  }

  public static AuditResponse fromAudit(Audit audit) {
    AuditResponse response = new AuditResponse();
    response.setId(audit.getId());
    response.setType(audit.getType());
    response.setStatus(audit.getStatus());
    response.setDescription(audit.getDescription());
    response.setCreateAt(audit.getCreateAt());
    response.setCreateBy(audit.getCreateBy());
    return response;
  }
}
