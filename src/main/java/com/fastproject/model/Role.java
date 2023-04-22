package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("def_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  private String id;

  private String name;

}