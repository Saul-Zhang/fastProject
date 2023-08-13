package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/18 23:08
 */
@Data
public class DefinedField {

  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createAt;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateAt;

  @TableField(fill = FieldFill.INSERT)
  private Long createBy;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateBy;
}
