package com.fastproject.model.response;

import lombok.Data;

@Data
public class PageResult {

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
  public static PageResult page(Object data,long count) {
    PageResult pageResult = new PageResult();
    pageResult.setData(data);
    pageResult.setCode(0);
    pageResult.setCount(count);
    pageResult.setMsg("请求成功");

    return pageResult;
  }

  public static PageResult page(Object data) {
    PageResult pageResult = new PageResult();
    pageResult.setData(data);
    pageResult.setCode(0);
    pageResult.setMsg("请求成功");
    return pageResult;
  }
}
