package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Position;
import com.fastproject.model.auto.SysPositionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位表 SysPositionMapper
 */
public interface PositionMapper extends BaseMapper<Position> {
      	   	      	      	      	      
    long countByExample(SysPositionExample example);

    int deleteByExample(SysPositionExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(Position record);

    int insertSelective(Position record);

    List<Position> selectByExample(SysPositionExample example);
		
    Position selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") Position record, @Param("example") SysPositionExample example);

    int updateByExample(@Param("record") Position record, @Param("example") SysPositionExample example);
		
    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
  	  	
}