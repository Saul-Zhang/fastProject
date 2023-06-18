package com.fastproject.model.request.body;

import com.fastproject.model.User;
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

  private Character status;

  private String phone;

  private Character gender;

  private String email;

  private List<Long> roleIds;

  private List<Long> departmentIds;

  public static User userBody2User(UserBody userBody) {

    User user = new User();
    user.setId(userBody.getId());
    user.setUsername(userBody.getUsername());
    user.setPassword(userBody.getPassword());
    user.setRealName(userBody.getRealName());
    user.setPosId(userBody.getPosId());
    user.setEmployeeId(userBody.getEmployeeId());
    user.setPhone(userBody.getPhone());
    user.setGender(userBody.getGender());
    user.setEmail(userBody.getEmail());
    user.setStatus(userBody.getStatus());
    return user;
  }
}
