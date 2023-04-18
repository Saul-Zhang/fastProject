package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fc.v2.util.DateUtils;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("def_notice")
public class Notice implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 主键
   **/
  private String id;

  /**
   * 标题
   **/
  private String title;

  /**
   * 内容
   **/
  private String content;

  /**
   * 类型
   **/
  private Integer type;

  /**
   * 创建人id
   **/
  private String createId;

  /**
   * 创建人name
   **/
  private String createUsername;

  /**
   * 发信时间
   **/
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

//  /**
//   * 格式化时间
//   *
//   * @return YYYY_MM_DD
//   */
//  public String getdate() {
//    return DateUtils.dateTime(this.createTime);
//  }

}