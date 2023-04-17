package com.fc.v2.model.custom.autocode;

import com.fc.v2.util.StringUtils;
import lombok.Data;


/**
 * 实体列
 */
@Data
public class BeanColumn {

  /**
   * 表\目录
   **/
  private String table_catalog;
  /**
   * 是否为null
   **/
  private String is_nullable;
  /**
   * 表名
   **/
  private String table_name;
  /**
   * 数据库
   **/
  private String table_schema;
  /**
   * 额外的 EXTRA": "auto_increment  自增id
   **/
  private String extra;
  /**
   * 列名
   **/
  private String column_name;
  /**
   * 主键 PRI
   **/
  private String column_key;
  /**
   * 数字精度
   **/
  private String numeric_precision;
  /**
   * 权限
   **/
  private String privileges;
  /**
   * 列注释
   **/
  private String column_comment;
  /**
   * 数字刻度
   **/
  private String numeric_scale;
  /**
   * 列/类型
   **/
  private String column_type;
  /**
   * 生成表达式
   **/
  private String generation_expression;
  /**
   * 序数位置
   **/
  private String ordinal_position;
  /**
   * 数据类型
   **/
  private String data_type;
  /**
   * 默认值
   **/
  private String column_default;
  /**
   * 字符最大长度
   **/
  private String character_maximum_length;
  /**
   * 字符\八位字节\长度
   **/
  private String character_octet_length;
  /**
   * 日期时间精度
   **/
  private String datetime_precision;
  /**
   * 字符集名称
   **/
  private String character_set_name;
  /**
   * 排序规则名称
   **/
  private String collation_name;

  /**
   * 实体类型 java.lang.String
   **/
  private String beanType;

  /**
   * 实体bean列名 例如:nameVc
   **/
  private String beanName;
  /**
   * mapperxml需要类型 例如:jdbcType="VARCHAR"
   **/
  private String jdbcType;

  /**
   * java类型
   **/
  private String javaType;
  /**
   * java 首字母大写
   **/
  private String javaName;

  /**
   * 是字符串类型
   */
  private Integer htmlType = 1;

  /**
   * 字典表名字
   */
  private String dictTypeName;
  /**
   * 字典表里面的数据
   **/
  private AutoDictType autoDictType;


  enum htmlType {
    //字符串类型，labne类型，switch按钮类型，时间类型,上传类型
    STRING("String", 1),
    LABLE("lable", 2),
    SWITCH("switch", 3),
    TIME("time", 4),
    UPLOAD("upload", 5);

    htmlType(String type, int value) {
      this.type = type;
      this.value = value;
    }

    public String type;
    public int value;

  }


  public String getJavaType() {
    String beanType = this.getBeanType();
    String returnStr = "String";
    if (beanType == null) {
      return returnStr;
    } else {
      returnStr = beanType.substring(beanType.lastIndexOf(".") + 1, beanType.length());
    }
    javaType = returnStr;
    return javaType;
  }

  public void setJavaType(String javaType) {
    this.javaType = javaType;
  }

  public String getBeanType() {
    String type = this.getData_type();
    String returnStr = "java.lang.String";
    if (type == null) {
      return returnStr;
    }
    switch (type) {
      case "tinyint":
      case "smallint":
      case "int":
      case "mediumint":
      case "integer":
        returnStr = "java.lang.Integer";
        break;
      case "bigint":
        returnStr = "java.lang.Long";
        break;
      case "float":
        returnStr = "java.lang.Float";
        break;
      case "double":
        returnStr = "java.lang.Double";
        break;
      case "decimal":
        returnStr = "java.math.BigDecimal";
        break;
      case "bit":
        returnStr = "java.lang.Byte";
        break;
      case "char":
        returnStr = "java.lang.Character";
        break;
      case "varchar":
      case "tinytext":
      case "text":
      case "mediumtext":
      case "longtext":
        returnStr = "java.lang.String";
        break;
      case "date":
      case "datetime":
      case "timestamp":
        returnStr = "java.util.Date";
        break;
      default:
        break;
    }
    beanType = returnStr;
    return beanType;
  }


  public String getBeanName() {
    if (getColumn_name() != null) {
      return StringUtils.upperCase_(this.column_name, false);
    }
    return beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public String getJavaName() {
    javaName = StringUtils.firstUpperCase(getBeanName());
    return javaName;
  }


  public String getJdbcType() {
    String datetype = getData_type();
    String returnStr = "VARCHAR";
    if (datetype == null) {
      return returnStr;
    }
    switch (datetype) {
      case "tinyint":
        returnStr = "TINYINT";
        break;
      case "smallint":
        returnStr = "SMALLINT";
        break;
      case "int":
      case "mediumint":
      case "integer":
        returnStr = "INTEGER";
        break;
      case "bigint":
        returnStr = "BIGINT";
        break;
      case "float":
        returnStr = "REAL";
        break;
      case "double":
        returnStr = "DOUBLE";
        break;
      case "decimal":
        returnStr = "DECIMAL";
        break;
      case "bit":
        returnStr = "OTHER";
        break;
      case "char":
        returnStr = "CHAR";
        break;
      case "varchar":
      case "tinytext":
      case "text":
      case "mediumtext":
      case "longtext":
        returnStr = "VARCHAR";
        break;
      case "date":
      case "datetime":
      case "timestamp":
        returnStr = "TIMESTAMP";
        break;
      default:
        break;
    }
    jdbcType = returnStr;
    return jdbcType;
  }
}
