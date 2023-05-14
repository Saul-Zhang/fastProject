package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.request.query.DicDataQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.satoken.SaTokenUtil;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典数据表Service
 */
@Service
@RequiredArgsConstructor
public class DictDataService {

  private final DictDataMapper dictDataMapper;
  private final DictTypeMapper dictTypeMapper;

  /**
   * 分页查询
   */
  public PageInfo<DictData> list(DicDataQuery query) {

    PageHelper.startPage(query.getPage(), query.getLimit());
    List<DictData> list = dictDataMapper.selectList(new LambdaQueryWrapperX<DictData>()
        .eqIfPresent(DictData::getCode, query.getCode()));
    return new PageInfo<>(list);
  }

//
//  @Override
//  public int deleteByPrimaryKey(String ids) {
//    List<String> lista = ConvertUtil.toListStrArray(ids);
//    TSysDictDataExample example = new TSysDictDataExample();
//    example.createCriteria().andIdIn(lista);
//    return dictDataMapper.deleteByExample(example);
//  }
//
  /**
   * 添加
   */
  public AjaxResult add(DictData record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    record.setCreateTime(new Date());
    record.setUpdateTime(new Date());
    record.setCreateBy(SaTokenUtil.getUser().getUsername());
     dictDataMapper.insert(record);
     return AjaxResult.success();
  }

  public DictData selectBId(Long id) {
    return dictDataMapper.selectById(id);
  }

  public AjaxResult updateById(DictData record) {
    record.setUpdateTime(new Date());
    record.setUpdateBy(SaTokenUtil.getUser().getUsername());
     dictDataMapper.updateById(record);
     return  AjaxResult.success();
  }

//
//  @Override
//  public int updateByExampleSelective(DictData record, TSysDictDataExample example) {
//
//    return dictDataMapper.updateByExampleSelective(record, example);
//  }
//
//
//  @Override
//  public int updateByExample(DictData record, TSysDictDataExample example) {
//
//    return dictDataMapper.updateByExample(record, example);
//  }
//
//  @Override
//  public List<DictData> selectByExample(TSysDictDataExample example) {
//
//    return dictDataMapper.selectByExample(example);
//  }
//
//
//  @Override
//  public long countByExample(TSysDictDataExample example) {
//
//    return dictDataMapper.countByExample(example);
//  }


  public AjaxResult deleteByIds(List<Long> ids) {
dictDataMapper.deleteBatchIds(ids);
    return AjaxResult.success();
  }

//  /**
//   * 检查name
//   *
//   * @param TSysDictData
//   * @return
//   */
//  public int checkNameUnique(DictData dictData) {
//    TSysDictDataExample example = new TSysDictDataExample();
//    example.createCriteria().andDictValueEqualTo(dictData.getValue());
//    List<DictData> list = dictDataMapper.selectByExample(example);
//    return list.size();
//  }
//
//  /**
//   * 批量删除
//   *
//   * @param dictIds
//   * @author fuce
//   * @Date 2019年9月9日 上午12:40:52
//   */
//  public void deleteByPrimaryDictIds(List<String> dictIds) {
//    TSysDictDataExample example = new TSysDictDataExample();
//    example.createCriteria().andIdIn(dictIds);
//    dictDataMapper.deleteByExample(example);
//  }


}
