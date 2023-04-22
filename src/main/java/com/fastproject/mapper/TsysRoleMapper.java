package com.fastproject.mapper;

import com.fastproject.model.Role;
import com.fastproject.model.auto.TsysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysRoleMapper {
    int countByExample(TsysRoleExample example);

    int deleteByExample(TsysRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(TsysRoleExample example);

    Role selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") TsysRoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") TsysRoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}