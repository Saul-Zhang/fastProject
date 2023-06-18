package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fastProject
 * @date 2023/5/12 15:54
 */
@TableName("rel_department_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationDepartmentUser {

  private Long id;

  private Long departmentId;

  private Long userId;

  public RelationDepartmentUser(Long departmentId) {
    this.departmentId = departmentId;
  }
}
