package com.fastproject.service;

import static com.fastproject.model.request.body.UserBody.userBody2User;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fastproject.common.conf.FastProperties;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.RelationRoleUserMapper;
import com.fastproject.mapper.RoleMapper;
import com.fastproject.mapper.UserMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.RelationRoleUser;
import com.fastproject.model.User;
import com.fastproject.model.constant.Status;
import com.fastproject.model.custom.RoleVo;
import com.fastproject.model.request.body.UserBody;
import com.fastproject.model.request.query.UserQuery;
import com.fastproject.model.response.AjaxResult;
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
import java.util.Collections;
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

  private final DepartmentService departmentService;

  private final DictDataService dictDataService;

  /**
   * 分页查询
   */
  public PageInfo<UserResponse> list(UserQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    return new PageInfo<>(userMapper.getAll(query));
  }


  @Transactional(rollbackFor = Exception.class)
  public AjaxResult updateStatus(List<Long> userIds, Character status) {
    userIds.forEach(id -> {
      User entity = new User();
      entity.setStatus(status);
      entity.setId(id);
      userMapper.updateById(entity);
    });
    return AjaxResult.success();
  }

  /**
   * 添加用户跟角色信息
   */
  @Transactional(rollbackFor = Exception.class)
  public AjaxResult add(User record, String roleIds) {
    Long userId = SnowflakeIdWorker.getId();
    record.setId(userId);
    //密码加密
    record.setPassword(MD5Util.encode(record.getPassword()));
    record.setStatus(Status.ENABLE);
    userMapper.insert(record);

    DictData dictData = new DictData();
    dictData.setCode("user");
    dictData.setLabel(record.getRealName());
    dictData.setValue(String.valueOf(userId));
    dictDataService.insert(dictData);

    if (StringUtils.isNotEmpty(roleIds)) {
      List<Long> roleIdList = ConvertUtil.toListLongArray(roleIds);
      for (Long rolesId : roleIdList) {
        RelationRoleUser roleUser = new RelationRoleUser(SnowflakeIdWorker.getId(),
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
   * 获取所有权限 并且增加是否有权限字段
   */
  public List<RoleVo> getRolesByUserId(Long userId) {
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

  public UserResponse getUserById(Long userId) {
    UserResponse userResponse = userMapper.selectById(userId);
    userResponse.setDepartmentIds(
        StringUtils.join(departmentService.getDepartmentIdByUserId(userId), ","));
    return userResponse;
  }

  /**
   * 修改用户密码
   */
  public AjaxResult updateUserPassword(User record) {
    record.setPassword(MD5Util.encode(record.getPassword()));
    //修改用户信息
    userMapper.updateById(record);
    return AjaxResult.success();
  }


  @Transactional(rollbackFor = Exception.class)
  public AjaxResult update(UserBody body) {
    //先删除这个用户的所有角色
    roleUserMapper.delete(new LambdaQueryWrapperX<RelationRoleUser>()
        .inIfPresent(RelationRoleUser::getRoleId, body.getRoleIds())
        .eq(RelationRoleUser::getUserId, body.getId()));
    //添加新的角色信息
    if (CollectionUtils.isNotEmpty(body.getRoleIds())) {
      for (Long roleId : body.getRoleIds()) {
        RelationRoleUser roleUser = new RelationRoleUser();
        roleUser.setId(SnowflakeIdWorker.getId());
        roleUser.setUserId(body.getId());
        roleUser.setRoleId(roleId);
        roleUserMapper.insert(roleUser);
      }
    }

    // 清除此用户角色信息缓存
    StpUtil.getSessionByLoginId(body.getId()).delete("Role_List");

    //修改用户信息
    userMapper.updateById(userBody2User(body));

    // 更新用户部门信息
    departmentService.deleteRelDeptUser(Collections.singletonList(body.getId()));
    departmentService.insertRelDeptUser(body.getId(), body.getDepartmentIds());
    return AjaxResult.success();
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
    if (queryUser.getStatus().equals(Status.DISABLE)) {
      return AjaxResult.error(500, "当前用户不存在");
    }

    // 校验通过，开始登录
    StpUtil.login(queryUser.getId(), rememberMe);
    SaTokenUtil.setUser(queryUser);
    StpUtil.getTokenSession().set("ip", ServletUtils.getIP(request));
    return AjaxResult.success().put("tokenInfo", StpUtil.getTokenInfo());
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult deleteByIds(List<Long> userIds) {
    userMapper.deleteBatchIds(userIds);
    roleUserMapper.delete(
        new LambdaQueryWrapperX<RelationRoleUser>().in(RelationRoleUser::getUserId, userIds));
    departmentService.deleteRelDeptUser(userIds);
    return AjaxResult.success();
  }
}
