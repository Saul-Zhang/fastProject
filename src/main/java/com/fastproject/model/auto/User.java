package com.fastproject.model.auto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName("def_user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   **/
  private String id;

  /**
   * 用户账号
   **/
  private String username;

  /**
   * 用户密码
   **/
  private String password;

  /**
   * 姓名
   **/
  private String realName;

  /**
   * 部门id
   **/
  private String depId;

  /**
   * 岗位id
   **/
  private String posId;

  /**
   * 工号
   */
  private String employeeId;

  private Integer status;

  private String tel;

  private Integer gender;

  private String email;

}