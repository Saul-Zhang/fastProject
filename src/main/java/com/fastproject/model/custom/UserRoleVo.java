package com.fastproject.model.custom;

import com.fastproject.model.Role;
import java.util.List;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/8/25 23:19
 */
@Data
public class UserRoleVo {

//  private Long id;
  private String realName;
  private String employeeId;
  private List<Role> roles;
}
