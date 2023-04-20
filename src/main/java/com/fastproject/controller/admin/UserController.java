package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.common.base.BaseController;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.common.domain.ResultTable;
import com.fastproject.common.log.Log;
import com.fastproject.model.auto.Department;
import com.fastproject.model.auto.SysDepartmentExample;
import com.fastproject.model.auto.SysPosition;
import com.fastproject.model.auto.SysPositionExample;
import com.fastproject.model.auto.TsysRole;
import com.fastproject.model.auto.User;
import com.fastproject.model.custom.RoleVo;
import com.fastproject.model.request.query.UserQuery;
import com.fastproject.model.response.UserResponse;
import com.fastproject.service.SysDepartmentService;
import com.fastproject.service.SysPositionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户Controller
 */
@Api(value = "用户数据")
@Controller
@RequestMapping("/UserController")
public class UserController extends BaseController {

  private final String prefix = "admin/user";
  //部门
  @Autowired
  private SysDepartmentService departmentService;
  //岗位
  @Autowired
  private SysPositionService positionService;

  /**
   * 展示跳转页面
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("system:user:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }


  /**
   * list集合
   */
  @ApiOperation(value = "分页查询", notes = "分页查询")
  @GetMapping("/list")
  @SaCheckPermission("system:user:list")
  @ResponseBody
  public ResultTable list(UserQuery query) {
    PageInfo<UserResponse> page = sysUserService.list(query);
    return pageTable(page.getList(), page.getTotal());
  }

  /**
   * 新增跳转
   */
  @ApiOperation(value = "新增跳转", notes = "新增跳转")
  @GetMapping("/add")
  public String add(ModelMap modelMap) {
    //添加角色列表
    List<TsysRole> tsysRoleList = sysRoleService.queryList();
    //部门列表
    List<Department> departments = departmentService.selectByExample(new SysDepartmentExample());
    //岗位列表
    List<SysPosition> sysPositions = positionService.selectByExample(new SysPositionExample());
    //角色
    modelMap.put("tsysRoleList", tsysRoleList);
    //部门
    modelMap.put("departmentsList", departments);
    //岗位
    modelMap.put("sysPositionsList", sysPositions);
    return prefix + "/add";
  }

  /**
   * 新增保存
   *
   * @param user
   * @param model
   * @param roles
   * @return
   * @author fuce
   * @Date 2019年11月11日 下午4:14:57
   */
  @Log(title = "用户新增", action = "111")
  @ApiOperation(value = "新增", notes = "新增")
  @PostMapping("/add")
  @SaCheckPermission("system:user:add")
  @ResponseBody
  public AjaxResult add(User user,
      @RequestParam(value = "roleIds", required = false) String roleIds) {
    int b = sysUserService.insertUserRoles(user, roleIds);
    if (b > 0) {
      return success();
    } else {
      return error();
    }
  }

  /**
   * 删除用户
   *
   * @param ids
   * @return
   */
  //@Log(title = "删除用户", action = "1")
  @ApiOperation(value = "删除", notes = "删除")
  @DeleteMapping("/remove")
  @SaCheckPermission("system:user:remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    int b = sysUserService.deleteByPrimaryKey(ids);
    if (b > 0) {
      return success();
    } else {
      return error();
    }
  }

  /**
   * 检查用户
   *
   * @param user
   * @return
   */
  @ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
  @PostMapping("/checkLoginNameUnique")
  @ResponseBody
  public int checkLoginNameUnique(User user) {
    int b = sysUserService.checkLoginNameUnique(user);
    if (b > 0) {
      return 1;
    } else {
      return 0;
    }
  }


  /**
   * 修改用户跳转
   *
   * @param id
   * @param mmap
   * @return
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") String id, ModelMap mmap) {
    //查询所有角色
    List<RoleVo> roleVos = sysUserService.getUserIsRole(id);
    //岗位列表
    List<SysPosition> sysPositions = positionService.selectByExample(new SysPositionExample());
    mmap.put("roleVos", roleVos);
    mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
    //岗位
    mmap.put("sysPositionsList", sysPositions);
    return prefix + "/edit";
  }

  /**
   * 修改保存用户
   */
  //@Log(title = "修改保存用户", action = "1")
  @ApiOperation(value = "修改保存用户", notes = "修改保存用户")
  @SaCheckPermission("system:user:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(User user,
      @RequestParam(value = "roleIds", required = false) String roleIds) {
    return toAjax(sysUserService.updateUserRoles(user, roleIds));
  }


  /**
   * 修改用户密码跳转
   *
   * @param id
   * @param mmap
   * @return
   */
  //@Log(title = "修改用户密码", action = "1")
  @ApiOperation(value = "修改用户密码跳转", notes = "修改用户密码跳转")
  @GetMapping("/editPwd/{id}")
  public String editPwd(@PathVariable("id") String id, ModelMap mmap) {
    mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
    return prefix + "/editPwd";
  }

  /**
   * 修改保存用户
   */
  //@Log(title = "修改用户密码", action = "1")
  @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
  @SaCheckPermission("system:user:editPwd")
  @PostMapping("/editPwd")
  @ResponseBody
  public AjaxResult editPwdSave(User user) {
    return toAjax(sysUserService.updateUserPassword(user));
  }


}
