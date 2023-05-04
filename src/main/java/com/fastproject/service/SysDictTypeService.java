package com.fastproject.service;

import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.TSysDictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典类型表Service
 *
 * @author 一休
 * @Title: TSysDictTypeService.java
 * @Package com.fc.v2.service
 * @email 438081243@qq.com
 * @date 2019-09-05 12:34:25
 */
@Service
public class SysDictTypeService {

  @Autowired
  private TSysDictTypeMapper tSysDictTypeMapper;
  @Autowired
  private DictDataMapper dictDataMapper;

//	/**
//	 * 分页查询
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 */
//	 public PageInfo<DictType> list(Tablepar tablepar,String name){
//	        TSysDictTypeExample testExample=new TSysDictTypeExample();
//	        testExample.setOrderByClause("id+0 desc");
//	        if(name!=null&&!"".equals(name)){
//	        	testExample.createCriteria().andDictNameLike("%"+name+"%");
//	        }
//
//	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
//	        List<DictType> list= tSysDictTypeMapper.selectByExample(testExample);
//	        PageInfo<DictType> pageInfo = new PageInfo<DictType>(list);
//	        return  pageInfo;
//	 }
//
//	@Override
//	@Transactional
//	public int deleteByPrimaryKey(String ids) {
//		//查询type数据得data中DictType有哪些
//		List<String> lista=ConvertUtil.toListStrArray(ids);
//		TSysDictTypeExample example=new TSysDictTypeExample();
//		example.createCriteria().andIdIn(lista);
//		List<DictType> dictTypes=tSysDictTypeMapper.selectByExample(example);
//		//在删除type下面得data数据
//		List<String> datatypes=new ArrayList<String>();
//		for (DictType dictType : dictTypes) {
//
//			datatypes.add(dictType.getCode());
//		}
//		TSysDictDataExample  dictDataExample=new TSysDictDataExample();
//		dictDataExample.createCriteria().andDictTypeIn(datatypes);
//		dictDataMapper.deleteByExample(dictDataExample);
//		//在删除type数据
//		tSysDictTypeMapper.deleteByExample(example);
//		return 1;
//	}
//
//
//	@Override
//	public DictType selectByPrimaryKey(String id) {
//
//		return tSysDictTypeMapper.selectByPrimaryKey(id);
//	}
//
//
//	@Override
//	public int updateByPrimaryKeySelective(DictType record) {
//		record.setUpdateTime(new Date());
//		record.setUpdateBy(SaTokenUtil.getUser().getUsername());
//		return tSysDictTypeMapper.updateByPrimaryKeySelective(record);
//	}
//
//	/**
//	 * 添加
//	 */
//	@Override
//	public int insertSelective(DictType record) {
//		//添加雪花主键id
//		record.setId(SnowflakeIdWorker.getUUID());
//		record.setCreateTime(new Date());
//		record.setUpdateTime(new Date());
//		record.setCreateBy(SaTokenUtil.getUser().getUsername());
//
//		return tSysDictTypeMapper.insertSelective(record);
//	}
//
//
//	@Override
//	public int updateByExampleSelective(DictType record, TSysDictTypeExample example) {
//
//		return tSysDictTypeMapper.updateByExampleSelective(record, example);
//	}
//
//
//	@Override
//	public int updateByExample(DictType record, TSysDictTypeExample example) {
//
//		return tSysDictTypeMapper.updateByExample(record, example);
//	}
//
//	@Override
//	public List<DictType> selectByExample(TSysDictTypeExample example) {
//
//		return tSysDictTypeMapper.selectByExample(example);
//	}
//
//
//	@Override
//	public long countByExample(TSysDictTypeExample example) {
//
//		return tSysDictTypeMapper.countByExample(example);
//	}
//
//
//	@Override
//	public int deleteByExample(TSysDictTypeExample example) {
//
//		return tSysDictTypeMapper.deleteByExample(example);
//	}
//
//	/**
//	 * 检查name
//	 * @param TSysDictType
//	 * @return
//	 */
//	public int checkNameUnique(DictType dictType){
//		TSysDictTypeExample example=new TSysDictTypeExample();
//		example.createCriteria().andDictNameEqualTo(dictType.getName());
//		List<DictType> list=tSysDictTypeMapper.selectByExample(example);
//		return list.size();
//	}
}
