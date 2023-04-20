package com.fastproject.model.response;


import com.fastproject.common.annotation.Dict;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserResponse implements Serializable {

  private static final long serialVersionUID = 1L;

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
  private Integer deptId;

  private String roleName;

  /**
   * 岗位id
   **/
  private String posId;

  /**
   * 部门名称
   **/
  private String deptName;
  /**
   * 岗位名称
   **/
  private String posName;

  private String employeeId;

  private String status;

  private String tel;

  @Dict(dictCode = "gender")
  private String gender;

  private String email;
}
