package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.PermissionMapper;
import com.fastproject.mapper.PermissionRoleMapper;
import com.fastproject.model.Permission;
import com.fastproject.model.PermissionRole;
import com.fastproject.model.auto.TsysPermissionExample;
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
   * 修改权限状态展示或者不展示
   *
   * @param record
   * @return
   */
  public int updateVisible(Permission record) {
    return tsysPermissionMapper.updateByPrimaryKeySelective(record);
  }


  @Override
  public int updateByExampleSelective(Permission record, TsysPermissionExample example) {
    //默认设置不跳转
    if (record.getIsBlank() == null) {
      record.setIsBlank(0);
    }
    return tsysPermissionMapper.updateByExampleSelective(record, example);
  }


  @Override
  public int updateByExample(Permission record, TsysPermissionExample example) {
    //默认设置不跳转
    if (record.getIsBlank() == null) {
      record.setIsBlank(0);
    }
    return tsysPermissionMapper.updateByExample(record, example);
  }

  @Override
  public List<Permission> selectByExample(TsysPermissionExample example) {

    return tsysPermissionMapper.selectByExample(example);
  }


  @Override
  public long countByExample(TsysPermissionExample example) {

    return tsysPermissionMapper.countByExample(example);
  }


  @Override
  public int deleteByExample(TsysPermissionExample example) {

    return tsysPermissionMapper.deleteByExample(example);
  }

  /**
   * 检查权限名字
   *
   * @param tsysUser
   * @return
   */
  public int checkNameUnique(Permission permission) {
    TsysPermissionExample example = new TsysPermissionExample();
    example.createCriteria().andNameEqualTo(permission.getName());
    List<Permission> list = tsysPermissionMapper.selectByExample(example);
    return list.size();
  }

  /**
   * 检查权限URL
   *
   * @param tsysUser
   * @return
   */
  public int checkURLUnique(Permission permission) {
    TsysPermissionExample example = new TsysPermissionExample();
    example.createCriteria().andUrlEqualTo(permission.getUrl());
    List<Permission> list = tsysPermissionMapper.selectByExample(example);
    return list.size();
  }

  /**
   * 检查权限perms字段
   *
   * @param tsysUser
   * @return
   */
  public int checkPermsUnique(Permission permission) {
    TsysPermissionExample example = new TsysPermissionExample();
    example.createCriteria().andPermsEqualTo(permission.getPerms());
    List<Permission> list = tsysPermissionMapper.selectByExample(example);
    return list.size();
  }


  /**
   * 根据父id 以及类型查询权限子集
   *
   * @param pid
   * @return
   */
  public List<Permission> queryPid(String pid, int type) {
    TsysPermissionExample example = new TsysPermissionExample();
    example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
    return tsysPermissionMapper.selectByExample(example);
  }

  /**
   * 根据用户id查询菜单栏
   */
  public List<Menu> getMenus(Integer userId) {
    List<Menu> collect = getPermissionByUserid(userId).stream()
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
    Map<String, Menu> map;
    List<Menu> treeList = new ArrayList<>();

    if (null == list || list.isEmpty()) {
      return null;
    }
    map = list.stream().collect(Collectors.toMap(Menu::getId, a -> a, (k1, k2) -> k1));
    for (Menu menu : list) {
      if ("0".equals(menu.getParentId())) {
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
    List<Permission> allPower = getPermissionByUserid(null);
    //角色权限
    List<Permission> rolePower = permissionMapper.queryRoleId(roleId);

    List<SysPower> sysPowerList = new ArrayList<>();

    allPower.forEach(sysPower -> {
      SysPower sysPower1 = new SysPower(sysPower.getId(), sysPower.getName(), sysPower.getType(),
          sysPower.getPerms(), sysPower.getUrl(), sysPower.getIsBlank(), sysPower.getPid(),
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
  public List<Permission> getPermissionByUserid(Long userId) {
//    if (userId == null) {
//      TsysPermissionExample example = new TsysPermissionExample();
//      example.createCriteria().andVisibleEqualTo(0);
//      example.setOrderByClause("order_num  is null  ASC,order_num  ASC");
//      return tsysPermissionMapper.selectByExample(example);
//    }
    return permissionMapper.findByAdminUserId(userId);
  }


  /**
   * 根据权限字段查询是否存在
   *
   * @param perms
   * @return
   * @author fuce
   * @Date 2019年9月1日 上午2:06:31
   */
  public Boolean queryLikePerms(String perms) {
    TsysPermissionExample example = new TsysPermissionExample();
    example.createCriteria().andPermsLike("%gen:" + perms + "%");
    List<Permission> list = tsysPermissionMapper.selectByExample(example);
    return list.size() > 0;
  }


}
