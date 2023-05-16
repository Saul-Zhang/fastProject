package com.fastproject.model.request.body;

import java.util.List;
import lombok.Data;

/**
 * @author fastproject
 * @date 2023/5/5 18:47
 */
@Data
public class PermissionRoleBody {

  private Long roleId;
  private List<Long> permissionIds;
}
