package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Permission;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResult;
import com.fastproject.model.response.TreeResult;
import com.fastproject.service.PermissionService;
import com.fastproject.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
 * 权限Controller
 *
 * @author fastProject
 */
@Api(value = "权限")
@Controller
@RequestMapping("/PermissionController")
@RequiredArgsConstructor
public class PermissionController {

  private final String prefix = "admin/permission";

  private final PermissionService permissionService;
  private final RoleService roleService;

  /**
   * 权限列表展示
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("system:permission:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

  /**
   * 权限列表
   */
  @ApiOperation(value = "分页查询", notes = "分页查询")
  @GetMapping("/list")
  @SaCheckPermission("system:permission:list")
  @ResponseBody
  public PageResult list() {
    return PageResult.page(permissionService.list());
  }

  /**
   * 新增权限
   */
  @ApiOperation(value = "新增跳转", notes = "新增跳转")
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }


  /**
   * 权限添加
   */
  //@Log(title = "权限添加", action = "1")
  @ApiOperation(value = "新增", notes = "新增")
  @PostMapping("/add")
  @SaCheckPermission("system:permission:add")
  @ResponseBody
  public AjaxResult add(@RequestBody Permission permission) {
    return permissionService.insert(permission);
  }

  /**
   * 删除权限
   */
  //@Log(title = "删除权限", action = "1")
  @ApiOperation(value = "删除", notes = "删除")
  @DeleteMapping("/remove")
  @SaCheckPermission("system:permission:remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> ids) {
    return permissionService.delete(ids);
  }

  /**
   * 修改权限
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{roleId}")
  public String edit(@PathVariable("roleId") Long id, ModelMap modelMap) {
    //获取自己的权限信息
    Permission permission = permissionService.selectById(id);
    //获取父权限信息
    Permission pattsysPermission = permissionService.selectById(permission.getPid());
    modelMap.put("permission", permission);
    modelMap.put("parentPermission", pattsysPermission);
    return prefix + "/edit";
  }

  /**
   * 修改保存权限
   */
  //@Log(title = "修改保存权限", action = "1")
  @ApiOperation(value = "修改保存", notes = "修改保存")
  @SaCheckPermission("system:permission:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Permission permission) {
    return permissionService.update(permission);
  }


  /**
   * 根据角色id获取bootstarp 所有打勾权限
   *
   * @param roleId 角色id集合
   * @return
   */
  @ApiOperation(value = "根据角色id获取所有打勾权限", notes = "根据角色id获取 所有打勾权限")
  @GetMapping("/getCheckPrem")
  @ResponseBody
  public TreeResult getCheckPrem(String roleId) {

    return TreeResult.treeData(permissionService.getRolePower(roleId));
  }


  /**
   * 跳转到菜单树页面
   *
   * @return
   */
  @ApiOperation(value = "跳转到菜单树页面", notes = "跳转到菜单树页面")
  @GetMapping("/tree/{roleId}")
  public String Tree(@PathVariable("roleId") String roleId, Model model) {
    model.addAttribute("roleId", roleId);
    return prefix + "/tree";
  }


  /**
   * 修改保存角色
   */
  //@Log(title = "修改保存角色", action = "1")
  @ApiOperation(value = "授权保存", notes = "授权保存")
  @SaCheckPermission("system:role:edit")
  @PutMapping("/updateRolePermission")
  @ResponseBody
  public AjaxResult updateRolePermission(Long roleId, List<Long> permissionIds) {
    return permissionService.updateRolePermission(roleId, permissionIds);
  }


  @GetMapping("/selectParent")
  @ResponseBody
  public TreeResult selectParent() {
    List<Permission> list = permissionService.getPermissionByUserId(null);
    Permission basePower = new Permission();
    basePower.setName("顶级权限");
    basePower.setId(0L);
    basePower.setPid(-1L);
    list.add(basePower);
    return TreeResult.treeData(list);
  }

  @PutMapping("/updateVisible")
  @ResponseBody
  public AjaxResult updateVisible(@RequestBody Permission Permission) {
//    int i = permissionService.updateVisible(Permission);
//    return toAjax(i);
    return null;
  }

}
