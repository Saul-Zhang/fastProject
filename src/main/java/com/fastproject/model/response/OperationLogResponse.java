package com.fastproject.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fastproject.common.annotation.Dict;
import com.fastproject.model.OperationLog;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OperationLogResponse {

  private Long id;

  private String title;

  private String method;

  @Dict(dictCode = "user")
  private Long operator;

  private String url;

  private String param;

  private String errorMsg;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createAt;

  private Long costTime;

  public static OperationLogResponse fromOperationLog(OperationLog log) {
    return new OperationLogResponse()
        .setId(log.getId()).setTitle(log.getTitle()).setMethod(log.getMethod())
        .setOperator(log.getOperator()).setUrl(log.getUrl()).setParam(log.getParam())
        .setCreateAt(log.getCreateAt())
        .setErrorMsg(log.getErrorMsg());
  }
}