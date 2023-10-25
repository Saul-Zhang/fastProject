package com.fastproject.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuditNoticeResponse {
    private Long pendingCount;
    private Long rejectCount;
    private Long totalCount;
}
