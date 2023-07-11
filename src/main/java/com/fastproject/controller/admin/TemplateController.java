package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Template;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fastproject
 * @date 2023/6/18 21:23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/TemplateController")
public class TemplateController {

  private final String prefix = "admin/template";

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

  @SaCheckPermission("system:template:edit")
  @PutMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Template template) {
    return templateService.updateById(template);
  }
}
