package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

@TableName(value = "def_operation_log")
@Data
public class OperationLog{

  private Long id;

  private String title;

  private String method;

  private Long operator;

  private String url;

  private String param;

  private String errorMsg;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createAt;

  private Long costTime;

}