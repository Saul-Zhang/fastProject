package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("rel_role_user")
@NoArgsConstructor
@AllArgsConstructor
public class RelationRoleUser implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String userId;

  private String roleId;

}