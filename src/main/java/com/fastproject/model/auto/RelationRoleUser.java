package com.fastproject.model.auto;

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

  private Integer id;

  private Integer userId;

  private Integer roleId;

}