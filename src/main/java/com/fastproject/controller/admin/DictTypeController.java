package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.DictType;
import com.fastproject.model.request.query.DicTypeQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.DictTypeService;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/DictTypeController")
@RequiredArgsConstructor
public class DictTypeController {

  private final String prefix = "admin/dict_type";

  private final DictTypeService dictTypeService;

  /**
   * 分页list页面
   */
  @GetMapping("/view")
  @SaCheckPermission("system:dictType:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

  /**
   * 字典类型表集合查询
   */
  //@Log(title = "字典类型表集合查询", action = "111")
  @GetMapping("/list")
  @SaCheckPermission("system:dictType:list")
  @ResponseBody
  public PageResponse list(DicTypeQuery query) {
    PageInfo<DictType> page = dictTypeService.list(query);
    return PageResponse.page(page.getList(), page.getTotal());
  }

  /**
   * 新增跳转
   */
  @GetMapping("/add")
  public String add(ModelMap modelMap) {
    return prefix + "/add";
  }

  /**
   * 新增保存
   */
  @PostMapping("/add")
  @SaCheckPermission("system:dictType:add")
  @ResponseBody
  public AjaxResult add(DictType dictType, Model model) {
    return dictTypeService.add(dictType);
  }

  /**
   * 删除字典类型
   */
  //@Log(title = "字典类型表删除", action = "111")
  @DeleteMapping("/remove")
  @SaCheckPermission("system:dictType:remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> ids) {
    return dictTypeService.deleteByIds(ids);
  }

  /**
   * 修改跳转
   */
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    mmap.put("dictType", dictTypeService.selectById(id));
    return prefix + "/edit";
  }

  /**
   * 修改保存
   */
  //@Log(title = "字典类型表修改", action = "111")
  @SaCheckPermission("system:dictType:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(DictType record) {
    return dictTypeService.updateById(record);
  }

  /**
   * 修改是否启用
   */
  @PutMapping("/updateStatus")
  @ResponseBody
  public AjaxResult updateStatus(@RequestParam Long id, @RequestParam Character status) {
    return dictTypeService.updateStatus(id, status);
  }
}
