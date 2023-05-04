package com.fastproject.mapper;

import com.fastproject.model.DictType;

/**
 * 字典类型表
 *
 * @author 一休
 * @email 438081243@qq.com
 * @date 2019-09-05 12:34:25
 */
public interface TSysDictTypeMapper {

  int deleteByPrimaryKey(String id);

  int insert(DictType record);

  int insertSelective(DictType record);


  DictType selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(DictType record);

  int updateByPrimaryKey(DictType record);
}