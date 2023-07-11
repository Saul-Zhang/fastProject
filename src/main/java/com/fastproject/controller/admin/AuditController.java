package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Audit;
import com.fastproject.model.request.query.AuditQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.AuditResponse;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.AuditService;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fastproject
 * @date 2023/6/18 21:23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/AuditController")
public class AuditController {

  private final String prefix = "admin/audit";

  private final AuditService auditService;

  @GetMapping("/view")
  @SaCheckPermission("system:audit:view")
  public String view() {
    return prefix + "/list";
  }


  /**
   * 获取“我审批的”列表
   */
  @GetMapping("/list")
  @SaCheckPermission("system:audit:view")
  @ResponseBody
  public PageResponse list(AuditQuery query) {
    return PageResponse.page(auditService.list(query));
  }

  @GetMapping("/detail-view/{auditId}")
  @SaCheckPermission("system:audit:view")
  public String detail(ModelMap modelMap, @PathVariable String auditId) {
    modelMap.put("auditId", auditId);
    return prefix + "/detail";
  }


  @GetMapping("/detail/{auditId}")
  @SaCheckPermission("system:audit:view")
  @ResponseBody
  public PageResponse detail(@PathVariable Long auditId) {
    return PageResponse.list(auditService.detail(auditId));
  }

  @GetMapping("/approve")
  @SaCheckPermission("system:audit:view")
  @ResponseBody
  public AjaxResult approve(Long auditId) {
    return auditService.approve(auditId);
  }

  @GetMapping("/apply-view")
  @SaCheckPermission("system:apply:view")
  public String applyView() {
    return prefix + "/apply/view";
  }

  @GetMapping("/apply-list")
  @SaCheckPermission("system:apply:view")
  @ResponseBody
  public PageResponse getApplyList(AuditQuery query) {
    return PageResponse.page(auditService.getApplyList(query));
  }
}
