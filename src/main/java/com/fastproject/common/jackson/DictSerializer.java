package com.fastproject.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fastproject.common.annotation.Dict;
import com.fastproject.common.utils.SpringUtils;
import com.fastproject.service.DictCacheService;
import java.io.IOException;
import java.util.Optional;

/**
 * @author fastProject
 * @date 2023/4/20 0:11
 */
public class DictSerializer extends StdSerializer<Object> implements ContextualSerializer {

  private static final long serialVersionUID = -6157558261755426448L;

  private String dictCode;

  public DictSerializer() {
    super(Object.class);
  }

  public DictSerializer(String dictCode) {
    super(Object.class);
    this.dictCode = dictCode;
  }

  @Override
  public void serialize(Object o, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    if (dictCode != null) {
      String obj = String.valueOf(o);
      String str = Optional.ofNullable(
          SpringUtils.getBean(DictCacheService.class).getData(dictCode, obj)).orElse(obj);
      jsonGenerator.writeString(str);
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
