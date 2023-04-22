package com.fastproject.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.fastproject.common.base.BaseController;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.mapper.GeneratorMapper.TsysUserMapper;
import com.fastproject.model.Notice;
import com.fastproject.model.User;
import com.fastproject.model.custom.Menu;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 后台方法
 *
 * @author fastProject
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController extends BaseController {

  private final UserService userService;

  private final String prefix = "admin";

  @Autowired
  private TsysUserMapper tsysUserMapper;

  @ApiOperation(value = "首页", notes = "首页")
  @GetMapping({"", "/index"})
  public String index(HttpServletRequest request) {
    request.getSession().setAttribute("sessionUserName", SaTokenUtil.getUser().getRealName());
    // 获取公告信息
    List<Notice> notices = noticeService.getNotice(SaTokenUtil.getUser(), null);
    request.getSession().setAttribute("notices", notices);
    return prefix + "/index";
  }


  @ApiOperation(value = "获取登录用户菜单栏", notes = "获取登录用户菜单栏")
  @GetMapping("/getUserMenu")
  @ResponseBody
  public List<Menu> getUserMenu() {
    return permissionService.getMenus(SaTokenUtil.getUserId());
  }


  /**
   * 请求到登陆界面
   */
  @ApiOperation(value = "请求到登陆界面", notes = "请求到登陆界面")
  @GetMapping("/login")
  public String login(ModelMap modelMap) {
    if (StpUtil.isLogin()) {
      return "redirect:/" + prefix + "/index";
    } else {
      modelMap.put("captchaEnabled", fastProperties.getCaptchaEnabled());
      return "login";
    }
  }

  /**
   * 用户登陆验证
   */
  @ApiOperation(value = "用户登陆验证", notes = "用户登陆验证")
  @PostMapping("/login")
  @ResponseBody
  public AjaxResult login(User user, String captcha, RedirectAttributes redirectAttributes,
      boolean rememberMe, HttpServletRequest request) {
    return userService.login(user, captcha, rememberMe, request);
  }




  /**
   * 退出登陆
   *
   * @return
   */
  @ApiOperation(value = "退出登陆", notes = "退出登陆")
  @GetMapping("/Loginout")
  @ResponseBody
  public AjaxResult LoginOut(HttpServletRequest request, HttpServletResponse response) {
    // 在这里执行退出系统前需要清空的数据
    // ...
    // 注销
    StpUtil.logout();
    return success();
  }

  /**** 页面测试 ****/
  @ApiOperation(value = "404页面", notes = "404页面")
  @GetMapping("Out404")
  public String Out404(HttpServletRequest request, HttpServletResponse response) {

    return "redirect:/error/404";
  }

  @GetMapping("Out403")
  @ApiOperation(value = "403页面", notes = "403页面")
  public String Out403(HttpServletRequest request, HttpServletResponse response) {

    return "redirect:/error/403";
  }

  @ApiOperation(value = "500页面", notes = "500页面")
  @GetMapping("Out500")
  public String Out500(HttpServletRequest request, HttpServletResponse response) {

    return "redirect:/error/500";
  }

  /**
   * 权限测试跳转页面
   *
   * @param request
   * @param response
   * @return
   */
  @ApiOperation(value = "权限测试跳转页面", notes = "权限测试跳转页面")
  @GetMapping("Outqx")
  @SaCheckPermission("system:user:asd")
  public String Outqx(HttpServletRequest request, HttpServletResponse response) {

    return "redirect:/error/500";
  }
  /**** 页面测试EDN ****/
}
