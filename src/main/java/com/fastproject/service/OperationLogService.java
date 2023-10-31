package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.OperationLogMapper;
import com.fastproject.model.OperationLog;
import com.fastproject.model.request.query.LogQuery;
import com.fastproject.model.response.OperationLogResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author fastProject
 * @date 2023/6/18 22:55
 */
@Service
@RequiredArgsConstructor
public class OperationLogService {

  private final OperationLogMapper operationLogMapper;


  public PageInfo<OperationLog> list(LogQuery query) {

    PageHelper.startPage(query.getPage(), query.getLimit());
    List<OperationLog> operationLogs = operationLogMapper.selectList(
        new LambdaQueryWrapperX<OperationLog>()
            .likeIfPresent(OperationLog::getTitle, query.getTitle())
            .eqIfPresent(OperationLog::getOperator, query.getOperator())
            .orderByDesc(OperationLog::getCreateAt));

    return new PageInfo<>(operationLogs);
  }

  public int add(OperationLog operationLog) {
    return operationLogMapper.insert(operationLog);
  }
}
