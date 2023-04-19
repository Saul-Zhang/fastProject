package com.fastproject.model.request.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends AbstractPageQuery {

  protected Long deptId;
  protected Long id;
}
