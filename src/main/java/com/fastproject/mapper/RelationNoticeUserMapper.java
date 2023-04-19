package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.auto.RelationNoticeUser;
import com.fastproject.model.auto.SysNoticeUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RelationNoticeUserMapper extends BaseMapper<RelationNoticeUser> {
      	      	   	      	   	      	   	      
    long countByExample(SysNoticeUserExample example);

    int deleteByExample(SysNoticeUserExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(RelationNoticeUser record);

    int insertSelective(RelationNoticeUser record);

    List<RelationNoticeUser> selectByExample(SysNoticeUserExample example);
		
    RelationNoticeUser selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") RelationNoticeUser record, @Param("example") SysNoticeUserExample example);

    int updateByExample(@Param("record") RelationNoticeUser record, @Param("example") SysNoticeUserExample example);
		
    int updateByPrimaryKeySelective(RelationNoticeUser record);

    int updateByPrimaryKey(RelationNoticeUser record);
  	  	
}