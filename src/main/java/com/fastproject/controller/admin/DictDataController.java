package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.DictData;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.service.SysDictDataService;
import com.fastproject.service.SysDictTypeService;
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
 * 字典表Controller
 *
 * @author fuce
 * @ClassName: DictDataController
 * @date 2019-11-20 22:46
 */
@Api(value = "字典数据表")
@Controller
@RequestMapping("/DictDataController")
public class DictDataController {

  private final String prefix = "admin/dict_data";
  @Autowired
  private SysDictDataService tSysDictDataService;
  @Autowired
  private SysDictTypeService sysDictTypeService;

  /**
   * 分页list页面
   *
   * @param model
   * @param dictId
   * @return
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("system:dictData:view")
  public String view(ModelMap model, String dictId) {
    model.addAttribute("dictId", dictId);
    return prefix + "/list";
  }

  /**
   * 字典数据表集合查询
   *
   * @param tablepar
   * @param searchText
   * @param dictId
   * @return
   */
  //@Log(title = "字典数据表集合查询", action = "1")
//  @ApiOperation(value = "分页查询", notes = "分页查询")
//  @GetMapping("/list")
//  @SaCheckPermission("system:dictData:list")
//  @ResponseBody
//  public Object list(Tablepar tablepar, String searchText, String dictId) {
//    PageInfo<DictData> page = tSysDictDataService.list(tablepar, searchText, dictId);
//    return PageResult.page(page.getList(), page.getTotal());
//  }

  /**
   * 新增跳转
   *
   * @param modelMap
   * @param dictId
   * @return
   */
  @ApiOperation(value = "新增跳转", notes = "新增跳转")
  @GetMapping("/add")
  public String add(ModelMap modelMap, String dictId) {
//    modelMap.addAttribute("dictType", sysDictTypeService.selectByPrimaryKey(dictId).getCode());
    return prefix + "/add";
  }

  /**
   * 新增保存
   *
   * @param dictData
   * @param model
   * @return
   */
  //@Log(title = "字典数据表新增", action = "1")
  @ApiOperation(value = "新增", notes = "新增")
  @PostMapping("/add")
  @SaCheckPermission("system:dictData:add")
  @ResponseBody
  public AjaxResult add(DictData dictData, Model model) {
//    int b = tSysDictDataService.insertSelective(dictData);
    return null;
  }

  /**
   * 删除
   *
   * @param ids
   * @return
   */
  //@Log(title = "字典数据表删除", action = "1")
  @ApiOperation(value = "删除", notes = "删除")
  @DeleteMapping("/remove")
  @SaCheckPermission("system:dictData:remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
//    int b = tSysDictDataService.deleteByPrimaryKey(ids);
    return null;
  }


  /**
   * 修改跳转
   *
   * @param id
   * @param mmap
   * @return
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") String id, ModelMap mmap) {
//    DictData sysDictData = tSysDictDataService.selectByPrimaryKey(id);
//    mmap.put("TSysDictData", sysDictData);
    return prefix + "/edit";
  }

  /**
   * 修改保存
   */
  //@Log(title = "字典数据表修改", action = "1")
  @ApiOperation(value = "修改保存", notes = "修改保存")
  @SaCheckPermission("system:dictData:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(DictData record) {
//    tSysDictDataService.updateByPrimaryKeySelective(record);
    return null;
  }


  /**
   * 修改是否默认
   *
   * @param record
   * @return
   */
  @PutMapping("/updateDefault")
  @ResponseBody
  public AjaxResult updateDefault(@RequestBody DictData record) {
//    int i = tSysDictDataService.updateByPrimaryKeySelective(record);
//    return toAjax(i);
    return null;
  }

  /**
   * 修改是否启用
   *
   * @param record
   * @return
   */
  @PutMapping("/updateEnable")
  @ResponseBody
  public AjaxResult updateEnable(@RequestBody DictData record) {
//    int i = tSysDictDataService.updateByPrimaryKeySelective(record);
//    return toAjax(i);
    return null;
  }

}
