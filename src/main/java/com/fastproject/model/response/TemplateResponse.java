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
public class TemplateResponse extends DefinedField {

  private String id;
  private String fieldKey;
  private String fieldName;
  private FieldType type;

  @Dict(dictCode = "true_or_false")
  private Boolean required;
  @Dict(dictCode = "true_or_false")
  private Boolean readOnly;
  private String dictTypeCode;

  public static TemplateResponse fromTemplate(Template template) {
    return TemplateResponse.builder()
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
