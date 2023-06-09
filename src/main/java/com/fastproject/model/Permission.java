package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@Data
@TableName("def_permission")
public class Permission implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String description;

  private String url;

  private Integer isBlank;

  private Long parentId;

  private String code;

  private Integer type;

  private String icon;

  private Integer orderNum;

  private Character status;
}