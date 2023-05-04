package com.fastproject.mapper;

import com.fastproject.model.SysQuartzJobLog;

/**
 * 定时任务调度日志表 SysQuartzJobLogMapper
 *
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2019-09-13 00:03:42
 */
public interface SysQuartzJobLogMapper {


  int deleteByPrimaryKey(String id);

  int insert(SysQuartzJobLog record);

  int insertSelective(SysQuartzJobLog record);


  SysQuartzJobLog selectByPrimaryKey(String id);


  int updateByPrimaryKeySelective(SysQuartzJobLog record);

  int updateByPrimaryKey(SysQuartzJobLog record);

}