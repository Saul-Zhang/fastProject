package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.aizuda.monitor.OshiMonitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器信息Controller
 *
 * @author fuce
 * @version V1.0
 * @ClassName: ServiceController
 * @date 2019-06-23 00:55
 */
@Api(value = "服务器信息")
@Controller
@RequestMapping("/ServiceController")
public class ServiceController {

  // 跳转页面参数
  private final String prefix = "view/service";

  @Resource
  private OshiMonitor oshiMonitor;

  /**
   * 展示页面
   */
  @ApiOperation(value = "展示页面", notes = "展示页面")
  @GetMapping("/view")
  @SaCheckPermission("system:service:view")
  public String view(ModelMap model) {
//		List<Notice> notices = noticeService.getNEW();
//		List<TsysOperLog> sysOperLog = sysOperLogService.getNEW();
//		if (notices == null || notices.size() <= 0) {
//			Notice notice = new Notice();
//			notice.setId("0");
//			notice.setTitle("暂无公告");
//			notice.setCreateTime(new Date());
//			notices.add(notice);
//		}

    long nd = 1000 * 24 * 60 * 60;
    long nh = 1000 * 60 * 60;
    long nm = 1000 * 60;
    // 获得两个时间的毫秒时间差异
    long diff = oshiMonitor.getJvmInfo().getUptime();
    // 计算差多少天
    long day = diff / nd;
    // 计算差多少小时
    long hour = diff % nd / nh;
    // 计算差多少分钟
    long min = diff % nd % nh / nm;
    // 计算差多少秒//输出结果
    //long sec = diff % nd % nh % nm / ns;

    model.addAttribute("runtime", day + "天" + hour + "小时" + min + "分钟");
    model.addAttribute("service", oshiMonitor);
//		model.addAttribute("sysNotices", notices);
//		model.addAttribute("sysOperLog", sysOperLog);
    return prefix + "/list";
  }
}
