package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Department;
import com.fastproject.model.auto.SysDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表
 */
public interface DepartmentMapper extends BaseMapper<Department> {
      	   	      	      	      	      	      	      	      	      
    long countByExample(SysDepartmentExample example);

    int deleteByExample(SysDepartmentExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(SysDepartmentExample example);
		
    Department selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") Department record, @Param("example") SysDepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") SysDepartmentExample example);
		
    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
  	  	
}