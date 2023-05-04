package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.DictData;

/**
 * 字典数据表
 */
public interface DictDataMapper extends BaseMapper<DictData> {

  int insert(DictData record);

  int insertSelective(DictData record);


  DictData selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(DictData record);
}