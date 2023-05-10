package com.fastproject.model.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TsysTables implements Serializable {

  private static final long serialVersionUID = 1L;
  private String tableName;//表名
  private String engine;//表引擎
  private String tableComment;//表注释
  private String tableModel;//表实体名字
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
  private Date createTime;
}
