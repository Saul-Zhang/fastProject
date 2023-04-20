package com.fastproject.mapper;

import com.fastproject.model.auto.DictType;
import com.fastproject.model.auto.TSysDictTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典类型表
 * 
 * @author 一休
 * @email 438081243@qq.com
 * @date 2019-09-05 12:34:25
 */
public interface TSysDictTypeMapper {
    long countByExample(TSysDictTypeExample example);

    int deleteByExample(TSysDictTypeExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(DictType record);

    int insertSelective(DictType record);

    List<DictType> selectByExample(TSysDictTypeExample example);
		
    DictType selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") DictType record, @Param("example") TSysDictTypeExample example);

    int updateByExample(@Param("record") DictType record, @Param("example") TSysDictTypeExample example);
		
    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
}