package com.fc.v2.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.fc.v2.common.base.BaseService;
import com.fc.v2.common.conf.FastProperties;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TSysRoleUserMapper;
import com.fc.v2.mapper.auto.TsysRoleMapper;
import com.fc.v2.mapper.auto.TsysUserMapper;
import com.fc.v2.mapper.custom.RoleDao;
import com.fc.v2.mapper.custom.TsysUserDao;
import com.fc.v2.model.auto.TSysRoleUser;
import com.fc.v2.model.auto.TSysRoleUserExample;
import com.fc.v2.model.auto.TsysRole;
import com.fc.v2.model.auto.TsysRoleExample;
import com.fc.v2.model.auto.TsysUser;
import com.fc.v2.model.auto.TsysUserExample;
import com.fc.v2.model.custom.RoleVo;
import com.fc.v2.model.custom.Tablepar;
import com.fc.v2.satoken.SaTokenUtil;
import com.fc.v2.util.MD5Util;
import com.fc.v2.util.ServletUtils;
import com.fc.v2.util.SnowflakeIdWorker;
import com.fc.v2.util.StringUtils;
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
public class SysUserService implements BaseService<TsysUser, TsysUserExample> {

  private final FastProperties properties;
  //生成的用户dao
  @Autowired
  private TsysUserMapper tsysUserMapper;

  //生成的角色用户dao
  @Autowired
  private TSysRoleUserMapper tSysRoleUserMapper;

  //自定义角色dao
  @Autowired
  private RoleDao roleDao;

  //自动生成的角色dao
  @Autowired
  private TsysRoleMapper tsysRoleMapper;

  //自定义用户dao
  @Autowired
  private TsysUserDao userDao;

  /**
   * 分页查询
   *
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<TsysUser> list(Tablepar tablepar) {
    PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
    List<TsysUser> list = userDao.queryUserInfo(tablepar.getSearchText());
    PageInfo<TsysUser> pageInfo = new PageInfo<TsysUser>(list);
    return pageInfo;
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
      tSysRoleUserMapper.deleteByExample(tSysRoleUserExample);
    }
    return i;

  }

  /**
   * 添加用户
   */
  @Override
  public int insertSelective(TsysUser record) {
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
  public int insertUserRoles(TsysUser record, String roles) {
    String userid = SnowflakeIdWorker.getUUID();
    record.setId(userid);
    if (StringUtils.isNotEmpty(roles)) {
      List<String> list_roles = ConvertUtil.toListStrArray(roles);
      for (String rolesid : list_roles) {
        TSysRoleUser roleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID(), userid, rolesid);
        tSysRoleUserMapper.insertSelective(roleUser);
      }
    }

    //密码加密
    record.setPassword(MD5Util.encode(record.getPassword()));
    return tsysUserMapper.insertSelective(record);
  }

  @Override
  public TsysUser selectByPrimaryKey(String id) {

    return tsysUserMapper.selectByPrimaryKey(id);
  }


  @Override
  public int updateByPrimaryKeySelective(TsysUser record) {
    record.setPassword(MD5Util.encode(record.getPassword()));
    return tsysUserMapper.updateByPrimaryKeySelective(record);
  }


  @Override
  public int updateByExampleSelective(TsysUser record, TsysUserExample example) {

    return tsysUserMapper.updateByExampleSelective(record, example);
  }


  @Override
  public int updateByExample(TsysUser record, TsysUserExample example) {

    return tsysUserMapper.updateByExample(record, example);
  }

  @Override
  public List<TsysUser> selectByExample(TsysUserExample example) {

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
   * @param tsysUser
   * @return
   */
  public int checkLoginNameUnique(TsysUser tsysUser) {
    TsysUserExample example = new TsysUserExample();
    example.createCriteria().andUsernameEqualTo(tsysUser.getUsername());
    List<TsysUser> list = tsysUserMapper.selectByExample(example);

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
    List<TsysRole> myRoles = roleDao.queryUserRole(userid);
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
  public int updateUserPassword(TsysUser record) {
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
  public int updateUserRoles(TsysUser record, String roleIds) {
    //先删除这个用户的所有角色
    TSysRoleUserExample tSysRoleUserExample = new TSysRoleUserExample();
    tSysRoleUserExample.createCriteria().andSysUserIdEqualTo(record.getId());
    tSysRoleUserMapper.deleteByExample(tSysRoleUserExample);
    if (StringUtils.isNotEmpty(roleIds)) {
      List<String> list_roles = ConvertUtil.toListStrArray(roleIds);
      //添加新的角色信息
      for (String role : list_roles) {
        TSysRoleUser tSysRoleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID(), record.getId(),
            role);
        tSysRoleUserMapper.insertSelective(tSysRoleUser);
      }
    }
    // 清除此用户角色信息缓存
    StpUtil.getSessionByLoginId(record.getId()).delete("Role_List");

    //修改用户信息
    return tsysUserMapper.updateByPrimaryKeySelective(record);
  }


  public AjaxResult login(TsysUser user, String captcha, boolean rememberMe,
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
    TsysUser queryUser = userDao.queryUserName(userName);
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
