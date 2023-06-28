package com.fastproject.model;

import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/26 0:04
 */
@Data
public class AuditContent {

  private Long filedId;
  private String before;
  private String after;
}