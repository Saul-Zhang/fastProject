package com.fc.v2.mapper;

import com.fc.v2.model.auto.Permission;
import com.fc.v2.model.auto.TsysPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysPermissionMapper {
    long countByExample(TsysPermissionExample example);

    int deleteByExample(TsysPermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(TsysPermissionExample example);

    Permission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") TsysPermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") TsysPermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}