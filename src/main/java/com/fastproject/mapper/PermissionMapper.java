package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface PermissionMapper extends BaseMapper<Permission> {

  /**
   * 根据用户id查询出用户的所有权限
   */
  @Select("select distinct p.*\n"
      + "from rel_permission_role pr,\n"
      + "     rel_role_user ru,\n"
      + "     def_permission p\n"
      + "where pr.role_id = ru.role_id\n"
      + "  AND pr.permission_id = p.id\n"
      + "  and p.status = 1\n"
      + "  AND ru.user_id = #{userId}\n"
      + "ORDER BY p.order_num is null ASC, p.order_num ASC")
  List<Permission> getByUserId(Long userId);

  /**
   * 根据角色id查询权限
   */
  @Select("select p.*\n"
      + "from def_permission p\n"
      + "         left join rel_permission_role pr on p.id = pr.permission_id\n"
      + "where pr.role_id = #{roleId} ")
  List<Permission> getByRoleId(Long roleId);

  /**
   * 根据角色id查询权限码集合
   */
  @Select("select code\n"
      + "from def_permission p\n"
      + "         left join rel_permission_role pr on p.id = pr.permission_id\n"
      + "where pr.role_id = #{roleId}")
  List<String> getCodesByRoleId(Long roleId);


}
