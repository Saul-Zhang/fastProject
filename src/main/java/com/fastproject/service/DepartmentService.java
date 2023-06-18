package com.fastproject.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fastproject.mapper.DepartmentMapper;
import com.fastproject.mapper.RelationDepartmentUserMapper;
import com.fastproject.model.Department;
import com.fastproject.model.RelationDepartmentUser;
import com.fastproject.model.request.query.DepartmentQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.LayUiTree;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  private final DepartmentMapper departmentMapper;
  private final RelationDepartmentUserMapper departmentUserMapper;


  public List<Department> getAll() {
    return departmentMapper.selectList(null);
  }

  public Set<Long> getDepartmentIdByUserId(Long userId) {
    if (userId == null) {
      return new HashSet<>();
    }
    return departmentUserMapper.selectList(new LambdaQueryWrapper<RelationDepartmentUser>()
        .select(RelationDepartmentUser::getDepartmentId)
        .eq(RelationDepartmentUser::getUserId, userId)).stream().map(
        RelationDepartmentUser::getDepartmentId).collect(Collectors.toSet());
  }

  public List<LayUiTree> getAllTree() {
    return departmentMapper.selectList(
            new LambdaQueryWrapper<Department>()
                .select(Department::getId, Department::getParentId, Department::getName))
        .stream().map(department -> {
          LayUiTree tree = new LayUiTree();
          tree.setId(department.getId());
          tree.setParentId(department.getParentId());
          tree.setTitle(department.getName());
          return tree;
        }).collect(Collectors.toList());
  }

  public List<LayUiTree> getTreeByUser(Long userId) {
    Set<Long> departmentIds = getDepartmentIdByUserId(userId);
    return departmentMapper.selectList(
            new LambdaQueryWrapper<Department>()
                .select(Department::getId, Department::getParentId, Department::getName))
        .stream().map(department -> {
          LayUiTree tree = new LayUiTree();
          tree.setId(department.getId());
          tree.setParentId(department.getParentId());
          tree.setTitle(department.getName());
//          if (departmentIds.contains(department.getId())) {
//            tree.setCheckArr("1");
//          }
          return tree;
        }).collect(Collectors.toList());
  }


  public List<LayUiTree> list(DepartmentQuery query) {
    return departmentMapper.selectList(null).stream()
        .map(department -> {
          LayUiTree tree = new LayUiTree();
          tree.setId(department.getId());
          tree.setTitle(department.getName());
          tree.setParentId(department.getParentId());
          return tree;
        }).collect(Collectors.toList());
  }

  public Department selectById(Long id) {
    return departmentMapper.selectById(id);
  }

  public AjaxResult updateById(Department record) {
    departmentMapper.updateById(record);
    return AjaxResult.success();
  }


  /**
   * 添加
   */
  public AjaxResult insert(Department record) {
    departmentMapper.insert(record);
    return AjaxResult.success();
  }

  public List<Department> selectAll() {

    return departmentMapper.selectList(null);
  }

  public AjaxResult deleteByIds(List<Long> ids) {
    departmentMapper.deleteBatchIds(ids);
    return AjaxResult.success();
  }

  /**
   * 修改权限状态展示或者不展示
   */
  public AjaxResult updateStatus(Long id, Character status) {
    Department entity = new Department();
    entity.setStatus(status);
    entity.setId(id);
    departmentMapper.updateById(entity);
    return AjaxResult.success();
  }

  public int deleteRelDeptUser(List<Long> userId) {
    return departmentUserMapper.delete(
        new LambdaQueryWrapper<RelationDepartmentUser>().in(RelationDepartmentUser::getUserId,
            userId));
  }

  public void insertRelDeptUser(Long userId, List<Long> deptIds) {
    deptIds.forEach(deptId -> {
      departmentUserMapper.insert(
          RelationDepartmentUser.builder().userId(userId).departmentId(deptId).build());
    });
  }
}
