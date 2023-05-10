package com.fastproject.model.request.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends AbstractPageQuery {

  protected String realName;
  protected String employeeId;
  private Character status;
  private Long departmentId;
}
