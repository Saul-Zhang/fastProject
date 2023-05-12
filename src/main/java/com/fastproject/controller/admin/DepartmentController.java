package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Department;
import com.fastproject.model.request.query.DepartmentQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.TreeResponse;
import com.fastproject.service.DepartmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/DepartmentController")
@RequiredArgsConstructor
public class DepartmentController {

  private final String prefix = "admin/department" ;
  private final DepartmentService departmentService;

  /**
   * 分页跳转
   */
  @GetMapping("/view")
  @SaCheckPermission("system:department:view")
  public String view(ModelMap model) {
    return prefix + "/list" ;
  }

  /**
   * 分页查询
   */
  //@Log(title = "部门表集合查询", action = "111")
  @GetMapping("/list")
  @SaCheckPermission("system:department:list")
  @ResponseBody
  public TreeResponse list(DepartmentQuery query) {
    return TreeResponse.treeData(departmentService.list(query));
  }

  /**
   * 新增
   */
  @GetMapping("/add")
  public String add(ModelMap modelMap) {
    return prefix + "/add" ;
  }

  /**
   * 新增
   */
  //@Log(title = "部门表新增", action = "111")
  @PostMapping("/add")
  @SaCheckPermission("system:department:add")
  @ResponseBody
  public AjaxResult add(@RequestBody Department department) {
    return departmentService.insert(department);
  }

  /**
   * 删除
   */
  //@Log(title = "部门表删除", action = "111")
  @DeleteMapping("/remove")
  @SaCheckPermission("system:department:remove")
  @ResponseBody
  public AjaxResult remove(List<Long> ids) {
    return departmentService.deleteByIds(ids);
  }


  /**
   * 修改跳转
   */
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    Department department = departmentService.selectById(id);

    mmap.put("department" , department);
    return prefix + "/edit" ;
  }

  /**
   * 修改保存
   */
  //@Log(title = "部门表修改", action = "111")
  @SaCheckPermission("system:department:edit")
  @PutMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Department record) {
    return departmentService.updateById(record);
  }


  @GetMapping("/selectParent")
  @ResponseBody
  public TreeResponse selectParent() {
    return TreeResponse.treeData(departmentService.selectAll());
  }

//  /**
//   * 根据主键查询
//   *
//   * @param id
//   * @param mmap
//   * @return
//   */
//  @PostMapping("/get/{id}")
//  public Department edit(@PathVariable("id") String id) {
//    return departmentService.selectByPrimaryKey(id);
//  }

  /**
   * 获取部门树状数据结构
   */
  @GetMapping("tree")
  @ResponseBody
  public TreeResponse tree(@RequestParam(required = false) Long userId) {
    return TreeResponse.treeData(departmentService.getTreeByUser(userId));
  }

  /**
   * 修改状态
   */
  @PutMapping("/updateStatus")
  @ResponseBody
  public AjaxResult updateStatus(@RequestParam Long id, Character status) {
    return departmentService.updateStatus(id, status);
  }

}
