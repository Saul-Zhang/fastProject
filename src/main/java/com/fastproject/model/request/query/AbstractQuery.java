package com.fastproject.model.request.query;

import lombok.Data;

@Data
public abstract class AbstractQuery {

  /**
   * 排序字段
   */

  private String orderByColumn;

  /**
   * 排序字符 asc desc
   */
  private String isAsc;
}
