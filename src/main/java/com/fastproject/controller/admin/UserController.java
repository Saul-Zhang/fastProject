package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.common.annotation.Log;
import com.fastproject.model.Position;
import com.fastproject.model.Role;
import com.fastproject.model.User;
import com.fastproject.model.custom.RoleVo;
import com.fastproject.model.request.query.UserQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.LayUiTree;
import com.fastproject.model.response.PageResponse;
import com.fastproject.model.response.UserResponse;
import com.fastproject.service.DepartmentService;
import com.fastproject.service.DictCacheService;
import com.fastproject.service.PositionService;
import com.fastproject.service.RoleService;
import com.fastproject.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户Controller
 *
 * @author fastProject
 */
@Api(value = "用户数据")
@Controller
@RequestMapping("/UserController")
@RequiredArgsConstructor
public class UserController {

  private final String prefix = "admin/user";

  private final DepartmentService departmentService;
  private final PositionService positionService;
  private final DictCacheService dictCacheService;
  private final UserService userService;
  private final RoleService roleService;

  /**
   * 展示跳转页面
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("system:user:view")
  public String view() {
    return prefix + "/list";
  }


  /**
   * list集合
   */
  @ApiOperation(value = "分页查询", notes = "分页查询")
  @GetMapping("/list")
  @SaCheckPermission("system:user:list")
  @ResponseBody
  public PageResponse list(UserQuery query) {
    PageInfo<UserResponse> page = userService.list(query);
    return PageResponse.page(page.getList(), page.getTotal());
  }

  /**
   * 新增跳转
   */
  @ApiOperation(value = "新增跳转", notes = "新增跳转")
  @GetMapping("/add")
  public String add(ModelMap modelMap) {
    //添加角色列表
    List<Role> roleList = roleService.getAll();
    //部门列表
    List<LayUiTree> departments = departmentService.getTreeByUser(null);
    //岗位列表
    List<Position> positions = positionService.getAll();
    //角色
    modelMap.put("roles", roleList);
    //部门
    modelMap.put("departments", departments);
    modelMap.put("positions", positions);
    modelMap.put("genders", dictCacheService.getDict("gender"));
    return prefix + "/add";
  }

  /**
   * 新增保存
   */
  @Log(title = "用户新增", action = "111")
  @ApiOperation(value = "新增", notes = "新增")
  @PostMapping("/add")
  @SaCheckPermission("system:user:add")
  @ResponseBody
  public AjaxResult add(User user,
      @RequestParam(value = "roleIds", required = false) String roleIds) {
    return userService.add(user, roleIds);
  }


  @PutMapping("/updateStatus")
  @SaCheckPermission("system:user:remove")
  @ResponseBody
  public AjaxResult updateStatus(@RequestParam List<Long> userIds,
      @RequestParam Character status) {
    if (CollectionUtils.isEmpty(userIds)) {
      return AjaxResult.error("至少选择一个用户");
    }
    return userService.updateStatus(userIds, status);
  }

  /**
   * 修改用户跳转
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long userId, ModelMap modelMap) {

    modelMap.put("roleVos", userService.getRolesByUserId(userId));
    modelMap.put("user", userService.getUserById(userId));
    //岗位
    modelMap.put("positions", positionService.getAll());
    modelMap.put("genders", dictCacheService.getDict("gender"));
//    modelMap.put("departments", departmentService.getDepartmentIdByUserId(userId));
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
      @RequestParam(value = "roleIds[]", required = false) List<Long> roleIds) {
    return userService.updateUserRoles(user, roleIds);
  }


  /**
   * 修改用户密码跳转
   */
  //@Log(title = "修改用户密码", action = "1")
  @ApiOperation(value = "修改用户密码跳转", notes = "修改用户密码跳转")
  @GetMapping("/editPwd/{id}")
  public String editPwd(@PathVariable("id") Long id, ModelMap modelMap) {
    modelMap.put("user", userService.getUserById(id));
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
    return userService.updateUserPassword(user);
  }
}
