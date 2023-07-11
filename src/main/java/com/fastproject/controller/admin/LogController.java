package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.OperationLog;
import com.fastproject.model.request.query.LogQuery;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.OperationLogService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/LogController")
@RequiredArgsConstructor
public class LogController {

  private final OperationLogService operationLogService;

  //跳转页面参数
  private final String prefix = "admin/log";

  @GetMapping("/view")
  @SaCheckPermission("system:log:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

	@GetMapping("/list")
	@SaCheckPermission("system:log:list")
	@ResponseBody
	public PageResponse list(LogQuery query){
    return PageResponse.page(operationLogService.list(query));
	}

//
//	/**
//	 * 删除日志
//	 * @param ids
//	 * @return
//	 */
//	//@Log(title = "删除日志", action = "1")
//	@ApiOperation(value = "删除", notes = "删除")
//	@DeleteMapping("/remove")
//	@SaCheckPermission("system:log:remove")
//	@ResponseBody
//	public AjaxResult remove(String ids){
//		int b=sysOperLogService.deleteByPrimaryKey(ids);
//		if(b>0){
//			return success();
//		}else{
//			return error();
//		}
//	}
//
//


}
