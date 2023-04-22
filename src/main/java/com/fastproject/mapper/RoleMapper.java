package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Role;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper extends BaseMapper<Role> {



  @Select("SELECT role_id as id, r.name as name "
      + "FROM rel_role_user ru,\n"
      + "     def_role r\n"
      + "WHERE ru.role_id = r.id\n"
      + "  AND ru.user_id = #{userId}")
  List<Role> queryRolesByUserId(String userId);

  /**
   * 根据用户id查询角色id
   */
  List<String> queryUserRoleId(String userId);

}
