package com.fastproject.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.datatype.jdk8.Jdk8BeanSerializerModifier;

/**
 * @author Zhang Song
 * @date 2023/4/20 0:42
 */
public class DictSerializerModifier extends BeanSerializerModifier {
  @Override
  public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
    return new DictSerializer(null, (JsonSerializer<Object>) serializer);
  }
}
