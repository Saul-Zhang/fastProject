package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 字典数据表
 *
 */
@TableName("def_dict_data")
@Data
public class DictData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典数据表id
	 */
	private String id;

	/**
	 * 字典排序
	 */
	private Integer dictSort;

	/**
	 * 字典标签
	 */
	private String dictLabel;

	/**
	 * 字典键值
	 */
	private String value;

	/**
	 * 字典类型
	 */
	private String code;

	/**
	 * 样式属性（其他样式扩展）
	 */
	private String cssClass;

	/**
	 * 表格回显样式
	 */
	private String listClass;

	/**
	 * 是否默认（Y是 N否）
	 */
	private String isDefault;

	/**
	 * 状态（0正常 1停用）
	 */
	private String status;

	/**
	 * 创建者
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 更新者
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 备注
	 */
	private String remark;

}