package com.fastproject.model.auto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.date.DateUtil;
import java.util.Date;

public class Test implements Serializable {
    private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value = "主键")
	private String id;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "年龄")
	private Integer age;
	
	@ApiModelProperty(value = "性别")
	private String sex;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "修改时间")
	private Date bCUpdate;
	
	@ApiModelProperty(value = "写个字符串")
	private String tCbName;
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id =  id;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name =  name;
	}
	@JsonProperty("age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age =  age;
	}
	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex =  sex;
	}
	@JsonProperty("createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime =  createTime;
	}
	@JsonProperty("bCUpdate")
	public Date getBCUpdate() {
		return bCUpdate;
	}

	public void setBCUpdate(Date bCUpdate) {
		this.bCUpdate =  bCUpdate;
	}
	@JsonProperty("tCbName")
	public String getTCbName() {
		return tCbName;
	}

	public void setTCbName(String tCbName) {
		this.tCbName =  tCbName;
	}

														
	public Test(String id,String name,Integer age,String sex,Date createTime,Date bCUpdate,String tCbName) {
				
		this.id = id;
				
		this.name = name;
				
		this.age = age;
				
		this.sex = sex;
				
		this.createTime = createTime;
				
		this.bCUpdate = bCUpdate;
				
		this.tCbName = tCbName;
				
	}

	public Test() {
	    super();
	}

	public String dateToStringConvert(Date date) {
		if(date!=null) {
			return DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	

}