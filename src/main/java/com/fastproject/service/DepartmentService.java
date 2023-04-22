package com.fastproject.service;

import cn.hutool.core.util.StrUtil;
import com.fastproject.common.base.BaseService;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.DepartmentMapper;
import com.fastproject.model.Department;
import com.fastproject.model.auto.SysDepartmentExample;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageInfo;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService implements BaseService<Department, SysDepartmentExample> {

  private final DepartmentMapper departmentMapper;

  /**
   * 分页查询
   *
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Department> list(Tablepar tablepar, String name) {
    SysDepartmentExample testExample = new SysDepartmentExample();
    testExample.setOrderByClause("id ASC");
    if (name != null && !"".equals(name)) {
      testExample.createCriteria().andDeptNameLike("%" + name + "%");
    }
    if (StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
      testExample.setOrderByClause(
          StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) + " " + tablepar.getIsAsc());
    }
    List<Department> list = departmentMapper.selectByExample(testExample);
    PageInfo<Department> pageInfo = new PageInfo<Department>(list);
    return pageInfo;
  }

  public List<Department> getAll() {
    return departmentMapper.selectList(
        new LambdaQueryWrapperX<Department>().eq(Department::getStatus, 1));
  }

  @Override
  public int deleteByPrimaryKey(String ids) {

    Integer[] integers = ConvertUtil.toIntArray(",", ids);
    List<Integer> stringB = Arrays.asList(integers);
    SysDepartmentExample example = new SysDepartmentExample();
    example.createCriteria().andIdIn(stringB);
    return departmentMapper.deleteByExample(example);

  }

  @Override
  public Department selectByPrimaryKey(String id) {

    Integer id1 = Integer.valueOf(id);
    return departmentMapper.selectByPrimaryKey(id1);

  }

  @Override
  public int updateByPrimaryKeySelective(Department record) {
    return departmentMapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 添加
   */
  @Override
  public int insertSelective(Department record) {

    record.setId(null);

    return departmentMapper.insertSelective(record);
  }

  @Override
  public int updateByExampleSelective(Department record, SysDepartmentExample example) {

    return departmentMapper.updateByExampleSelective(record, example);
  }

  @Override
  public int updateByExample(Department record, SysDepartmentExample example) {

    return departmentMapper.updateByExample(record, example);
  }

  @Override
  public List<Department> selectByExample(SysDepartmentExample example) {

    return departmentMapper.selectByExample(example);
  }

  @Override
  public long countByExample(SysDepartmentExample example) {

    return departmentMapper.countByExample(example);
  }

  @Override
  public int deleteByExample(SysDepartmentExample example) {

    return departmentMapper.deleteByExample(example);
  }

  /**
   * 检查name
   *
   * @param department
   * @return
   */
  public int checkNameUnique(Department department) {
    SysDepartmentExample example = new SysDepartmentExample();
    example.createCriteria().andDeptNameEqualTo(department.getName());
    List<Department> list = departmentMapper.selectByExample(example);
    return list.size();
  }

  /**
   * 修改权限状态展示或者不展示
   *
   * @param record
   * @return
   */
  public int updateVisible(Department record) {
    return departmentMapper.updateByPrimaryKeySelective(record);
  }

}
