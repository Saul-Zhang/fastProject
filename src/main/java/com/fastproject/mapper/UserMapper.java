package com.fastproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fastproject.common.mybatis.QueryWrapperX;
import com.fastproject.model.User;
import com.fastproject.model.custom.UserRoleVo;
import com.fastproject.model.request.query.UserQuery;
import com.fastproject.model.response.UserResponse;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fastProject
 * @date 2023/4/11 17:03
 */
public interface UserMapper extends BaseMapper<User> {


  @Select("SELECT u.*,\n"
      + "       p.NAME AS posName,\n"
      + "       d.NAME AS deptName\n"
      + "FROM def_user u \n"
      + "         LEFT JOIN def_position p ON u.pos_id = p.id\n"
      + "         LEFT JOIN def_department d ON u.dept_id = d.id "
      + " ${ew.customSqlSegment}")
  List<UserResponse> getUsers(@Param(Constants.WRAPPER) QueryWrapperX<User> queryWrapper);

  List<UserResponse> getAll(@Param("e") UserQuery query);

  UserResponse selectUserResponseById(Long id);

  List<UserRoleVo> getAllUserRole();
}
