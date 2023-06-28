package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Role;
import com.fastproject.model.Template;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.ColsResponse;
import com.fastproject.model.response.CustomColsResponse;
import com.fastproject.model.response.CustomerResponse;
import com.fastproject.model.response.PageResponse;
import com.fastproject.model.response.TemplateResponse;
import com.fastproject.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/CustomerController")
public class CustomerController {

  private final String prefix = "admin/customer";

  private final CustomerService customerService;

  @GetMapping("/view")
  @SaCheckPermission("system:customer:view")
  public String view() {
    return prefix + "/list";
  }


  @GetMapping("/list")
  @SaCheckPermission("system:customer:list")
  @ResponseBody
  public PageResponse list(@RequestParam Map<String, String> map) {
    return PageResponse.list(customerService.list(map));
  }

  @GetMapping("/cols")
  @SaCheckPermission("system:customer:list")
  @ResponseBody
  public List<ColsResponse> cols() {
    return customerService.cols();
  }

  @GetMapping("/add")
  @SaCheckPermission("system:customer:add")
  public String add() {
    return prefix + "/add";
  }


  @PostMapping("/add")
  @SaCheckPermission("system:customer:add")
  @ResponseBody
  public AjaxResult add(@RequestBody Map<Long,String> map) {
    return customerService.applyAuditAddCustomer(map);
  }


  @GetMapping("/edit/{customerId}")
  @SaCheckPermission("system:customer:edit")
  public String edit(@PathVariable("customerId") Long customerId, ModelMap modelMap) {
    modelMap.put("customerId", customerId);
    return prefix + "/edit";
  }

  @GetMapping("/{customerId}")
  @SaCheckPermission("system:customer:edit")
  @ResponseBody
  public List<CustomerResponse> getDetail(@PathVariable("customerId") Long customerId) {
   return customerService.selectByCustomerId(customerId);
  }


  @SaCheckPermission("system:customer:edit")
  @PutMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Map<Long,String> map) {
    return customerService.update(map);
  }
}
