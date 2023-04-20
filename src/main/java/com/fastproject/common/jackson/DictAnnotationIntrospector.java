package com.fastproject.common.jackson;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fastproject.common.annotation.Dict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fastProject
 * @date 2023/4/20 11:43
 */
@Slf4j
@Component
public class DictAnnotationIntrospector extends NopAnnotationIntrospector {
  @Override
  public Object findSerializer(Annotated am) {

    Dict annotation = am.getAnnotation(Dict.class);
    if (annotation != null) {
      log.info("----当前序列化使用自定义敏感字段序列化器----");
      return DictSerializer.class;
    }
    return super.findSerializer(am);
  }
}
