package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fastProject
 * @date 2023/6/18 22:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("def_template")
public class Template extends DefinedField {

  private Long id;
  private String fieldKey;
  private String fieldName;
  private FieldType type;
  private Boolean required;
  private String dictTypeCode;

  private Integer orderNum;
}
