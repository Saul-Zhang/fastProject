package com.fc.v2.common.conf;

import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目相关的配置
 * @author fastProject
 */
@Component
@ConfigurationProperties(prefix = "fast")
@Data
public class FastProperties
{
    /** 项目名称 */
    private String name;
    /** 版本 */
    private String version;
    /** 版权年份 */
    private String copyrightYear;
    /** 邮箱发送smtp */
    private String emailSmtp;
    /** 发送邮箱端口 */
    private String emailPort;
    /** 发送邮箱登录账号 */
    private String emailAccount;
    /** 发送邮箱登录密码 */
    private String emailPassword;
    /** 演示模式 **/
    private String demoEnabled;
    /**
     * 是否使用验证码
     */
    private Boolean captchaEnabled;
    /** 滚动验证码 **/
    private Boolean rollVerification;

    /** xss不拦截url配置 **/
    private List<String> xssNotFilterUrl;
    /** shiro不拦截url配置 **/
    private List<String> saTokenNotFilterUrl;
}
