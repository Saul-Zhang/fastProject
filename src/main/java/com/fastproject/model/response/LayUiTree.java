package com.fastproject.model.response;

import lombok.Data;

/**
 *
 * @author fastproject
 * @date 2023/5/5 11:25
 *
 * layui.dtree标准数据格式
 */
@Data
public class LayUiTree {

  private Long id;
  private String title;
  private Long parentId;
  /**
   * checkArr = "1"时节点是选中状态
   */
  private String checkArr = "0";
}
