package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.common.annotation.Log;
import com.fastproject.model.Position;
import com.fastproject.model.Role;
import com.fastproject.model.User;
import com.fastproject.model.request.body.UserBody;
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
 * @author fastProject
 */
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
  @GetMapping("/view")
  @SaCheckPermission("system:user:view")
  public String view() {
    return prefix + "/list";
  }


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
  @PostMapping("/add")
  @SaCheckPermission("system:user:add")
  @ResponseBody
  public AjaxResult add(User user,
      @RequestParam(value = "roleIds", required = false) String roleIds) {
    return userService.add(user, roleIds);
  }

  @PutMapping("/remove")
  @SaCheckPermission("system:user:remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> userIds) {
    if (CollectionUtils.isEmpty(userIds)) {
      return AjaxResult.error("至少选择一个用户");
    }
    return userService.deleteByIds(userIds);
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
  @SaCheckPermission("system:user:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(UserBody body) {
    return userService.update(body);
  }


  /**
   * 修改用户密码跳转
   */
  //@Log(title = "修改用户密码", action = "1")
  @GetMapping("/editPwd/{id}")
  public String editPwd(@PathVariable("id") Long id, ModelMap modelMap) {
    modelMap.put("user", userService.getUserById(id));
    return prefix + "/editPwd";
  }

  /**
   * 修改保存用户
   */
  //@Log(title = "修改用户密码", action = "1")
  @SaCheckPermission("system:user:editPwd")
  @PostMapping("/editPwd")
  @ResponseBody
  public AjaxResult editPwdSave(User user) {
    return userService.updateUserPassword(user);
  }
}
