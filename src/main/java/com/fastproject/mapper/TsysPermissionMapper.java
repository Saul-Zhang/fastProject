package com.fastproject.mapper;

import com.fastproject.model.Permission;

public interface TsysPermissionMapper {

  int deleteByPrimaryKey(String id);

  int insert(Permission record);

  int insertSelective(Permission record);


  Permission selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(Permission record);

  int updateByPrimaryKey(Permission record);
}