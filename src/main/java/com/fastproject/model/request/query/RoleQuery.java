package com.fastproject.model.request.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQuery extends AbstractPageQuery {

  private String name;

}
