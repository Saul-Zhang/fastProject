package com.fastproject.mapper;

import com.fastproject.model.auto.Department;
import com.fastproject.model.auto.SysDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表 SysDepartmentMapper
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2020-04-17 13:12:58
 */
public interface SysDepartmentMapper {
      	   	      	      	      	      	      	      	      	      
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