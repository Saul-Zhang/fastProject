package com.fastproject.service;

import com.fastproject.common.base.BaseService;
import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.common.utils.ConvertUtil;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.TSysDictTypeMapper;
import com.fastproject.model.auto.DictData;
import com.fastproject.model.auto.DictType;
import com.fastproject.model.auto.TSysDictDataExample;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典数据表Service
 */
@Service
public class SysDictDataService implements BaseService<DictData, TSysDictDataExample> {

  @Autowired
  private DictDataMapper dictDataMapper;
  @Autowired
  private TSysDictTypeMapper tSysDictTypeMapper;

  /**
   * 分页查询
   *
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<DictData> list(Tablepar tablepar, String name, String dictId) {
    TSysDictDataExample testExample = new TSysDictDataExample();
    testExample.setOrderByClause("dict_sort ASC");
    if (dictId != null && !"".equals(dictId)) {
      DictType dictType = tSysDictTypeMapper.selectByPrimaryKey(dictId);
      if (dictType != null) {
        testExample.createCriteria().andDictTypeEqualTo(dictType.getCode());
      }
    }
    if (name != null && !"".equals(name)) {
      testExample.createCriteria().andDictValueLike("%" + name + "%");
    }

    PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
    List<DictData> list = dictDataMapper.selectByExample(testExample);
    PageInfo<DictData> pageInfo = new PageInfo<DictData>(list);
    return pageInfo;
  }


  @Override
  public int deleteByPrimaryKey(String ids) {
    List<String> lista = ConvertUtil.toListStrArray(ids);
    TSysDictDataExample example = new TSysDictDataExample();
    example.createCriteria().andIdIn(lista);
    return dictDataMapper.deleteByExample(example);
  }

  /**
   * 添加
   */
  @Override
  public int insertSelective(DictData record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    record.setCreateTime(new Date());
    record.setUpdateTime(new Date());
    record.setCreateBy(SaTokenUtil.getUser().getUsername());
    return dictDataMapper.insertSelective(record);
  }

  @Override
  public DictData selectByPrimaryKey(String id) {
    return dictDataMapper.selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(DictData record) {
    record.setUpdateTime(new Date());
    record.setUpdateBy(SaTokenUtil.getUser().getUsername());
    return dictDataMapper.updateByPrimaryKeySelective(record);
  }


  @Override
  public int updateByExampleSelective(DictData record, TSysDictDataExample example) {

    return dictDataMapper.updateByExampleSelective(record, example);
  }


  @Override
  public int updateByExample(DictData record, TSysDictDataExample example) {

    return dictDataMapper.updateByExample(record, example);
  }

  @Override
  public List<DictData> selectByExample(TSysDictDataExample example) {

    return dictDataMapper.selectByExample(example);
  }


  @Override
  public long countByExample(TSysDictDataExample example) {

    return dictDataMapper.countByExample(example);
  }


  @Override
  public int deleteByExample(TSysDictDataExample example) {

    return dictDataMapper.deleteByExample(example);
  }

  /**
   * 检查name
   *
   * @param TSysDictData
   * @return
   */
  public int checkNameUnique(DictData dictData) {
    TSysDictDataExample example = new TSysDictDataExample();
    example.createCriteria().andDictValueEqualTo(dictData.getValue());
    List<DictData> list = dictDataMapper.selectByExample(example);
    return list.size();
  }

  /**
   * 批量删除
   *
   * @param dictIds
   * @author fuce
   * @Date 2019年9月9日 上午12:40:52
   */
  public void deleteByPrimaryDictIds(List<String> dictIds) {
    TSysDictDataExample example = new TSysDictDataExample();
    example.createCriteria().andIdIn(dictIds);
    dictDataMapper.deleteByExample(example);
  }


}
