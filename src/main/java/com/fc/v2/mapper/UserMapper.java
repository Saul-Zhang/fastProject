package com.fc.v2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fc.v2.common.mybatis.LambdaQueryWrapperX;
import com.fc.v2.model.auto.User;
import com.fc.v2.model.response.UserResponse;
import com.github.pagehelper.Page;
import java.sql.Wrapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fastProject
 * @date 2023/4/11 17:03
 */
public interface UserMapper extends BaseMapper<User> {


  @Select("select u.*, r.name, p.name\n"
      + "from def_user u\n"
      + "         left join rel_role_user ru on u.id = ru.user_id\n"
      + "         left join def_role r on ru.role_id = r.id\n"
      + "         left join def_position p on u.pos_id = p.id"
      + "${ew.customSqlSegment}")
  Page<UserResponse> getUsers(Page<UserResponse> page,
      @Param(Constants.WRAPPER) LambdaQueryWrapperX<User> queryWrapper);
}
