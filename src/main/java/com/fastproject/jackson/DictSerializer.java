package com.fastproject.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fastproject.annotation.Dict;
import java.io.IOException;
import java.util.Optional;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author fastProject
 * @date 2023/4/20 0:11
 */
public class DictSerializer extends JsonSerializer<Object> implements ContextualSerializer {

  private String dictCode;
  private JsonSerializer<Object> defSerializer;

  public DictSerializer(String dictCode, JsonSerializer<Object> defSerializer){
    this.dictCode = dictCode;
    this.defSerializer =defSerializer;
  }
  @Override
  public void serialize(Object o, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    // 字段自己的序列化操做，好比目前传入的是areaCode=10001，则先序列化areaCode：10001
//    this.serialize(value, gen, serializers);
    // 有自定义注解的type标识，咱们就增长一个对应的label，例：areaCode则 增长一个areaCodeVtTestLabel字段
    if (dictCode != null) {
      // 原字段名 例：areaCode
//      String fieldName = gen.getOutputContext().getCurrentName();
      // 字段对应的label 如 高新园区
//      String codeLabel = RedisUtils.dictCodeToLabel(type, value.toString());
      // 写入
      jsonGenerator.writeString("codeLabel");
    }
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider serializerProvider,
      BeanProperty beanProperty) throws JsonMappingException {

    Dict dict = Optional.ofNullable(beanProperty).map(b -> b.getAnnotation(Dict.class))
        .orElse(null);
    if (dict != null) {
      return new DictSerializer(dict.dictCode(), defSerializer );
    }
    return defSerializer;
  }
}
