package com.fastproject.service;

import cn.dev33.satoken.session.SaSessionCustomUtil;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.PermissionMapper;
import com.fastproject.mapper.PermissionRoleMapper;
import com.fastproject.model.Permission;
import com.fastproject.model.PermissionRole;
import com.fastproject.model.custom.Menu;
import com.fastproject.model.custom.SysPower;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.util.SnowflakeIdWorker;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class PermissionService {

  private final PermissionMapper permissionMapper;

  private final PermissionRoleMapper permissionRoleMapper;

  /**
   * 分页查询
   */
  public List<Permission> list() {
    return permissionMapper.selectList(null);
  }


  @Transactional(rollbackFor = Exception.class)
  public AjaxResult delete(List<Long> ids) {

    permissionRoleMapper.delete(new LambdaQueryWrapperX<PermissionRole>()
        .in(PermissionRole::getPermissionId, ids));
    ids.forEach(id -> {
      permissionMapper.delete(new LambdaQueryWrapperX<Permission>()
          .eq(Permission::getId, id));
      permissionMapper.delete(new LambdaQueryWrapperX<Permission>()
          .eq(Permission::getPid, id));
    });
    return AjaxResult.success();
  }


  public AjaxResult insert(Permission record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    //判断为目录的时候添加父id为0
    if (record.getType() == 0) {
      record.setPid(1L);
    }
    //默认设置不跳转
    if (record.getIsBlank() == null) {
      record.setIsBlank(0);
    }
    permissionMapper.insert(record);
    return AjaxResult.success();
  }

  public Permission selectById(Long id) {

    return permissionMapper.selectById(id);
  }

  public AjaxResult update(Permission record) {
    //默认设置不跳转
    if (record.getIsBlank() == null) {
      record.setIsBlank(0);
    }
    permissionMapper.updateById(record);
    return AjaxResult.success();
  }


  /**
   * 根据用户id查询菜单栏
   */
  public List<Menu> getMenus(Long userId) {
    List<Menu> collect = getPermissionByUserId(userId).stream()
        .map(permission -> new Menu(permission.getId(), permission.getPid(), permission.getName(),
            permission.getType(), permission.getIsBlank(), permission.getIcon(),
            permission.getUrl()))
        .collect(Collectors.toList());
    return getTree(collect);
  }

//  /**
//   * 递归查询权限
//   */
//  private List<Menu> getMenus(List<Menu> treeList, String parentId) {
//    List<Menu> menuList = new ArrayList<>();
//    if (StringUtils.isNotNull(parentId) && treeList != null && treeList.size() > 0) {
//      List<Menu> childList = null;
//      for (Menu menu : treeList) {
//        if (permission.getPid().equals(parentId)) {
//          if (permission.getChildCount() != null && permission.getChildCount() > 0) {
//            childList = getMenus(treeList, permission.getId());
//          }
//          Menu menu = new Menu(permission.getId(), permission.getPid(), permission.getName(),
//              permission.getType(), permission.getIsBlank(), permission.getIcon(),
//              permission.getUrl(), childList);
//          menuList.add(menu);
//          childList = null;
//        }
//      }
//    }
//
//    return menuList;
//  }

  public static List<Menu> getTree(List<Menu> list) {
    Map<Long, Menu> map;
    List<Menu> treeList = new ArrayList<>();

    if (null == list || list.isEmpty()) {
      return null;
    }
    map = list.stream().collect(Collectors.toMap(Menu::getId, a -> a, (k1, k2) -> k1));
    for (Menu menu : list) {
      if (0L == (menu.getParentId())) {
        treeList.add(menu);
      } else {
        // 子级通过父id获取到父级的类型
        Menu parent = map.get(menu.getParentId());
        // 父级获得子级，再将子级放到对应的父级中
        parent.getChildren().add(menu);
      }
    }
    return treeList;
  }


  /**
   * 根据角色id查询所有权限，权限有会有标识表示
   *
   * @return
   */
  public List<SysPower> getRolePower(String roleId) {
    //所有权限
    List<Permission> allPower = getPermissionByUserId(null);
    //角色权限
    List<Permission> rolePower = permissionMapper.getByRoleId(roleId);

    List<SysPower> sysPowerList = new ArrayList<>();

    allPower.forEach(sysPower -> {
      SysPower sysPower1 = new SysPower(sysPower.getId(), sysPower.getName(), sysPower.getType(),
          sysPower.getCode(), sysPower.getUrl(), sysPower.getIsBlank(), sysPower.getPid(),
          sysPower.getIcon(), sysPower.getOrderNum(), sysPower.getStatus(), "0");
      rolePower.forEach(sysRolePower -> {
        if (sysRolePower.getId().equals(sysPower.getId())) {
          sysPower1.setCheckArr("1");
          return;
        }
      });
      sysPowerList.add(sysPower1);

    });
    return sysPowerList;

  }


  /**
   * 根据用户id获取用户角色如果用户为null 获取所有权限
   */
  public List<Permission> getPermissionByUserId(Long userId) {
    if (userId == null) {
      return permissionMapper.selectList(null);
    }
    return permissionMapper.getByUserId(userId);
  }


  public AjaxResult updateRolePermission(Long roleId, List<Long> permissionIds) {
    if (roleId == null || CollectionUtils.isEmpty(permissionIds)) {
      return AjaxResult.error();
    }
    permissionRoleMapper.delete(
        new LambdaQueryWrapperX<PermissionRole>().eq(PermissionRole::getRoleId, roleId));

    permissionIds.forEach(permissionId -> {
      PermissionRole permissionRole = new PermissionRole();
      permissionRole.setRoleId(roleId);
      permissionRole.setPermissionId(permissionId);
      permissionRoleMapper.insert(permissionRole);
    });
    // 刷新权限
    SaSessionCustomUtil.getSessionById("role-" + roleId).delete("Permission_List");
    return AjaxResult.success();
  }
}
