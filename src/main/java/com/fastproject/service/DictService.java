package com.fastproject.service;

import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.TSysDictTypeMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.auto.TSysDictDataExample;
import com.fastproject.model.DictType;
import com.fastproject.model.auto.TSysDictTypeExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dict")
public class DictService {
	@Autowired
	private DictDataMapper dictDataMapper;
	
	@Autowired
	private TSysDictTypeMapper dictTypeMapper;

	/**
	 * 根据字典类型查询字典数据信息
	 * 
	 * @param dictType 字典类型
	 * @return 参数键值
	 */
	public List<DictData> getType(String dictType) {
		TSysDictDataExample example=new TSysDictDataExample();
		if(dictType!=null) {
			example.createCriteria().andDictTypeEqualTo(dictType);
			return dictDataMapper.selectByExample(example);
		}
		 return new ArrayList<DictData>();
	}

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 * 
	 * @param dictType
	 *            字典类型
	 * @param dictValue
	 *            字典键值
	 * @return 字典标签
	 */
	public String getLabel(String dictType, String dictValue) {
		
		TSysDictDataExample example=new TSysDictDataExample();
		if(dictType!=null&&dictValue!=null) {
			example.createCriteria().andDictTypeEqualTo(dictType).andDictValueEqualTo(dictValue);
			List<DictData> dictDatas= dictDataMapper.selectByExample(example);
			if(dictDatas.size()>0) {
				return dictDatas.get(0).getDictLabel();
			}
		}
		return "";
	}
	
	/**
	 * 根字典类型查询字典
	 */
	public DictType getSysDictType(String dictType) {
		TSysDictTypeExample dictTypeExample=new TSysDictTypeExample();
		 dictTypeExample.createCriteria().andDictTypeEqualTo(dictType);
		 List<DictType> dictTypes =dictTypeMapper.selectByExample(dictTypeExample);
		 if(dictTypes !=null&& dictTypes.size()>0) {
			 return dictTypes.get(0);
		 }
		 return null;
		 
	}
	
	
	
}
