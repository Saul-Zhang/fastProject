package com.fastproject.model.response;


import com.fastproject.common.annotation.Dict;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author fastProject
 */
@Data
public class UserResponse implements Serializable {

  private String id;

  private String username;

  private String realName;

  private String deptId;

  private String posId;

  /**
   * 部门ids,dtree组件需要用逗号分割
   **/
  private String departmentIds;
  /**
   * 岗位名称
   **/
  private String posName;

  private String employeeId;

  private Integer status;

  private String phone;

  @Dict(dictCode = "gender")
  private Integer gender;

  private String email;
}
