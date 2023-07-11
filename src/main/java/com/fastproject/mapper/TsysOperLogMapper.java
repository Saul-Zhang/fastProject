package com.fastproject.mapper;

import com.fastproject.model.OperationLog;

public interface TsysOperLogMapper {


  int deleteByPrimaryKey(String id);

  int insert(OperationLog record);

  int insertSelective(OperationLog record);


  OperationLog selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(OperationLog record);

  int updateByPrimaryKey(OperationLog record);
}