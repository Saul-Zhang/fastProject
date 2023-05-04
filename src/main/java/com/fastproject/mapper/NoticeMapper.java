package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.Notice;


public interface NoticeMapper extends BaseMapper<Notice> {


  int deleteByPrimaryKey(String id);

  int insert(Notice record);

  int insertSelective(Notice record);


  Notice selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(Notice record);

  int updateByPrimaryKey(Notice record);

}