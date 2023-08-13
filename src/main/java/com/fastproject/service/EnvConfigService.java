package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.EnvConfigMapper;
import com.fastproject.model.EnvConfig;
import com.fastproject.model.Role;
import com.fastproject.model.response.AjaxResult;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author fastProject
 * @date 2023/7/15 23:48
 */
@Service
@RequiredArgsConstructor
public class EnvConfigService {

  private final EnvConfigMapper envConfigMapper;

  public PageInfo<EnvConfig> list() {
    return new PageInfo<>(envConfigMapper.selectList(null));
  }

  public AjaxResult updateById(EnvConfig envConfig) {
    envConfigMapper.updateById(envConfig);
    return AjaxResult.success();
  }

  public boolean isMultiLevelAudit() {
    return "1".equals(envConfigMapper.selectOne(
            new LambdaQueryWrapperX<EnvConfig>().eq(EnvConfig::getCode, "MULTI_LEVEL_AUDIT_ENABLE"))
        .getValue());
  }
}
