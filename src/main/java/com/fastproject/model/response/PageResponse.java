package com.fastproject.model.response;

import lombok.Data;

@Data
public class PageResponse {

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
  public static PageResponse page(Object data,long count) {
    PageResponse pageResponse = new PageResponse();
    pageResponse.setData(data);
    pageResponse.setCode(0);
    pageResponse.setCount(count);
    pageResponse.setMsg("请求成功");

    return pageResponse;
  }

  public static PageResponse list(Object data) {
    PageResponse pageResponse = new PageResponse();
    pageResponse.setData(data);
    pageResponse.setCode(0);
    pageResponse.setMsg("请求成功");
    return pageResponse;
  }
}
