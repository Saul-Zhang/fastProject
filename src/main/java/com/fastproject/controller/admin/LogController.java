package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.OperationLog;
import com.fastproject.model.request.query.LogQuery;
import com.fastproject.model.response.OperationLogResponse;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.OperationLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import java.util.stream.Collectors;
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
  private final String prefix = "view/log";

  @GetMapping("/view")
  @SaCheckPermission("system:log:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

	@GetMapping("/list")
	@SaCheckPermission("system:log:list")
	@ResponseBody
	public PageResponse list(LogQuery query){
        PageInfo<OperationLog> pageInfo = operationLogService.list(query);
        return PageResponse.page(pageInfo.getList().stream()
            .map(OperationLogResponse::fromOperationLog).collect(
                Collectors.toList()) ,pageInfo.getTotal());
	}

}
