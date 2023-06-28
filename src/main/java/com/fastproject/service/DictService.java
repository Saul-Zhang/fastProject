package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.DictType;

import com.fastproject.model.constant.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dict")
@RequiredArgsConstructor
public class DictService {

	private final DictDataMapper dictDataMapper;
	private final DictTypeMapper dictTypeMapper;

	private final Map<String, Map<String, String>> DICT_DATA_CACHE = new ConcurrentHashMap<>();
	private final Map<String, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>();


	public Map<String, Map<String, String>> loadDictMap() {
		return dictDataMapper.selectList(
						new LambdaQueryWrapperX<DictData>().eq(DictData::getStatus, Status.ENABLE))
				.stream().collect(Collectors.groupingBy(DictData::getCode,
						Collectors.toMap(DictData::getValue, DictData::getLabel)
				));
	}


	public void clear() {
		DICT_DATA_CACHE.clear();
	}

	public String getData(String code, String value) {
		loadLocalCache();
		return Optional.ofNullable(DICT_DATA_CACHE.get(code)).map(m -> m.get(value)).orElse(null);
	}

	public Map<String, String> getDict(String code) {
		loadLocalCache();
		return Optional.ofNullable(DICT_DATA_CACHE.get(code)).orElse(new HashMap<>());
	}

	private void loadLocalCache() {
		if (DICT_DATA_CACHE.isEmpty()) {
			synchronized (DICT_DATA_CACHE) {
				if (DICT_DATA_CACHE.isEmpty()) {
					DICT_DATA_CACHE.putAll(loadDictMap());
				}
			}
		}
	}




	/**
	 * 根据字典类型查询字典数据信息
	 * 
	 * @param dictType 字典类型
	 * @return 参数键值
	 */
	public List<DictData> getType(String dictType) {
//		TSysDictDataExample example=new TSysDictDataExample();
//		if(dictType!=null) {
//			example.createCriteria().andDictTypeEqualTo(dictType);
//			return dictDataMapper.selectByExample(example);
//		}
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
		
//		TSysDictDataExample example=new TSysDictDataExample();
//		if(dictType!=null&&dictValue!=null) {
//			example.createCriteria().andDictTypeEqualTo(dictType).andDictValueEqualTo(dictValue);
//			List<DictData> dictDatas= dictDataMapper.selectByExample(example);
//			if(dictDatas.size()>0) {
//				return dictDatas.get(0).getDictLabel();
//			}
//		}
		return "";
	}
	
	/**
	 * 根字典类型查询字典
	 */
	public DictType getSysDictType(String dictType) {
//		TSysDictTypeExample dictTypeExample=new TSysDictTypeExample();
//		 dictTypeExample.createCriteria().andDictTypeEqualTo(dictType);
//		 List<DictType> dictTypes =dictTypeMapper.selectByExample(dictTypeExample);
//		 if(dictTypes !=null&& dictTypes.size()>0) {
//			 return dictTypes.get(0);
//		 }
		 return null;
		 
	}

	public List<DictType> getDictTypes() {
		return dictTypeMapper.selectList(null);
	}
	
	
	
}
