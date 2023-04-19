package com.fastproject.controller.admin;

import com.fastproject.common.base.BaseController;
import com.fastproject.common.conf.FastProperties;
import com.fastproject.common.domain.AjaxResult;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fastProject
 */
@Api(value = "验证码")
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController extends BaseController {

  private final FastProperties properties;

  /**
   * 验证码生成
   *
   * @param request  请求报文
   * @param response 响应报文
   */
  @RequestMapping("/captchaImage")
  public void generate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Captcha captcha;
    if (properties.getRollVerification()) {
      captcha = new GifCaptcha(130, 48, 4);
    } else {
      // 静态验证码
      captcha = new SpecCaptcha(130, 48, 4);
    }
    CaptchaUtil.out(captcha, request, response);
  }

  /**
   * 异步验证
   *
   * @param request 请求报文
   * @param captcha 验证码
   * @return 验证结果
   */
  @RequestMapping("verify")
  public AjaxResult verify(HttpServletRequest request, String captcha) {
    if (CaptchaUtil.ver(captcha, request)) {
      return success();
    }
    return error();
  }


}
