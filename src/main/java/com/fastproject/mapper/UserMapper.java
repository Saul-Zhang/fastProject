package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.model.auto.User;
import com.fastproject.model.response.UserResponse;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fastProject
 * @date 2023/4/11 17:03
 */
public interface UserMapper extends BaseMapper<User> {


  @Select("select u.*, r.name as roleName , p.name as posName, d.name as deptName \n"
      + "from def_user u\n"
      + "         left join rel_role_user ru on u.id = ru.user_id\n"
      + "         left join def_role r on ru.role_id = r.id\n"
      + "         left join def_position p on u.pos_id = p.id "
      + "         left join def_department d on u.dept_id = d.id "
      + " ${ew.customSqlSegment}")
  List<UserResponse> getUsers(@Param(Constants.WRAPPER) LambdaQueryWrapperX<User> queryWrapper);
}
