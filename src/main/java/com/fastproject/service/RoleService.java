package com.fastproject.service;

import cn.hutool.core.util.RandomUtil;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.RoleMapper;
import com.fastproject.mapper.TsysPermissionRoleMapper;
import com.fastproject.mapper.TsysRoleMapper;
import com.fastproject.model.Role;
import com.fastproject.model.User;
import com.fastproject.model.auto.TsysPermissionRole;
import com.fastproject.model.auto.TsysPermissionRoleExample;
import com.fastproject.model.auto.TsysRoleExample;
import com.fastproject.model.constant.Status;
import com.fastproject.model.request.query.RoleQuery;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleMapper roleMapper;
  //角色mapper
  @Autowired
  private TsysRoleMapper tsysRoleMapper;
  //自动生成的权限角色映射mapper
  @Autowired
  private TsysPermissionRoleMapper tsysPermissionRoleMapper;


  /**
   * 分页查询
   */
  public PageInfo<Role> list(RoleQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    LambdaQueryWrapperX<Role> queryWrapperX = new LambdaQueryWrapperX<Role>()
        .likeIfPresent(Role::getName, query.getName())
        .eq(Role::getStatus, Status.ENABLE);
    return new PageInfo<>(roleMapper.selectList(queryWrapperX));
  }

  /**
   * 查询全部角色集合
   */
  public List<Role> getAll() {
    return roleMapper.selectList(null);
  }


  @Transactional(rollbackFor = Exception.class)
  public int deleteByPrimaryKey(String ids) {
    List<String> lista = ConvertUtil.toListStrArray(ids);
    //先删除角色下面的所有权限
    TsysPermissionRoleExample permissionRoleExample = new TsysPermissionRoleExample();
    permissionRoleExample.createCriteria().andRoleIdIn(lista);
    tsysPermissionRoleMapper.deleteByExample(permissionRoleExample);
    //在删除角色
    TsysRoleExample example = new TsysRoleExample();
    example.createCriteria().andIdIn(lista);
    return tsysRoleMapper.deleteByExample(example);
  }

  @Transactional
  public AjaxResult updateStatus(List<String> roleIds, Character status) {
    roleIds.forEach(id -> {
      Role entity = new Role();
      entity.setStatus(status);
      entity.setId(id);
      roleMapper.updateById(entity);
    });
    return AjaxResult.success();
  }

  public int insertSelective(Role record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    return tsysRoleMapper.insertSelective(record);
  }

  /**
   * 添加角色绑定权限
   *
   * @param record 角色信息
   * @param prem   权限id集合
   * @return
   */
  @Transactional
  public int insertRoleAndPrem(Role record, String prem) {
    //添加雪花主键id
    String roleid = SnowflakeIdWorker.getUUID();
    record.setId(roleid);
    //添加权限
    List<String> prems = ConvertUtil.toListStrArray(prem);
    for (String premid : prems) {
      TsysPermissionRole tsysPermissionRole = new TsysPermissionRole(RandomUtil.randomUUID(),
          roleid, premid);
      tsysPermissionRoleMapper.insertSelective(tsysPermissionRole);
    }
    return tsysRoleMapper.insertSelective(record);
  }


  public Role selectByPrimaryKey(String id) {

    return tsysRoleMapper.selectByPrimaryKey(id);
  }


  public int updateByPrimaryKeySelective(Role record) {
    return tsysRoleMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 修改用户角色 以及下面绑定的权限
   *
   * @param record
   * @return
   */
  @Transactional
  public int updateRoleAndPrem(String roleId, String powerIds) {
    //先删除角色下面的所有权限
    TsysPermissionRoleExample permissionRoleExample = new TsysPermissionRoleExample();
    permissionRoleExample.createCriteria().andRoleIdEqualTo(roleId);
    tsysPermissionRoleMapper.deleteByExample(permissionRoleExample);
    //添加权限关联
    List<String> prems = ConvertUtil.toListStrArray(powerIds);
    int i = 0;
    for (String pre : prems) {
      TsysPermissionRole permissionRole = new TsysPermissionRole(RandomUtil.randomUUID(), roleId,
          pre);
      tsysPermissionRoleMapper.insertSelective(permissionRole);
      i++;
    }

    return i;
  }


  public int updateByExample(Role record, TsysRoleExample example) {

    return tsysRoleMapper.updateByExample(record, example);
  }


  public List<Role> selectByExample(TsysRoleExample example) {

    return tsysRoleMapper.selectByExample(example);
  }


  /**
   * 检查角色name
   *
   * @param tsysUser
   * @return
   */
  public int checkNameUnique(Role role) {
    TsysRoleExample example = new TsysRoleExample();
    example.createCriteria().andNameEqualTo(role.getName());
    List<Role> list = tsysRoleMapper.selectByExample(example);
    return list.size();
  }

  /**
   * 根据用户id查询角色
   * @param userid
   * @return
   */
//	public List<Role> queryUserRole(String userid){
////		return roleMapper.queryUserRole(userid);
//	}

}
