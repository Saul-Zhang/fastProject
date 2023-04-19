package com.fastproject.common.domain;

import lombok.Data;

@Data
public class ResultTable {

  /**
   * 状态码
   */
  private Integer code;

  /**
   * 提示消息
   */
  private String msg;

  /**
   * 消息总量
   */
  private Long count;

  /**
   * 数据对象
   */
  private Object data;


  /**
   * 构 建
   */
  public static ResultTable pageTable(long count, Object data) {
    ResultTable resultTable = new ResultTable();
    resultTable.setData(data);
    resultTable.setCode(0);
    resultTable.setCount(count);
    resultTable.setMsg("请求成功");

    return resultTable;
  }

  public static ResultTable dataTable(Object data) {
    ResultTable resultTable = new ResultTable();
    resultTable.setData(data);
    resultTable.setCode(0);
    resultTable.setMsg("请求成功");
    return resultTable;
  }
}
