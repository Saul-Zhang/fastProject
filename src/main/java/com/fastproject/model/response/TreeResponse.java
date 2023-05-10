package com.fastproject.model.response;


import lombok.Data;

@Data
public class TreeResponse {

  /**
   * 状态信息
   */
  private Status status = new Status();

  /**
   * 返回数据
   */
  private Object data;

  /**
   * 所需内部类
   */
  @Data
  public static class Status {

    private Integer code = 200;

    private String message = "默认";

    public Status(Integer code, String message) {
      this.code = code;
      this.message = message;
    }

    public Status() {
    }
  }

  public static TreeResponse treeData(Object data){
    TreeResponse treeResponse = new TreeResponse();
    treeResponse.setData(data);
    return treeResponse;
  }

}
