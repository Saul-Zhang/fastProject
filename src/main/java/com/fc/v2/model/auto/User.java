package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName("def_user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   **/
  @ApiModelProperty(value = "主键")
  private String id;

  /**
   * 用户账号
   **/
  @ApiModelProperty(value = "用户账号")
  private String username;

  /**
   * 用户密码
   **/
  @ApiModelProperty(value = "用户密码")
  private String password;

  /**
   * 昵称
   **/
  @ApiModelProperty(value = "昵称")
  private String nickname;

  /**
   * 部门id
   **/
  @ApiModelProperty(value = "部门id")
  private Integer depId;

  /**
   * 岗位id
   **/
  @ApiModelProperty(value = "岗位id")
  private String posId;

  /**
   * 工号
   */
  private String employeeId;

  private Integer status;

  private String tel;

  private String gender;

  private String email;

}