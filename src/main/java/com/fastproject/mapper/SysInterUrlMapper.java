package com.fastproject.mapper;

import com.fastproject.model.SysInterUrl;
import com.fastproject.model.auto.SysInterUrlExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拦截url表 SysInterUrlMapper
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2020-01-05 01:48:22
 */
//@Mapper
public interface SysInterUrlMapper {
      	   	      	      	      	      
    long countByExample(SysInterUrlExample example);

    int deleteByExample(SysInterUrlExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(SysInterUrl record);

    int insertSelective(SysInterUrl record);

    List<SysInterUrl> selectByExample(SysInterUrlExample example);
		
    SysInterUrl selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") SysInterUrl record, @Param("example") SysInterUrlExample example);

    int updateByExample(@Param("record") SysInterUrl record, @Param("example") SysInterUrlExample example); 
		
    int updateByPrimaryKeySelective(SysInterUrl record);

    int updateByPrimaryKey(SysInterUrl record);
  	  	
}