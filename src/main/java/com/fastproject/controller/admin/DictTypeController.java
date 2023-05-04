package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.DictType;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResult;
import com.fastproject.service.SysDictTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典类型Controller
 *
 * @author fuce
 * @ClassName: DictTypeController
 * @date 2019-11-20 22:45
 */
@Api(value = "字典类型表")
@Controller
@RequestMapping("/DictTypeController")
public class DictTypeController {

  private final String prefix = "admin/dict_type";

  @Autowired
  private SysDictTypeService tSysDictTypeService;

//  /**
//   * 分页list页面
//   *
//   * @param model
//   * @return
//   */
//  @ApiOperation(value = "分页跳转", notes = "分页跳转")
//  @GetMapping("/view")
//  @SaCheckPermission("system:dictType:view")
//  public String view(ModelMap model) {
//    return prefix + "/list";
//  }
//
//  /**
//   * 字典类型表集合查询
//   *
//   * @param tablepar   post参数
//   * @param searchText 查询字段
//   * @return ResultTable 集合
//   */
//  //@Log(title = "字典类型表集合查询", action = "111")
//  @ApiOperation(value = "分页查询", notes = "分页查询")
//  @GetMapping("/list")
//  @SaCheckPermission("system:dictType:list")
//  @ResponseBody
//  public PageResult list(Tablepar tablepar, String searchText) {
//    PageInfo<DictType> page = tSysDictTypeService.list(tablepar, searchText);
//    return pageTable(page.getList(), page.getTotal());
//  }
//
//  /**
//   * 新增跳转
//   *
//   * @param modelMap 试图
//   * @return String 跳转连接
//   */
//  @ApiOperation(value = "新增跳转", notes = "新增跳转")
//  @GetMapping("/add")
//  public String add(ModelMap modelMap) {
//    return prefix + "/add";
//  }
//
//  /**
//   * 新增保存
//   *
//   * @param dictType 字段类型
//   * @param model    model
//   * @return AjaxResult 对象
//   */
//  //@Log(title = "字典类型表新增", action = "111")
//  @ApiOperation(value = "新增", notes = "新增")
//  @PostMapping("/add")
//  @SaCheckPermission("system:dictType:add")
//  @ResponseBody
//  public AjaxResult add(DictType dictType, Model model) {
//    int b = tSysDictTypeService.insertSelective(dictType);
//    if (b > 0) {
//      return success();
//    } else {
//      return error();
//    }
//  }
//
//  /**
//   * 删除字典类型
//   *
//   * @param ids
//   * @return
//   */
//  //@Log(title = "字典类型表删除", action = "111")
//  @ApiOperation(value = "删除", notes = "删除")
//  @DeleteMapping("/remove")
//  @SaCheckPermission("system:dictType:remove")
//  @ResponseBody
//  public AjaxResult remove(String ids) {
//    int b = tSysDictTypeService.deleteByPrimaryKey(ids);
//    if (b > 0) {
//      return success();
//    } else {
//      return error();
//    }
//  }
//
//  /**
//   * 检查字典类型名字相同
//   *
//   * @param dictType
//   * @return
//   */
//  @ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
//  @PostMapping("/checkNameUnique")
//  @ResponseBody
//  public int checkNameUnique(DictType dictType) {
//    int b = tSysDictTypeService.checkNameUnique(dictType);
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
//    mmap.put("TSysDictType", tSysDictTypeService.selectByPrimaryKey(id));
//    return prefix + "/edit";
//  }
//
//  /**
//   * 修改保存
//   */
//  //@Log(title = "字典类型表修改", action = "111")
//  @ApiOperation(value = "修改保存", notes = "修改保存")
//  @SaCheckPermission("system:dictType:edit")
//  @PostMapping("/edit")
//  @ResponseBody
//  public AjaxResult editSave(DictType record) {
//    return toAjax(tSysDictTypeService.updateByPrimaryKeySelective(record));
//  }
//
//  /**
//   * 修改是否启用
//   *
//   * @param record
//   * @return
//   */
//  @PutMapping("/updateEnable")
//  @ResponseBody
//  public AjaxResult updateEnable(@RequestBody DictType record) {
//    int i = tSysDictTypeService.updateByPrimaryKeySelective(record);
//    return toAjax(i);
//  }


}
