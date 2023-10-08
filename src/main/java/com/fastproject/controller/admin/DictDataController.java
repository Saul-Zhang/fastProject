package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.DictData;
import com.fastproject.model.request.query.DicDataQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.DictDataService;
import com.fastproject.service.DictTypeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "字典数据表")
@Controller
@RequestMapping("/DictDataController")
public class DictDataController {

  private final String prefix = "view/dict_data";
  @Autowired
  private DictDataService dictDataService;
  @Autowired
  private DictTypeService dictTypeService;

  /**
   * 分页list页面
   */
  @GetMapping("/view")
  @SaCheckPermission("system:dictData:view")
  public String view(ModelMap model, String code) {
    model.addAttribute("code", code);
    return prefix + "/list";
  }

  /**
   * 字典数据表集合查询
   */
  @GetMapping("/list")
  @SaCheckPermission("system:dictData:list")
  @ResponseBody
  public PageResponse list(DicDataQuery query) {
    PageInfo<DictData> page = dictDataService.list(query);
    return PageResponse.page(page.getList(), page.getTotal());
  }

  /**
   * 新增跳转
   */
  @GetMapping("/add")
  public String add(ModelMap modelMap, String code) {
//    modelMap.addAttribute("code", dictTypeService.selectById(dictTypeId).getCode());
    modelMap.addAttribute("code", code);
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
  @PostMapping("/add")
  @SaCheckPermission("system:dictData:add")
  @ResponseBody
  public AjaxResult add(DictData dictData, Model model) {
    return dictDataService.add(dictData);
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
  public AjaxResult remove(@RequestParam List<Long> ids) {
    return dictDataService.deleteByIds(ids);
  }


  /**
   * 修改跳转
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap maps) {
    DictData dictData = dictDataService.selectBId(id);
    maps.put("dictData", dictData);
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
    return dictDataService.updateById(record);
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
   */
  @PutMapping("/updateEnable")
  @ResponseBody
  public AjaxResult updateStatus(@RequestParam Long id, @RequestParam Character status) {
    return dictDataService.updateStatus(id, status);
  }

}
