package com.fastproject.mapper;

import com.fastproject.model.TSysEmail;

public interface TSysEmailMapper {


  int deleteByPrimaryKey(String id);

  int insert(TSysEmail record);

  int insertSelective(TSysEmail record);


  TSysEmail selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(TSysEmail record);

  int updateByPrimaryKey(TSysEmail record);
}