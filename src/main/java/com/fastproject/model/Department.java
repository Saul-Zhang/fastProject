package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 部门表
 */
@Data
@TableName("def_department")
public class Department implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   **/
  @ApiModelProperty(value = "id")
  private Long id;

  /**
   * 父id
   **/
  @ApiModelProperty(value = "父id")
  private Long parentId;

  /**
   * 部门名称
   **/
  @ApiModelProperty(value = "部门名称")
  private String name;

  /**
   * 部门负责人
   **/
  @ApiModelProperty(value = "部门负责人")
  private String leader;

  /**
   * 电话
   **/
  @ApiModelProperty(value = "电话")
  private String phone;

  /**
   * 邮箱
   **/
  @ApiModelProperty(value = "邮箱")
  private String email;

  /**
   * 状态
   **/
  @ApiModelProperty(value = "状态")
  private Character status;

  /**
   * 排序
   **/
  @ApiModelProperty(value = "排序")
  private Integer orderNum;
}