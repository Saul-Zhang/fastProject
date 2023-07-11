package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Role;
import com.fastproject.model.request.query.RoleQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.RoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fastProject
 */
@Api(value = "用户角色")
@Controller
@RequestMapping("/RoleController")
@RequiredArgsConstructor
public class RoleController {

  private final String prefix = "admin/role";

  private final RoleService roleService;

  /**
   * 展示页面
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("system:role:view")
  public String view() {
    return prefix + "/list";
  }

  /**
   * 角色列表
   */
  @ApiOperation(value = "分页查询", notes = "分页查询")
  @GetMapping("/list")
  @SaCheckPermission("system:role:list")
  @ResponseBody
  public PageResponse list(RoleQuery query) {
    PageInfo<Role> page = roleService.list(query);
    return PageResponse.page(page.getList(), page.getTotal());
  }

  /**
   * 新增角色
   */
  @ApiOperation(value = "新增跳转", notes = "新增跳转")
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }


  /**
   * 角色添加
   */
  @ApiOperation(value = "新增", notes = "新增")
  @PostMapping("/add")
  @SaCheckPermission("system:role:add")
  @ResponseBody
  public AjaxResult add(@RequestBody Role role) {
    return roleService.insert(role);
  }

  /**
   * 删除角色
   */
  @DeleteMapping("/updateStatus")
  @SaCheckPermission("system:role:remove")
  @ResponseBody
  public AjaxResult updateStatus(@RequestParam List<Long> roleIds,
      @RequestParam Character status) {
    if (CollectionUtils.isEmpty(roleIds)) {
      return AjaxResult.error("至少选择一个角色");
    }
    return roleService.updateStatus(roleIds, status);
  }

  @DeleteMapping("/remove")
  @SaCheckPermission("system:role:remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return AjaxResult.error("至少选择一个角色");
    }
    return roleService.deleteByIds(ids);
  }


  /**
   * 修改角色
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{roleId}")
  public String edit(@PathVariable("roleId") Long roleId, ModelMap modelMap) {
    modelMap.put("role", roleService.selectById(roleId));
    return prefix + "/edit";
  }


  /**
   * 修改保存角色
   */
  //@Log(title = "修改保存角色", action = "1")
  @ApiOperation(value = "修改保存", notes = "修改保存")
  @SaCheckPermission("system:role:edit")
  @PutMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Role role) {
    return roleService.updateById(role);
  }
}
