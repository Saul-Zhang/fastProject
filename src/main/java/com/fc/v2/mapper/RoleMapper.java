package com.fc.v2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.v2.model.auto.TsysRole;
import java.util.List;
import javax.management.relation.Role;

/**
 * 角色Dao
 */
public interface RoleMapper extends BaseMapper<Role> {

  /**
   * 根据用户id查询角色
   */
  public List<TsysRole> queryUserRole(String userid);

  /**
   * 根据用户id查询角色id
   */
  List<String> queryUserRoleId(String userId);

}
