package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/5/12 15:54
 */
@TableName("rel_department_user")
@Data
public class RelationDepartmentUser {

  private Long id;

  private Long departmentId;

  private Long userId;
}
