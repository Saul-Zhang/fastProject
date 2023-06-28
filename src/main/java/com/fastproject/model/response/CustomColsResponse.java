package com.fastproject.model.response;

import com.fastproject.model.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fastProject
 * @date 2023/6/22 20:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomColsResponse {

  private String title;
  private String field;

  public static CustomColsResponse fromTemplate(Template template) {
    return new CustomColsResponse(template.getFieldName(), template.getFieldKey());
  }
}
