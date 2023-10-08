package com.fastproject.model.response;

import com.fastproject.model.FieldType;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/22 20:09
 */
@Data
@Builder
public class CustomerEditResponse {


//  private Long customerId;


  private String fieldId;

  private String fieldName;

  private String value;

  private Map<String, String> dictDataMap;

  private Boolean required;

  private FieldType type;
}
