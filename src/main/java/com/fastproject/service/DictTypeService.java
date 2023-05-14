package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.model.DictType;
import com.fastproject.model.request.query.DicTypeQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.util.SnowflakeIdWorker;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fastProject
 */
@Service
@RequiredArgsConstructor
public class DictTypeService {

  private final DictTypeMapper dictTypeMapper;
  private final DictDataMapper dictDataMapper;

  /**
   * 分页查询
   */
  public PageInfo<DictType> list(DicTypeQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    List<DictType> list = dictTypeMapper.selectList(new LambdaQueryWrapperX<DictType>()
        .likeIfPresent(DictType::getName, query.getName()));
    return new PageInfo<>(list);
  }

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
  public DictType selectById(Long id) {

    return dictTypeMapper.selectById(id);
  }


  public AjaxResult updateById(DictType record) {
    if (StringUtils.isEmpty(record.getCode())) {
      return AjaxResult.error("字典编码不能为空");
    }
    record.setUpdateTime(new Date());
    dictTypeMapper.updateById(record);
    return AjaxResult.success();
  }


  /**
   * 添加
   */
  public AjaxResult add(DictType record) {
    if (StringUtils.isEmpty(record.getCode())) {
      return AjaxResult.error("字典编码不能为空");
    }
    Long count = dictTypeMapper.selectCount(new LambdaQueryWrapperX<DictType>()
        .eq(DictType::getCode, record.getCode()));
    if (count > 0) {
      return AjaxResult.error(record.getCode() + "字典编码已经存在");
    }
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getUUID());
    dictTypeMapper.insert(record);
    return AjaxResult.success();
  }


  @Transactional(rollbackFor = Exception.class)
  public AjaxResult deleteByIds(List<Long> ids) {
    dictDataMapper.deleteBatchIds(ids);
    return AjaxResult.success();
  }

  public AjaxResult updateStatus(Long id, Character status) {
    DictType entity = new DictType();
    entity.setStatus(status);
    entity.setId(id);
    dictTypeMapper.updateById(entity);
    return AjaxResult.success();
  }
}
