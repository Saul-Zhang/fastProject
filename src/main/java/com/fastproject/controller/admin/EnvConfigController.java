package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.EnvConfig;
import com.fastproject.model.Role;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.EnvConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fastProject
 * @date 2023/7/15 23:47
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/EnvConfigController")
public class EnvConfigController {

  private final EnvConfigService envConfigService;

  private final String prefix = "view/env";

  @GetMapping("/view")
  @SaCheckPermission("system:env:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

  @GetMapping("/list")
  @SaCheckPermission("system:env:view")
  @ResponseBody
  public PageResponse list() {
    return PageResponse.page(envConfigService.list());
  }

  @SaCheckPermission("system:env:edit")
  @PutMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody EnvConfig envConfig) {
    return envConfigService.updateById(envConfig);
  }
}
