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
   * 昵称
   **/
  @ApiModelProperty(value = "昵称")
  private String realName;

  /**
   * 部门id
   **/
  @ApiModelProperty(value = "部门id")
  private String deptId;

//  private List<String> roleNames;

  /**
   * 岗位id
   **/
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
