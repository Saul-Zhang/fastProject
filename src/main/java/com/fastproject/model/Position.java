package com.fastproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.Integer;
import lombok.Data;

/**
 * 岗位表 SysPosition
 */
@TableName("def_position")
@Data
public class Position implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@ApiModelProperty(value = "主键")
	private String id;

	/** 岗位名称 **/
	@ApiModelProperty(value = "岗位名称")
	private String name;

	/** 排序 **/
	@ApiModelProperty(value = "排序")
	private Integer orderNum;

	/** 状态 **/
	@ApiModelProperty(value = "状态")
	private Integer status;

}