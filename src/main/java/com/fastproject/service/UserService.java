package com.fastproject.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fastproject.common.conf.FastProperties;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.mybatis.QueryWrapperX;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.RelationRoleUserMapper;
import com.fastproject.mapper.RoleMapper;
import com.fastproject.mapper.UserMapper;
import com.fastproject.model.RelationRoleUser;
import com.fastproject.model.User;
import com.fastproject.model.constant.Status;
import com.fastproject.model.custom.RoleVo;
import com.fastproject.model.request.query.UserQuery;
import com.fastproject.model.response.UserResponse;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.MD5Util;
import com.fastproject.util.ServletUtils;
import com.fastproject.util.SnowflakeIdWorker;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  private final FastProperties properties;

  private final RelationRoleUserMapper roleUserMapper;

  private final RoleMapper roleMapper;

  /**
   * 分页查询
   */
  public PageInfo<UserResponse> list(UserQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    QueryWrapperX<User> queryWrapperX = new QueryWrapperX<User>()
        .likeIfPresent("employee_id", query.getEmployeeId())
        .likeIfPresent("real_name", query.getRealName())
        .eqIfPresent("u.status", query.getStatus());
    return new PageInfo<>(userMapper.getUsers(queryWrapperX));
  }


  @Transactional
  public AjaxResult deleteByIds(String ids) {
    List<String> userIds = ConvertUtil.toListStrArray(ids);

    userIds.forEach(id -> {
          User user = new User();
          user.setId(id);
          user.setStatus(Status.DISABLE);
          userMapper.updateById(user);
        }
    );
      roleUserMapper.delete(
          new LambdaQueryWrapperX<RelationRoleUser>().in(RelationRoleUser::getUserId, userIds));
    return AjaxResult.success();

  }

  @Transactional
  public AjaxResult updateStatus(List<String> userIds, Character status) {
    userIds.forEach(id -> {
      User entity = new User();
      entity.setStatus(status);
      entity.setId(id);
      userMapper.updateById(entity);
    });
//    List<String> userIds = ConvertUtil.toListStrArray(ids);
//    userMapper.deleteBatchIds(userIds);
//    if (i > 0) {
//      roleUserMapper.delete(
//          new LambdaQueryWrapperX<RelationRoleUser>().in(RelationRoleUser::getUserId, userIds));
//    }
    return AjaxResult.success();

  }

  /**
   * 添加用户跟角色信息
   */
  @Transactional(rollbackFor = Exception.class)
  public AjaxResult add(User record, String roleIds) {
    String userid = SnowflakeIdWorker.getUUID();
    record.setId(userid);
    //密码加密
    record.setPassword(MD5Util.encode(record.getPassword()));
    userMapper.insert(record);
    if (StringUtils.isNotEmpty(roleIds)) {
      List<String> roleIdList = ConvertUtil.toListStrArray(roleIds);
      for (String rolesId : roleIdList) {
        RelationRoleUser roleUser = new RelationRoleUser(SnowflakeIdWorker.getUUID(),
            record.getId(), rolesId);
        roleUserMapper.insert(roleUser);
      }
    }

    return AjaxResult.success();
  }

  public List<User> getAll() {
    return userMapper.selectList(new LambdaQueryWrapperX<User>()
        .eq(User::getStatus, Status.ENABLE));
  }

  /**
   * 检查用户name
   */
  public int checkLoginNameUnique(String username) {

    return userMapper.selectOne(new LambdaQueryWrapperX<User>()
        .eq(User::getUsername, username)) == null ? 0 : 1;
  }

  /**
   * 获取所有权限 并且增加是否有权限字段
   */
  public List<RoleVo> getRolesByUserId(String userId) {
    List<RoleVo> list = new ArrayList<>();
    roleMapper.selectList(null).forEach(
        role -> {
          RelationRoleUser roleUser = roleUserMapper.selectOne(
              new LambdaQueryWrapperX<RelationRoleUser>()
                  .eq(RelationRoleUser::getUserId, userId)
                  .eq(RelationRoleUser::getRoleId, role.getId()));
          list.add(new RoleVo(role.getId(), role.getName(), roleUser != null));
        });

    return list;
  }

  public User getUserById(String userId) {
    return userMapper.selectById(userId);
  }


  /**
   * 修改用户密码
   */
  public int updateUserPassword(User record) {
    record.setPassword(MD5Util.encode(record.getPassword()));
    //修改用户信息
    return userMapper.updateById(record);
  }


  /**
   * 修改用户信息以及角色信息
   */
  @Transactional(rollbackFor = Exception.class)
  public int updateUserRoles(User record, List<String> roleIds) {
    //先删除这个用户的所有角色
    roleUserMapper.delete(new LambdaQueryWrapperX<RelationRoleUser>()
        .inIfPresent(RelationRoleUser::getRoleId, roleIds)
        .eq(RelationRoleUser::getUserId, record.getId()));
    //添加新的角色信息
    if (CollectionUtils.isNotEmpty(roleIds)) {
      for (String roleId : roleIds) {
        RelationRoleUser roleUser = new RelationRoleUser();
        roleUser.setId(SnowflakeIdWorker.getUUID());
        roleUser.setUserId(record.getId());
        roleUser.setRoleId(roleId);
        roleUserMapper.insert(roleUser);
      }
    }
    // 清除此用户角色信息缓存
    StpUtil.getSessionByLoginId(record.getId()).delete("Role_List");

    //修改用户信息
    return userMapper.updateById(record);
  }


  public AjaxResult login(User user, String captcha, boolean rememberMe,
      HttpServletRequest request) {
    if (properties.getCaptchaEnabled()) {
      // 获取session中的验证码
      String verCode = (String) request.getSession().getAttribute("captcha");
      // 判断验证码
      if (captcha != null && captcha.equalsIgnoreCase(verCode.trim())) {
        // 清除session中的验证码
        CaptchaUtil.clear(request);
      } else {
        return AjaxResult.error(500, "验证码不正确!");
      }
    }

    String userName = user.getUsername();
    User queryUser = userMapper.selectOne(
        new QueryWrapper<User>().lambda().eq(User::getUsername, userName));
    if (queryUser == null || !SaSecureUtil.md5(user.getPassword())
        .equals(queryUser.getPassword())) {
      return AjaxResult.error(500, "用户名或密码不正确");
    }

    // 校验通过，开始登录
    StpUtil.login(queryUser.getId(), rememberMe);
    SaTokenUtil.setUser(queryUser);
    StpUtil.getTokenSession().set("ip", ServletUtils.getIP(request));
    return AjaxResult.success().put("tokenInfo", StpUtil.getTokenInfo());
  }
}
