package com.fc.v2.mapper;

import com.fc.v2.model.auto.RelationNoticeUser;
import com.fc.v2.model.auto.SysNoticeUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告_用户外键 SysNoticeUserMapper
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2019-09-08 02:12:54
 */
public interface SysNoticeUserMapper {
      	      	   	      	   	      	   	      
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