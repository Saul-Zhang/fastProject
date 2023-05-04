package com.fastproject.mapper;

import com.fastproject.model.TsysOperLog;

public interface TsysOperLogMapper {


  int deleteByPrimaryKey(String id);

  int insert(TsysOperLog record);

  int insertSelective(TsysOperLog record);


  TsysOperLog selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(TsysOperLog record);

  int updateByPrimaryKey(TsysOperLog record);
}