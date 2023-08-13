package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.RelationRoleUserMapper;
import com.fastproject.mapper.RoleMapper;
import com.fastproject.model.RelationRoleUser;
import com.fastproject.model.Role;
import com.fastproject.model.constant.RoleCode;
import com.fastproject.model.constant.Status;
import com.fastproject.model.request.query.RoleQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleMapper roleMapper;
  private final RelationRoleUserMapper roleUserMapper;


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


  @Transactional
  public AjaxResult updateStatus(List<Long> roleIds, Character status) {
    roleIds.forEach(id -> {
      Role entity = new Role();
      entity.setStatus(status);
      entity.setId(id);
      roleMapper.updateById(entity);
    });
    return AjaxResult.success();
  }

  public AjaxResult insert(Role record) {
    record.setId(SnowflakeIdWorker.getId());
    record.setStatus(Status.ENABLE);
    roleMapper.insert(record);
    return AjaxResult.success();
  }

  public Role selectById(Long id) {
    return roleMapper.selectById(id);
  }


  public AjaxResult updateById(Role record) {
    roleMapper.updateById(record);
    return AjaxResult.success();
  }

  public AjaxResult deleteByIds(List<Long> ids) {
    if (!roleUserMapper.selectList(
            new LambdaQueryWrapperX<RelationRoleUser>().in(RelationRoleUser::getRoleId, ids))
        .isEmpty()) {
      return AjaxResult.error(500, "当前角色有关联的用户，无法删除");
    }
    roleMapper.deleteBatchIds(ids);
    return AjaxResult.success();
  }

  public boolean isAdmin(Long userId) {
    List<Long> roleIds = roleUserMapper.selectList(new LambdaQueryWrapperX<RelationRoleUser>()
            .eq(RelationRoleUser::getUserId, userId)).stream()
        .map(RelationRoleUser::getRoleId).collect(Collectors.toList());
    return roleMapper.selectList(new LambdaQueryWrapperX<Role>()
            .inIfPresent(Role::getId, roleIds)).stream()
        .anyMatch(role -> RoleCode.ADMIN.equals(role.getCode()));
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
