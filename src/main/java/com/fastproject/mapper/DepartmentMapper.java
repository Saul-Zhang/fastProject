package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Department;

/**
 * 部门表
 */
public interface DepartmentMapper extends BaseMapper<Department> {


  int deleteByPrimaryKey(Integer id);

  int insert(Department record);

  int insertSelective(Department record);


  Department selectByPrimaryKey(Integer id);


  int updateByPrimaryKeySelective(Department record);

  int updateByPrimaryKey(Department record);

}