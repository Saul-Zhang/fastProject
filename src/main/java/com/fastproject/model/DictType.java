
package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 字典类型表
 */
@TableName("def_dic_type")
@Data
public class DictType implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   **/
  private String id;

  /**
   * 字典名称
   **/
  private String name;

  /**
   * 字典类型
   **/
  private String code;

  /**
   * 状态（0正常 1停用）
   **/
  private Character status;

  /**
   * 创建者
   **/
  private String createBy;

  /**
   * 创建时间
   **/
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  /**
   * 更新者
   **/
  private String updateBy;

  /**
   * 更新时间
   **/
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  /**
   * 备注
   **/
  private String remark;

}