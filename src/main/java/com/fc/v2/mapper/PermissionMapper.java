package com.fc.v2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.v2.model.auto.Permission;
import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

  /**
   * 查询全部权限
   *
   */
  List<String> findAll();

  /**
   * 根据用户id查询出用户的所有权限
   *
   * @param userId
   * @return
   */
  List<Permission> findByAdminUserId(String userId);

  /**
   * 根据角色id查询权限
   *
   * @param roleid
   * @return
   */
  List<Permission> queryRoleId(String roleid);

  /**
   * 根据角色id查询权限码集合
   */
  List<String> queryPermsList(String roleId);


}
