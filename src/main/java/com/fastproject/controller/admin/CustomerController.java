package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.common.annotation.Log;
import com.fastproject.model.request.body.BatchUpdateCustomerBody;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.ColsResponse;
import com.fastproject.model.response.CustomerEditResponse;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.CustomerService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fastproject
 * @date 2023/6/18 21:23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/CustomerController")
public class CustomerController {

  private final String prefix = "view/customer";

  private final CustomerService customerService;

  @GetMapping("/view")
  @SaCheckPermission("system:customer:list")
  public String view() {
    return prefix + "/list";
  }


  @GetMapping("/list")
  @SaCheckPermission("system:customer:list")
  @ResponseBody
  public PageResponse page(@RequestParam Map<String, String> query) {
//    return PageResponse.list(customerService.list(query));
    return PageResponse.page(customerService.page(query));
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


  @Log(title = "新增客户")
  @PostMapping("/add")
  @SaCheckPermission("system:customer:add")
  @ResponseBody
  public AjaxResult add(@RequestBody Map<String, String> map) {
    return customerService.applyAuditAddCustomer(map);
  }

  @GetMapping("/checkName")
  @ResponseBody
  public AjaxResult checkName(String customerName, String customerId) {
    if(!customerService.checkName(customerName, customerId)){
        return AjaxResult.error("客户名称已存在");
    }
    return AjaxResult.success();
  }


  @GetMapping("/edit/{customerId}")
  @SaCheckPermission("system:customer:edit")
  public String edit(@PathVariable("customerId") String customerId, ModelMap modelMap) {
    modelMap.put("customerId", customerId);
    return prefix + "/edit";
  }


  @GetMapping("/{customerId}")
  @SaCheckPermission("system:customer:edit")
  @ResponseBody
  public List<CustomerEditResponse> getDetail(@PathVariable("customerId") Long customerId) {
    return customerService.selectByCustomerId(customerId);
  }

  @Log(title = "编辑客户")
  @SaCheckPermission("system:customer:edit")
  @PutMapping("/edit/{customerId}")
  @ResponseBody
  public AjaxResult editSave(@RequestBody Map<String, String> map,
      @PathVariable("customerId") Long customerId) {
    return customerService.applyAuditUpdateCustomer(map, customerId);
  }

  @GetMapping("/batchEdit")
  @SaCheckPermission("system:customer:edit")
  public String batchEdit(@RequestParam List<String> ids, ModelMap modelMap) {
    modelMap.put("ids", String.join(",",ids));
    modelMap.put("count",ids.size());
    return prefix + "/batchEdit";
  }

  @Log(title = "批量编辑客户")
  @PutMapping("/batchEdit")
  @SaCheckPermission("system:customer:edit")
  @ResponseBody
  public AjaxResult batchUpdate(BatchUpdateCustomerBody body){
    return customerService.applyAuditBatchUpdateCustomer(body);
  }

  @Log(title = "删除客户")
  @SaCheckPermission("system:customer:remove")
  @DeleteMapping("/remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> ids, @RequestParam String description) {
    return customerService.applyAuditRemoveCustomer(ids, description);
  }

  @Log(title = "导入客户")
  @SaCheckPermission("system:customer:upload")
  @PostMapping("/upload")
  @ResponseBody
  public AjaxResult upload(MultipartFile file) throws IOException {
    return customerService.applyAuditUpload(file);
  }

  @Log(title = "导出客户")
  @SaCheckPermission("system:customer:export")
  @GetMapping("/export")
  @ResponseBody
  public void export(HttpServletResponse response, @RequestParam List<Long> ids,
      @RequestParam Map<String, String> query) throws IOException {
    customerService.export(response, ids, query);
  }

  @GetMapping("/template")
  @ResponseBody
  public void downloadTemplate(HttpServletResponse response) throws IOException {
    customerService.downloadTemplate(response);
  }

}
