package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author fastproject
 */
@TableName("rel_permission_role")
@Data
public class PermissionRole implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private Long roleId;

  private Long permissionId;


}