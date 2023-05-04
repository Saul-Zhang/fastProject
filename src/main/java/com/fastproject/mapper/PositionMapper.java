package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Position;

/**
 * 岗位表 SysPositionMapper
 */
public interface PositionMapper extends BaseMapper<Position> {


  int deleteByPrimaryKey(String id);

  int insert(Position record);

  int insertSelective(Position record);


  Position selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(Position record);

  int updateByPrimaryKey(Position record);

}