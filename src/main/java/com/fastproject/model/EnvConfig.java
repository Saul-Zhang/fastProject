package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fastProject
 * @date 2023/6/24 22:30
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "cfg_env")
@Data
public class EnvConfig extends DefinedField {

  private Long id;

  private String name;

  private String code;

  private String value;

  private String description;
}
