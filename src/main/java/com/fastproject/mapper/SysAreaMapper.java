package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.auto.SysArea;
import com.fastproject.model.auto.SysAreaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaMapper extends BaseMapper<SysArea> {
    long countByExample(SysAreaExample example);

    int deleteByExample(SysAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    List<SysArea> selectByExample(SysAreaExample example);

    SysArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysArea record, @Param("example") SysAreaExample example);

    int updateByExample(@Param("record") SysArea record, @Param("example") SysAreaExample example);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);
}