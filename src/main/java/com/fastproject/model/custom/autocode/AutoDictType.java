package com.fastproject.model.custom.autocode;

import java.util.List;

import com.fastproject.model.auto.DictData;
import com.fastproject.model.auto.DictType;

public class AutoDictType {
	//字典表
	private DictType dictType;
	//字典表里面的数据
	private List<DictData> dictDatas;

	public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public List<DictData> getDictDatas() {
		return dictDatas;
	}

	public void setDictDatas(List<DictData> dictDatas) {
		this.dictDatas = dictDatas;
	}

	public AutoDictType(DictType dictType, List<DictData> dictDatas) {
		super();
		this.dictType = dictType;
		this.dictDatas = dictDatas;
	}

	public AutoDictType() {
		super();
	}
	
	
	
	
	
}
