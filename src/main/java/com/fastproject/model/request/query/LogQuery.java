package com.fastproject.model.request.query;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class LogQuery extends AbstractPageQuery {

  private String title;
  private Long operator;
}
