package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@Data
@TableName("def_permission")
public class Permission implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String name;

  private String description;

  private String url;

  private Integer isBlank;

  private String pid;

  private String perms;

  private Integer type;

  private String icon;

  private Integer orderNum;

  private Integer status;

  private Integer childCount;

}