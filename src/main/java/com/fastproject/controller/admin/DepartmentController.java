package com.fastproject.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "部门表")
@Controller
@RequestMapping("/DepartmentController")
public class DepartmentController {

//  private final String prefix = "admin/sysDepartment";
//  @Autowired
//  private DepartmentService departmentService;
//
//  /**
//   * 分页跳转
//   *
//   * @param model
//   * @return
//   * @author fuce
//   * @Date 2020年4月18日 下午11:48:55
//   */
//  @ApiOperation(value = "分页跳转", notes = "分页跳转")
//  @GetMapping("/view")
//  @SaCheckPermission("gen:sysDepartment:view")
//  public String view(ModelMap model) {
//    return prefix + "/list";
//  }
//
//  /**
//   * 分页查询
//   *
//   * @param tablepar
//   * @param searchText
//   * @return
//   * @author fuce
//   * @Date 2020年4月18日 下午11:48:34
//   */
//  //@Log(title = "部门表集合查询", action = "111")
//  @ApiOperation(value = "分页查询", notes = "分页查询")
//  @GetMapping("/list")
//  @SaCheckPermission("gen:sysDepartment:list")
//  @ResponseBody
//  public PageResult list(Tablepar tablepar, String searchText) {
//    PageInfo<Department> page = departmentService.list(tablepar, searchText);
//    return treeTable(page.getList());
//  }
//
//  /**
//   * 新增
//   */
//  @ApiOperation(value = "新增跳转", notes = "新增跳转")
//  @GetMapping("/add")
//  public String add(ModelMap modelMap) {
//    return prefix + "/add";
//  }
//
//  /**
//   * 新增
//   *
//   * @param department
//   * @return
//   * @author fuce
//   * @Date 2020年4月18日 下午11:47:51
//   */
//  //@Log(title = "部门表新增", action = "111")
//  @ApiOperation(value = "新增", notes = "新增")
//  @PostMapping("/add")
//  @SaCheckPermission("gen:sysDepartment:add")
//  @ResponseBody
//  public AjaxResult add(@RequestBody Department department) {
//    int b = departmentService.insertSelective(department);
//    if (b > 0) {
//      return success();
//    } else {
//      return error();
//    }
//  }
//
//  /**
//   * 删除
//   *
//   * @param ids
//   * @return
//   */
//  //@Log(title = "部门表删除", action = "111")
//  @ApiOperation(value = "删除", notes = "删除")
//  @DeleteMapping("/remove")
//  @SaCheckPermission("gen:sysDepartment:remove")
//  @ResponseBody
//  public AjaxResult remove(String ids) {
//    int b = departmentService.deleteByPrimaryKey(ids);
//    if (b > 0) {
//      return success();
//    } else {
//      return error();
//    }
//  }
//
//  /**
//   * 检查用户
//   *
//   * @param tsysUser
//   * @return
//   */
//  @ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
//  @PostMapping("/checkNameUnique")
//  @ResponseBody
//  public int checkNameUnique(Department department) {
//    int b = departmentService.checkNameUnique(department);
//    if (b > 0) {
//      return 1;
//    } else {
//      return 0;
//    }
//  }
//
//
//  /**
//   * 修改跳转
//   *
//   * @param id
//   * @param mmap
//   * @return
//   */
//  @ApiOperation(value = "修改跳转", notes = "修改跳转")
//  @GetMapping("/edit/{id}")
//  public String edit(@PathVariable("id") String id, ModelMap mmap) {
//    //获取自己的权限信息
//    Department mytsysPermission = departmentService.selectByPrimaryKey(id);
//
//    mmap.put("SysDepartment", mytsysPermission);
//    return prefix + "/edit";
//  }
//
//  /**
//   * 修改保存
//   */
//  //@Log(title = "部门表修改", action = "111")
//  @ApiOperation(value = "修改保存", notes = "修改保存")
//  @SaCheckPermission("gen:sysDepartment:edit")
//  @PutMapping("/edit")
//  @ResponseBody
//  public AjaxResult editSave(@RequestBody Department record) {
//    return toAjax(departmentService.updateByPrimaryKeySelective(record));
//  }
//
//
//  @GetMapping("/selectParent")
//  @ResponseBody
//  public TreeResult selectParent() {
//    List<Department> list = departmentService.selectByExample(new SysDepartmentExample());
//    return dataTree(list);
//  }
//
//
//  /**
//   * 根据主键查询
//   *
//   * @param id
//   * @param mmap
//   * @return
//   */
//  @PostMapping("/get/{id}")
//  @ApiOperation(value = "根据id查询唯一", notes = "根据id查询唯一")
//  public Department edit(@PathVariable("id") String id) {
//    return departmentService.selectByPrimaryKey(id);
//  }
//
//  /**
//   * Describe: 获取部门树状数据结构
//   */
//  @GetMapping("tree")
//  @ApiOperation(value = "获取部门树状数据结构", notes = "获取部门树状数据结构")
//  @ResponseBody
//  public TreeResult tree() {
//    return dataTree(departmentService.getAll());
//  }
//
//  /**
//   * 修改状态
//   *
//   * @param record
//   * @return
//   */
//  @PutMapping("/updateVisible")
//  @ResponseBody
//  public AjaxResult updateVisible(@RequestBody Department record) {
//    int i = departmentService.updateVisible(record);
//    return toAjax(i);
//  }


}
