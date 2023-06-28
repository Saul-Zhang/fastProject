package com.fastproject.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/22 20:09
 */
@Data
@Builder
public class CustomerResponse {


//  private Long customerId;


  private String fieldId;

  private String fieldName;

  private String value;
}
