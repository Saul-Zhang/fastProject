package com.fastproject.common.conf.xss;

import com.fastproject.common.conf.FastProperties;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class XssFilterAtuoConfig {

  @Autowired
  private FastProperties fastProperties;


  /**
   * 注册自定义过滤器
   *
   * @return
   */
  @Bean
  public FilterRegistrationBean<SimpleCORSFilter> xssFiltrRegister() {
    FilterRegistrationBean<SimpleCORSFilter> registration = new FilterRegistrationBean<SimpleCORSFilter>();
    //设置系统过滤器 (setFilter就是你所定义的过滤器filter类)
    registration.setFilter(new SimpleCORSFilter());

    //过滤所有路径
    registration.addUrlPatterns("/*");
    //过滤器名称
    registration.setName("XssFilter");

    List<String> xssnoturlList = fastProperties.getXssNotFilterUrl();
    String xssnot = "";
    if (xssnoturlList != null && xssnoturlList.size() > 0) {
      xssnot = String.join(",", xssnoturlList);
    }
    //添加忽略的格式以及链接请求
    registration.addInitParameter("exclusions",
        "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*," + xssnot);
    //优先级
    registration.setOrder(1);

    return registration;
  }

  /**
   *	过滤JSON数据
   */
//	@Bean
//	@Primary
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		SimpleModule module = new SimpleModule();
//		//自定义序列化过滤配置(XssStringJsonDeserializer), 对入参进行转译
//		module.addDeserializer(String.class, new XssStringJsonDeserializer());
//		// 注册解析器
//		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
//		objectMapper.registerModule(module);
//		return new MappingJackson2HttpMessageConverter(objectMapper);
//	}
}
