package com.fastproject.model.response;

import com.fastproject.common.annotation.Dict;
import com.fastproject.model.DefinedField;
import com.fastproject.model.FieldType;
import com.fastproject.model.Template;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fastProject
 * @date 2023/6/18 22:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ColsResponse extends DefinedField {

  private String id;
  private String fieldKey;
  private String fieldName;
  private FieldType type;

  private Boolean required;
  private Boolean readOnly;
  private String dictTypeCode;

  private Map<String, String> dictDataMap;


  public static ColsResponse fromTemplate(Template template) {
    return ColsResponse.builder()
        .id(String.valueOf(template.getId()))
        .fieldKey(template.getFieldKey())
        .fieldName(template.getFieldName())
        .type(template.getType())
        .required(template.getRequired())
        .readOnly(template.getReadOnly())
        .dictTypeCode(template.getDictTypeCode())
        .build();
  }
}
