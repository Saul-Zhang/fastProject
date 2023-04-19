package com.fastproject.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fastproject.mapper.PermissionMapper;
import com.fastproject.mapper.RelationRoleUserMapper;
import com.fastproject.mapper.RoleMapper;
import com.fastproject.model.auto.RelationRoleUser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

  @Autowired
  private PermissionMapper permissionMapper;

  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private RelationRoleUserMapper roleUserMapper;

  /**
   * 返回一个账号所拥有的权限码集合
   * <p> 注：权限变动时需要清除缓存：SaSessionCustomUtil.getSessionById("role-" + roleId).delete("Permission_List");
   */
  @Override
  public List<String> getPermissionList(Object loginId, String loginType) {
    List<String> permList = new ArrayList<>();
    for (String roleId : getRoleList(loginId, loginType)) {
      SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + roleId);
      List<String> list = roleSession.get("Permission_List",
          () -> permissionMapper.queryPermsList(roleId));
      permList.addAll(list);
    }
    return permList;
  }

  /**
   * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
   * <p> 注：角色变动时需要清除缓存：StpUtil.getSessionByLoginId(userId).delete("Role_List");
   */
  @Override
  public List<String> getRoleList(Object loginId, String loginType) {
    SaSession session = StpUtil.getSessionByLoginId(loginId);
    return session.get("Role_List",
        () -> roleUserMapper.selectList(new QueryWrapper<RelationRoleUser>().lambda()
                .eq(RelationRoleUser::getUserId, loginId))
            .stream().map(relationRoleUser -> String.valueOf(relationRoleUser.getRoleId())).collect(Collectors.toList()));
  }

}