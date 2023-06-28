package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.DictType;
import com.fastproject.model.constant.Status;
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
  private final DictTypeMapper dictTypeMapper;

  private final Map<String, Map<String, String>> DICT_DATA_CACHE = new ConcurrentHashMap<>();
  private final Map<String, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>();

  private void loadLocalCache() {
    if (DICT_DATA_CACHE.isEmpty()) {
      synchronized (DICT_DATA_CACHE) {
        if (DICT_DATA_CACHE.isEmpty()) {
          DICT_DATA_CACHE.putAll(loadDictMap());
        }
      }
    }
  }

  public Map<String, Map<String, String>> loadDictMap() {
    return dictDataMapper.selectList(
            new LambdaQueryWrapperX<DictData>().eq(DictData::getStatus, Status.ENABLE))
        .stream().collect(Collectors.groupingBy(DictData::getCode,
            Collectors.toMap(DictData::getValue, DictData::getLabel)
        ));
  }


  public void clear() {
    DICT_DATA_CACHE.clear();
  }

  public String getData(String code, String value) {
    loadLocalCache();
    return Optional.ofNullable(DICT_DATA_CACHE.get(code)).map(m -> m.get(value)).orElse(null);
  }

  public Map<String, String> getDict(String code) {
    loadLocalCache();
    return Optional.ofNullable(DICT_DATA_CACHE.get(code)).orElse(new HashMap<>());
  }
}
