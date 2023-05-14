package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.DictType;

/**
 * 字典类型表
 */
public interface DictTypeMapper extends BaseMapper<DictType> {

  int deleteByPrimaryKey(String id);

  int insert(DictType record);

  int insertSelective(DictType record);


  DictType selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(DictType record);

  int updateByPrimaryKey(DictType record);
}