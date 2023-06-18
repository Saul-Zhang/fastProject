package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.PositionMapper;
import com.fastproject.model.Position;
import com.fastproject.model.constant.Status;
import com.fastproject.model.request.body.UpdateStatusBody;
import com.fastproject.model.request.query.PositionQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位 PositionService
 **/
@Service
@RequiredArgsConstructor
public class PositionService {

  private final PositionMapper positionMapper;


  public List<Position> getAll() {
    return positionMapper.selectList(null);
  }

  /**
   * 分页查询
   */
  public PageInfo<Position> list(PositionQuery query) {
    PageHelper.startPage(query.getPage(), query.getLimit());
    List<Position> list = positionMapper.selectList(
        new LambdaQueryWrapperX<Position>().eq(Position::getStatus, Status.ENABLE)
            .orderByAsc(Position::getOrderNum));
    return new PageInfo<>(list);
  }

  public AjaxResult add(Position position) {
    position.setId(SnowflakeIdWorker.getUUID());
    positionMapper.insert(position);
    return AjaxResult.success();
  }

  @Transactional(rollbackFor = Exception.class)
  public AjaxResult updateStatus(Long id, Character status) {
    Position entity = new Position();
    entity.setId(id);
    entity.setStatus(status);
    positionMapper.updateById(entity);
    return AjaxResult.success();
  }

  public AjaxResult delete(List<Long> ids) {
    positionMapper.deleteBatchIds(ids);
    return AjaxResult.success();


  }

  public Position selectById(Long id) {
    return positionMapper.selectById(id);
  }

  //  @Override
//  public Position selectByPrimaryKey(String id) {
//
//    return positionMapper.selectByPrimaryKey(id);
//
//  }
//
//
//  @Override
  public AjaxResult updateById(Position record) {
    positionMapper.updateById(record);
    return AjaxResult.success();
  }
//
//
//  /**
//   * 添加
//   */
//  @Override
//  public int insertSelective(Position record) {
//
//    //添加雪花主键id
//    record.setId(SnowflakeIdWorker.getUUID());
//
//    return positionMapper.insertSelective(record);
//  }
//
//
//  @Override
//  public int updateByExampleSelective(Position record, SysPositionExample example) {
//
//    return positionMapper.updateByExampleSelective(record, example);
//  }
//
//
//  @Override
//  public int updateByExample(Position record, SysPositionExample example) {
//
//    return positionMapper.updateByExample(record, example);
//  }
//
//  @Override
//  public List<Position> selectByExample(SysPositionExample example) {
//
//    return positionMapper.selectByExample(example);
//  }
//
//
//  @Override
//  public long countByExample(SysPositionExample example) {
//
//    return positionMapper.countByExample(example);
//  }
//
//
//  @Override
//  public int deleteByExample(SysPositionExample example) {
//
//    return positionMapper.deleteByExample(example);
//  }
//
//  /**
//   * 检查name
//   *
//   * @param position
//   * @return
//   */
//  public int checkNameUnique(Position position) {
//    SysPositionExample example = new SysPositionExample();
////    example.createCriteria().andPostNameEqualTo(position.getPostName());
//    List<Position> list = positionMapper.selectByExample(example);
//    return list.size();
//  }
//
//
//  /**
//   * 修改权限状态展示或者不展示
//   *
//   * @param record
//   * @return
//   */
//  public int updateVisible(Position record) {
//    return positionMapper.updateByPrimaryKeySelective(record);
//  }

}
