package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.DictType;
import com.fastproject.model.request.query.DicTypeQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.util.SnowflakeIdWorker;
import com.fastproject.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
  private final DictService dictService;

  /**
   * 分页查询
   */
  public PageInfo<DictType> list(DicTypeQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    List<DictType> list = dictTypeMapper.selectList(new LambdaQueryWrapperX<DictType>()
        .likeIfPresent(DictType::getName, query.getName()));
    return new PageInfo<>(list);
  }

  public DictType selectById(Long id) {

    return dictTypeMapper.selectById(id);
  }


  @Transactional(rollbackFor = Exception.class)
  public AjaxResult updateById(DictType record) {
    if (StringUtils.isEmpty(record.getCode())) {
      return AjaxResult.error("字典编码不能为空");
    }
    DictType dictType = dictTypeMapper.selectById(record.getId());
    // 如果更改了字典类型的字典编码，则去更新字典数据中的字典编码
    if (!record.getCode().equals(dictType.getCode())) {
      DictData dictData = new DictData();
      dictData.setCode(record.getCode());
      dictDataMapper.update(dictData,
          new LambdaQueryWrapperX<DictData>().eq(DictData::getCode, record.getCode()));
    }
    dictTypeMapper.updateById(record);
    dictService.clear();
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
    record.setId(SnowflakeIdWorker.getId());
    dictTypeMapper.insert(record);
    dictService.clear();
    return AjaxResult.success();
  }


  @Transactional(rollbackFor = Exception.class)
  public AjaxResult deleteByIds(List<Long> ids) {
    dictTypeMapper.selectList(new LambdaQueryWrapperX<DictType>().in(DictType::getId, ids))
        .forEach(dictType -> dictDataMapper.delete(new LambdaQueryWrapperX<DictData>()
            .eq(DictData::getCode, dictType.getCode())));
    dictTypeMapper.deleteBatchIds(ids);
    dictService.clear();
    return AjaxResult.success();
  }

//  public AjaxResult updateStatus(Long id, Character status) {
//    DictType entity = new DictType();
//    entity.setHidden(status);
//    entity.setId(id);
//    dictTypeMapper.updateById(entity);
//    return AjaxResult.success();
//  }
}
