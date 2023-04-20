package com.fastproject.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fastproject.common.annotation.Dict;
import java.io.IOException;
import java.util.Optional;

/**
 * @author fastProject
 * @date 2023/4/20 0:11
 */
public class DictSerializer extends StdSerializer<Object> implements ContextualSerializer {

  private static final long serialVersionUID = -6157558261755426448L;

  private String dictCode;

  public DictSerializer(){
    super(Object.class);
  }

  public DictSerializer(String dictCode) {
    super(Object.class);
    this.dictCode = dictCode;
  }

//  @Override
//  public Class<Object> handledType() {
//    return Object.class;
//  }

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
//      dictCode = null;
    }
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider serializerProvider,
      BeanProperty beanProperty) throws JsonMappingException {

    Dict dict = Optional.ofNullable(beanProperty).map(b -> b.getAnnotation(Dict.class))
        .orElse(null);
    if (dict != null) {
      dictCode = dict.dictCode();
      return new DictSerializer(dictCode);
    }
    return serializerProvider.findNullValueSerializer(null);
  }
}
