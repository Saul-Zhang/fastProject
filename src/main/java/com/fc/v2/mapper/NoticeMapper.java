package com.fc.v2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.fc.v2.model.auto.Notice;
import com.fc.v2.model.auto.SysNoticeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NoticeMapper extends BaseMapper<Notice> {
      	      	   	      	   	      	   	      	   	      	   	      	   	      
    long countByExample(SysNoticeExample example);

    int deleteByExample(SysNoticeExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(SysNoticeExample example);
		
    Notice selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") Notice record, @Param("example") SysNoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") SysNoticeExample example);
		
    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
  	  	
}