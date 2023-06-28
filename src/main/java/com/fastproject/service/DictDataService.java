package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.request.query.DicDataQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.util.SnowflakeIdWorker;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

  /**
   * 添加
   */
  public AjaxResult add(DictData record) {
    insert(record);
    return AjaxResult.success();
  }

  public int insert(DictData record) {
    //添加雪花主键id
    record.setId(SnowflakeIdWorker.getId());
    return dictDataMapper.insert(record);
  }

  public DictData selectBId(Long id) {
    return dictDataMapper.selectById(id);
  }

  public AjaxResult updateById(DictData record) {
    if (StringUtils.isEmpty(record.getCode())) {
      return AjaxResult.error("字典编码不能为空");
    }
    dictDataMapper.updateById(record);
    return AjaxResult.success();
  }


  public AjaxResult deleteByIds(List<Long> ids) {
    dictDataMapper.deleteBatchIds(ids);
    return AjaxResult.success();
  }

  public AjaxResult updateStatus(Long id, Character status) {
    DictData record = new DictData();
    record.setStatus(status);
    record.setId(id);
    dictDataMapper.updateById(record);
    return AjaxResult.success();
  }

}
