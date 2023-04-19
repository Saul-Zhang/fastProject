package com.fastproject.model.request.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AbstractPageQuery extends AbstractQuery {

  /**
   * 页码
   */
  private int page;

  /**
   * 页码
   */
  private int limit;


}
