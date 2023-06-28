package com.fastproject.model.response;

import lombok.Data;

/**
 * @author fastProject
 * @date 2023/6/26 23:36
 */
@Data
public class AuditDetailResponse {

  private String filedName;
  private String before;
  private String after;
}
