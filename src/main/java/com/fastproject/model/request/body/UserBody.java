package com.fastproject.model.request.body;

import java.util.List;
import lombok.Data;

/**
 * @author fastProjcet
 * @date 2023/4/22 0:24
 */
@Data
public class UserBody {
  private Long id;

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
   * 岗位id
   **/
  private String posId;

  /**
   * 工号
   */
  private String employeeId;

  private Integer status;

  private String phone;

  private Integer gender;

  private String email;

  private List<Long> roleIds;

  private List<Long> departmentIds;
}
