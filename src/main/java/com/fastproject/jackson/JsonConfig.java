package com.fastproject.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Song
 * @date 2023/4/20 0:41
 */
@Component
public class JsonConfig {

  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    SimpleModule simpleModule = new SimpleModule().setSerializerModifier(
        new DictSerializerModifier());
    builder.modules(simpleModule);
    return builder;
  }
}
