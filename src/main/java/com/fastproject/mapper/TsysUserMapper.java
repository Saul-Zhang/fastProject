package com.fastproject.mapper;

import com.fastproject.model.auto.User;
import com.fastproject.model.auto.TsysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysUserMapper {
    long countByExample(TsysUserExample example);

    int deleteByExample(TsysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(TsysUserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") TsysUserExample example);

    int updateByExample(@Param("record") User record, @Param("example") TsysUserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}