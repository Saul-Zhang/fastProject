package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DepartmentMapper;
import com.fastproject.model.Department;
import com.fastproject.model.request.query.DepartmentQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.LayUiTree;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  private final DepartmentMapper departmentMapper;

//  /**
//   * 分页查询
//   *
//   * @param pageNum
//   * @param pageSize
//   * @return
//   */
//  public PageInfo<Department> list(Tablepar tablepar, String name) {
//    SysDepartmentExample testExample = new SysDepartmentExample();
//    testExample.setOrderByClause("id ASC");
//    if (name != null && !"".equals(name)) {
//      testExample.createCriteria().andDeptNameLike("%" + name + "%");
//    }
//    if (StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
//      testExample.setOrderByClause(
//          StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) + " " + tablepar.getIsAsc());
//    }
//    List<Department> list = departmentMapper.selectByExample(testExample);
//    PageInfo<Department> pageInfo = new PageInfo<Department>(list);
//    return pageInfo;
//  }

  public List<Department> getAll() {
    return departmentMapper.selectList(
        new LambdaQueryWrapperX<Department>().eq(Department::getStatus, 1));
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

//  @Override
//  public int deleteByPrimaryKey(String ids) {
//
//    Integer[] integers = ConvertUtil.toIntArray(",", ids);
//    List<Integer> stringB = Arrays.asList(integers);
//    SysDepartmentExample example = new SysDepartmentExample();
//    example.createCriteria().andIdIn(stringB);
//    return departmentMapper.deleteByExample(example);
//
//  }

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
//
//  @Override
//  public int updateByExampleSelective(Department record, SysDepartmentExample example) {
//
//    return departmentMapper.updateByExampleSelective(record, example);
//  }
//
//  @Override
//  public int updateByExample(Department record, SysDepartmentExample example) {
//
//    return departmentMapper.updateByExample(record, example);
//  }

  public List<Department> selectAll() {

    return departmentMapper.selectList(null);
  }

//  @Override
//  public long countByExample(SysDepartmentExample example) {
//
//    return departmentMapper.countByExample(example);
//  }
//

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

}
