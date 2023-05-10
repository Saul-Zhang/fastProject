package com.fastproject.service;

import cn.dev33.satoken.session.SaSessionCustomUtil;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.PermissionMapper;
import com.fastproject.mapper.PermissionRoleMapper;
import com.fastproject.model.Permission;
import com.fastproject.model.PermissionRole;
import com.fastproject.model.custom.Menu;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.LayUiTree;
import com.fastproject.model.response.TreeResponse;
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
          .eq(Permission::getParentId, id));
    });
    return AjaxResult.success();
  }


  public AjaxResult insert(Permission record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    //判断为目录的时候添加父id为0
//    if (record.getType() == 0) {
//      record.setParentId(0L);
//    }
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

  public LayUiTree getParent(Permission permission) {
    LayUiTree response = new LayUiTree();
    Permission parent = permissionMapper.selectById(permission.getParentId());
    if (parent == null) {
      response.setTitle("顶级权限");
      response.setId(0L);
      response.setParentId(-1L);
      return response;
    }
    response.setId(parent.getId());
    response.setParentId(parent.getParentId());
    response.setTitle(parent.getName());
    return response;
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
        .map(permission -> new Menu(permission.getId(), permission.getParentId(),
            permission.getName(),
            permission.getType(), permission.getIsBlank(), permission.getIcon(),
            permission.getUrl()))
        .collect(Collectors.toList());
    return getTree(collect);
  }

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
   */
  public List<LayUiTree> getPermissionsByRoleId(Long roleId) {
    //所有权限
    List<Permission> allPermission = getPermissionByUserId(null);
    //角色权限
    List<Permission> rolePermission = permissionMapper.getByRoleId(roleId);

    List<LayUiTree> result = new ArrayList<>();

    allPermission.forEach(permission -> {
      LayUiTree response = new LayUiTree();
      response.setId(permission.getId());
      response.setParentId(permission.getParentId());
      response.setTitle(permission.getName());
      rolePermission.forEach(entity -> {
        if (entity.getId().equals(response.getId())) {
          response.setCheckArr("1");
        }
      });
      result.add(response);

    });
    return result;

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

  public AjaxResult updateStatus(List<Long> ids, Character status) {
    for (Long id : ids) {
      Permission permission = permissionMapper.selectById(id);
      if (permission == null) {
        return AjaxResult.error("权限不存在");
      }
      List<Permission> children = getAllChildren(id);
      children.add(permission);

      children.forEach(per -> {
        per.setStatus(status);
        permissionMapper.updateById(per);
      });
    }
    return AjaxResult.success();
  }

  public List<Permission> getAllChildren(Long id) {
    List<Permission> permissions = new ArrayList<>();
    getChildrenPermission(id, permissions);
    return permissions;
  }

  public void getChildrenPermission(Long id, List<Permission> result) {
    List<Permission> permissions = permissionMapper.selectList(
        new LambdaQueryWrapperX<Permission>().eq(Permission::getParentId, id));
    if (!CollectionUtils.isEmpty(permissions)) {
      permissions.forEach(per -> getChildrenPermission(per.getId(), result));
    }
    result.addAll(permissions);
  }

  public TreeResponse selectParent() {
    List<LayUiTree> list = getPermissionByUserId(null).stream()
        .map(per -> {
          LayUiTree response = new LayUiTree();
          response.setId(per.getId());
          response.setParentId(per.getParentId());
          response.setTitle(per.getName());
          return response;
        }).collect(Collectors.toList());
    LayUiTree response = new LayUiTree();
    response.setTitle("顶级权限");
    response.setId(0L);
    response.setParentId(-1L);
    list.add(response);
    return TreeResponse.treeData(list);
  }
}
