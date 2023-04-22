package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.auto.TSysDictDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典数据表
 */
public interface DictDataMapper extends BaseMapper<DictData> {
    long countByExample(TSysDictDataExample example);

    int deleteByExample(TSysDictDataExample example);
		
    int insert(DictData record);

    int insertSelective(DictData record);

    List<DictData> selectByExample(TSysDictDataExample example);
		
    int updateByExampleSelective(@Param("record") DictData record, @Param("example") TSysDictDataExample example);

    int updateByExample(@Param("record") DictData record, @Param("example") TSysDictDataExample example);

    DictData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DictData record);
}