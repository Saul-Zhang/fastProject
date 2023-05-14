package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.model.DictData;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author fastProject
 * @date 2023/4/20 23:00
 */
@Service
@RequiredArgsConstructor
public class DictCacheService {

  private final DictDataMapper dictDataMapper;

  private final Map<String, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>();

  private void loadLocalCache() {
    if (LOCAL_CACHE.isEmpty()) {
      synchronized (LOCAL_CACHE) {
        if (LOCAL_CACHE.isEmpty()) {
          LOCAL_CACHE.putAll(loadDictMap());
        }
      }
    }
  }

  public Map<String, Map<String, String>> loadDictMap() {
    return dictDataMapper.selectList(
            new LambdaQueryWrapperX<DictData>().eq(DictData::getStatus, "0"))
        .stream().collect(
            Collectors.groupingBy(DictData::getCode,
                Collectors.toMap(DictData::getValue, DictData::getLabel))
        );
  }

  public void clear() {
    LOCAL_CACHE.clear();
  }

  public String getData(String code, String value) {
    loadLocalCache();
    return Optional.ofNullable(LOCAL_CACHE.get(code)).map(m ->m.get(value)).orElse(null);
  }

  public Map<String,String> getDict(String code) {
    loadLocalCache();
    return Optional.ofNullable(LOCAL_CACHE.get(code)).orElse(new HashMap<>());
  }

}
