package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.common.annotation.Log;
import com.fastproject.model.Template;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.TemplateService;
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
 * @author fastproject
 * @date 2023/6/18 21:23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/TemplateController")
public class TemplateController {

  private final String prefix = "view/template";

  private final TemplateService templateService;

  @GetMapping("/view")
  @SaCheckPermission("system:template:view")
  public String view() {
    return prefix + "/list";
  }


  @GetMapping("/list")
  @SaCheckPermission("system:template:list")
  @ResponseBody
  public PageResponse list() {
    return PageResponse.list(templateService.list());
  }

  @GetMapping("/add")
  @SaCheckPermission("system:template:add")
  public String add() {
    return prefix + "/add";
  }

  @Log(title = "添加模板属性")
  @PostMapping("/add")
  @SaCheckPermission("system:user:add")
  @ResponseBody
  public AjaxResult add(@RequestBody Template template) {
    return templateService.add(template);
  }


  @SaCheckPermission("system:template:edit")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
    modelMap.put("template", templateService.selectById(id));
    return prefix + "/edit";
  }

  @Log(title = "编辑模板属性")
  @SaCheckPermission("system:template:edit")
  @PutMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Template template) {
    return templateService.updateById(template);
  }

  @Log(title = "删除模板属性")
  @SaCheckPermission("system:template:remove")
  @DeleteMapping("/remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return AjaxResult.error("至少选择一个属性");
    }
    return templateService.deleteByIds(ids);
  }
}
