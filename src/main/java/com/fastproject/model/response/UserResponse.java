package com.fastproject.model.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponse {

  /**
   * 主键
   **/
  @ApiModelProperty(value = "主键")
  private Integer id;

  /**
   * 用户账号
   **/
  @ApiModelProperty(value = "用户账号")
  private String username;


  /**
   * 昵称
   **/
  @ApiModelProperty(value = "昵称")
  private String realName;

  /**
   * 部门id
   **/
  @ApiModelProperty(value = "部门id")
  private Integer depId;

  private String roleName;

  /**
   * 岗位id
   **/
  private String posId;

  /**
   * 部门名称
   **/
  private String depName;
  /**
   * 岗位名称
   **/
  private String posName;

  private String employeeId;

  private String status;

  private String tel;

  private String gender;

  private String email;

  public String getStatus(){
    return this.status+"qww";
  }
}
