package com.fastproject.model.custom;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

  /**
   * 菜单编号
   */
  private Long id;

  /**
   * 父节点
   */
  private Long parentId;

  /**
   * 标题
   */
  private String title;

  /**
   * 菜单类型 类型   0：目录   1：菜单   2：按钮
   */
  private Integer type;

  /**
   * 打 开 类 型 _iframe 0 _blank 1
   */
  private Integer openType;

  /**
   * 图标
   */
  private String icon;

  /**
   * 跳转路径
   */
  private String href;

  /**
   * 子菜单
   */
  private transient List<Menu> children = new ArrayList<>();


  /**
   * 计算列 提供给前端组件
   */
  private transient String checkArr = "0";


  public Menu(Long id, Long parentId, String title, Integer type, Integer openType,
      String icon, String href) {
    this.id = id;
    this.parentId = parentId;
    this.title = title;
    this.type = type;
    this.openType = openType;
    this.icon = icon;
    this.href = href;
  }

}
