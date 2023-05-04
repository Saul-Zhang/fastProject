package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.RelationNoticeUser;


public interface RelationNoticeUserMapper extends BaseMapper<RelationNoticeUser> {


  int deleteByPrimaryKey(String id);

  int insert(RelationNoticeUser record);

  int insertSelective(RelationNoticeUser record);


  RelationNoticeUser selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(RelationNoticeUser record);

  int updateByPrimaryKey(RelationNoticeUser record);

}