package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fastproject.model.constant.RoleCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("def_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  private Long id;

  private String name;

  private String description;

  private Character status;

  private RoleCode code;

  public Role(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}