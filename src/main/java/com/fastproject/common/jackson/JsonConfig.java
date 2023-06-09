package com.fastproject.common.jackson;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fastProject
 * @date 2023/4/20 0:41
 */
@Configuration
public class JsonConfig {

  @Resource
  private DictAnnotationIntrospector dictAnnotationIntrospector;

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.annotationIntrospector(dictAnnotationIntrospector)
        .serializerByType(Long.class, ToStringSerializer.instance);
  }

  /**
   * Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
   * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
   */
//  @Bean
//  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//    return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
//        .serializerByType(Long.class, ToStringSerializer.instance)
//        .serializerByType(Long.TYPE, ToStringSerializer.instance);
//  }

//  @Bean
//  public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder, DictAnnotationIntrospector introspector) {
//    // 根据已有的配置创建自定义的ObjectMapper
//    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//    AnnotationIntrospector annotationIntrospector = objectMapper.getSerializationConfig().getAnnotationIntrospector();
//    // 将自定义注解內省器加入到Jackson注解内省器集合里，AnnotationIntrospector是双向链表结构
//    AnnotationIntrospector pair = AnnotationIntrospectorPair.pair(annotationIntrospector, introspector);
//    objectMapper.setAnnotationIntrospector(pair);
//    return objectMapper;
//  }
//
//  @Bean
//  public DictAnnotationIntrospector dictAnnotationIntrospector() {
//    return new DictAnnotationIntrospector();
//  }
//
//  @Bean
//  public DictSerializer dictSerializer() {
//    return new DictSerializer();
//  }
}

