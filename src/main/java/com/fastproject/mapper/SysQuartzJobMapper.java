package com.fastproject.mapper;

import com.fastproject.model.SysQuartzJob;

/**
 * 定时任务调度表 SysQuartzJobMapper
 *
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2019-09-13 00:03:35
 */
public interface SysQuartzJobMapper {


  int deleteByPrimaryKey(String id);

  int insert(SysQuartzJob record);

  int insertSelective(SysQuartzJob record);


  SysQuartzJob selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(SysQuartzJob record);

  int updateByPrimaryKey(SysQuartzJob record);

}