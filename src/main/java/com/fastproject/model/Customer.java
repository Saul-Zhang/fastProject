package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/22 20:09
 */
@TableName("def_customer")
@Data
@Builder
public class Customer {

  private Long id;

  private Long customerId;

  private Long fieldId;

  private String value;
}
