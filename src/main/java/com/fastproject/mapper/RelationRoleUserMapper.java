package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastproject.model.auto.RelationRoleUser;
import com.fastproject.model.auto.TSysRoleUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationRoleUserMapper extends BaseMapper<RelationRoleUser> {
    int countByExample(TSysRoleUserExample example);

    int deleteByExample(TSysRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelationRoleUser record);

    int insertSelective(RelationRoleUser record);

    List<RelationRoleUser> selectByExample(TSysRoleUserExample example);

    RelationRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelationRoleUser record, @Param("example") TSysRoleUserExample example);

    int updateByExample(@Param("record") RelationRoleUser record, @Param("example") TSysRoleUserExample example);

    int updateByPrimaryKeySelective(RelationRoleUser record);

    int updateByPrimaryKey(RelationRoleUser record);
}