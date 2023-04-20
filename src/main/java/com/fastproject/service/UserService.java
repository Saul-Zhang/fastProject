package com.fastproject.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastproject.common.base.BaseService;
import com.fastproject.common.conf.FastProperties;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.GeneratorMapper.TsysUserMapper;
import com.fastproject.mapper.RelationRoleUserMapper;
import com.fastproject.mapper.RoleMapper;
import com.fastproject.mapper.TsysRoleMapper;
import com.fastproject.mapper.UserMapper;
import com.fastproject.model.auto.TSysRoleUserExample;
import com.fastproject.model.auto.TsysRole;
import com.fastproject.model.auto.TsysRoleExample;
import com.fastproject.model.auto.TsysUserExample;
import com.fastproject.model.auto.User;
import com.fastproject.model.custom.RoleVo;
import com.fastproject.model.request.query.UserQuery;
import com.fastproject.model.response.UserResponse;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.MD5Util;
import com.fastproject.util.ServletUtils;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements BaseService<User, TsysUserExample> {

  private final UserMapper userMapper;

  private final FastProperties properties;
  //生成的用户dao
  @Autowired
  private com.fastproject.mapper.TsysUserMapper tsysUserMapper;

  //生成的角色用户dao
  @Autowired
  private RelationRoleUserMapper relationRoleUserMapper;

  //自定义角色dao
  @Autowired
  private RoleMapper roleMapper;

  //自动生成的角色dao
  @Autowired
  private TsysRoleMapper tsysRoleMapper;

  //自定义用户dao
  @Autowired
  private TsysUserMapper userDao;

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * 分页查询
   */
  public PageInfo<UserResponse> list(UserQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    LambdaQueryWrapperX<User> queryWrapperX = new LambdaQueryWrapperX<User>()
        .eqIfPresent(User::getDepId, query.getDeptId())
        .eqIfPresent(User::getId, query.getId());
    UserResponse userResponse = new UserResponse();
    userResponse.setGender("1");
    try {
      System.out.println(objectMapper.writeValueAsString(userResponse));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new PageInfo<>(userMapper.getUsers(queryWrapperX));
  }


  @Override
  @Transactional
  public int deleteByPrimaryKey(String ids) {
    List<String> lista = ConvertUtil.toListStrArray(ids);
    TsysUserExample example = new TsysUserExample();
    example.createCriteria().andIdIn(lista);
    int i = tsysUserMapper.deleteByExample(example);
    if (i > 0) {
      TSysRoleUserExample tSysRoleUserExample = new TSysRoleUserExample();
      tSysRoleUserExample.createCriteria().andSysUserIdIn(lista);
      relationRoleUserMapper.deleteByExample(tSysRoleUserExample);
    }
    return i;

  }

  /**
   * 添加用户
   */
  @Override
  public int insertSelective(User record) {
    return tsysUserMapper.insertSelective(record);
  }

  /**
   * 添加用户跟角色信息
   *
   * @param record
   * @param roles
   * @return
   */
  @Transactional
  public int insertUserRoles(User record, String roles) {
//    String userid = SnowflakeIdWorker.getUUID();
//    record.setId(userid);
    //密码加密
    record.setPassword(MD5Util.encode(record.getPassword()));
    tsysUserMapper.insertSelective(record);
    if (StringUtils.isNotEmpty(roles)) {
      List<String> list_roles = ConvertUtil.toListStrArray(roles);
      for (String rolesid : list_roles) {
//        RelationRoleUser roleUser = new RelationRoleUser(SnowflakeIdWorker.getUUID(), record.getId(),
//            rolesid);
//        relationRoleUserMapper.insertSelective(roleUser);
      }
    }


    return 1;
  }

  @Override
  public User selectByPrimaryKey(String id) {

    return tsysUserMapper.selectByPrimaryKey(id);
  }


  @Override
  public int updateByPrimaryKeySelective(User record) {
    record.setPassword(MD5Util.encode(record.getPassword()));
    return tsysUserMapper.updateByPrimaryKeySelective(record);
  }


  @Override
  public int updateByExampleSelective(User record, TsysUserExample example) {

    return tsysUserMapper.updateByExampleSelective(record, example);
  }


  @Override
  public int updateByExample(User record, TsysUserExample example) {

    return tsysUserMapper.updateByExample(record, example);
  }

  @Override
  public List<User> selectByExample(TsysUserExample example) {

    return tsysUserMapper.selectByExample(example);
  }


  @Override
  public long countByExample(TsysUserExample example) {

    return tsysUserMapper.countByExample(example);
  }


  @Override
  public int deleteByExample(TsysUserExample example) {

    return tsysUserMapper.deleteByExample(example);
  }

  /**
   * 检查用户name
   *
   * @param user
   * @return
   */
  public int checkLoginNameUnique(User user) {
    TsysUserExample example = new TsysUserExample();
    example.createCriteria().andUsernameEqualTo(user.getUsername());
    List<User> list = tsysUserMapper.selectByExample(example);

    return list.size();
  }

  /**
   * 获取所有权限 并且增加是否有权限字段
   *
   * @return
   */
  public List<RoleVo> getUserIsRole(String userid) {
    List<RoleVo> list = new ArrayList<RoleVo>();
    //查询出我的权限
    List<TsysRole> myRoles = roleMapper.queryUserRole(userid);
    TsysRoleExample tsysRoleExample = new TsysRoleExample();
    //查询系统所有的角色
    List<TsysRole> tsysRoles = tsysRoleMapper.selectByExample(tsysRoleExample);
    if (StringUtils.isNotEmpty(tsysRoles)) {
      for (TsysRole tsysRole : tsysRoles) {
        Boolean isflag = false;
        RoleVo roleVo = new RoleVo(tsysRole.getId(), tsysRole.getName(), isflag);
        for (TsysRole myRole : myRoles) {
          if (tsysRole.getId().equals(myRole.getId())) {
            isflag = true;
            break;
          }
        }
        if (isflag) {
          roleVo.setIscheck(true);
          list.add(roleVo);
        } else {
          list.add(roleVo);
        }
      }
    }
    return list;
  }


  /**
   * 修改用户密码
   *
   * @param record
   * @return
   */
  public int updateUserPassword(User record) {
    record.setPassword(MD5Util.encode(record.getPassword()));
    //修改用户信息
    return tsysUserMapper.updateByPrimaryKeySelective(record);
  }


  /**
   * 修改用户信息以及角色信息
   *
   * @param record
   * @param roles
   * @return
   */
  @Transactional
  public int updateUserRoles(User record, String roleIds) {
    //先删除这个用户的所有角色
    TSysRoleUserExample tSysRoleUserExample = new TSysRoleUserExample();
//    tSysRoleUserExample.createCriteria().andSysUserIdEqualTo(record.getId());
    relationRoleUserMapper.deleteByExample(tSysRoleUserExample);
    if (StringUtils.isNotEmpty(roleIds)) {
      List<String> list_roles = ConvertUtil.toListStrArray(roleIds);
      //添加新的角色信息
      for (String role : list_roles) {
//        RelationRoleUser relationRoleUser = new RelationRoleUser(SnowflakeIdWorker.getUUID(),
//            record.getId(),
//            role);
//        relationRoleUserMapper.insertSelective(relationRoleUser);
      }
    }
    // 清除此用户角色信息缓存
    StpUtil.getSessionByLoginId(record.getId()).delete("Role_List");

    //修改用户信息
    return tsysUserMapper.updateByPrimaryKeySelective(record);
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
