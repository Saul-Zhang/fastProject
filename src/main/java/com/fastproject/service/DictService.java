package com.fastproject.service;

import com.fastproject.common.mybatis.LambdaQueryWrapperX;
import com.fastproject.mapper.DictDataMapper;
import com.fastproject.mapper.DictTypeMapper;
import com.fastproject.mapper.UserMapper;
import com.fastproject.model.DictData;
import com.fastproject.model.DictType;
import com.fastproject.model.User;
import com.fastproject.model.constant.Status;
import com.fastproject.satoken.SaTokenUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dict")
@RequiredArgsConstructor
public class DictService {

  private final DictDataMapper dictDataMapper;
  private final DictTypeMapper dictTypeMapper;
  private final UserMapper userMapper;

  private final Map<String, Map<String, String>> DICT_DATA_CACHE = new ConcurrentHashMap<>();


  public Map<String, Map<String, String>> loadDictMap() {
    return dictDataMapper.selectList(null)
        .stream()
        .peek(d -> {
          if (d.getIsUser()) {
            d.setValue(getUserIdByEmployeeId(d.getValue()));
          }
        })
        .collect(Collectors.groupingBy(DictData::getCode,
            Collectors.toMap(DictData::getValue, DictData::getLabel)
        ));
  }


  public void clear() {
    DICT_DATA_CACHE.clear();
  }

  public String getData(String code, String value) {
    loadLocalCache();
    // 处理销售经理
    if ("sales_manager".equals(code) && StringUtils.isNotBlank(value)) {
      return userMapper.selectById(Long.parseLong(value)).getRealName();
    }
    return Optional.ofNullable(DICT_DATA_CACHE.get(code)).map(m -> m.get(value)).orElse(null);
  }

  public Map<String, String> getDict(String code) {
    loadLocalCache();
    return Optional.ofNullable(DICT_DATA_CACHE.get(code)).orElse(new HashMap<>());
  }

  /**
   * 获取上级销售经理
   */
  public Map<String, String> getSalesManager() {
    Map<String, String> dict = getDict(SaTokenUtil.getUser().getEmployeeId());
    if (dict.isEmpty()) {
      dict = getDict("user");
    }
    return dict;
  }


  public boolean isUser(String code) {
    return dictDataMapper.selectList(
            new LambdaQueryWrapperX<DictData>().eq(DictData::getCode, code)).stream()
        .anyMatch(DictData::getIsUser);
  }

  private void loadLocalCache() {
    if (DICT_DATA_CACHE.isEmpty()) {
      synchronized (DICT_DATA_CACHE) {
        if (DICT_DATA_CACHE.isEmpty()) {
          DICT_DATA_CACHE.putAll(loadDictMap());
        }
      }
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateUserDictData(){
    dictDataMapper.delete(new LambdaQueryWrapperX<DictData>()
        .eq(DictData::getCode, "user"));
    userMapper.selectList(null).stream()
        .map(user -> new DictData().setIsUser(true).setCode("user").setLabel(user.getRealName())
            .setValue(user.getEmployeeId()).setStatus(Status.ENABLE)).forEach(dictDataMapper::insert);
    clear();
  }

  private String getUserIdByEmployeeId(String employeeId) {
    User user = userMapper.selectOne(
        new LambdaQueryWrapperX<User>().eq(User::getEmployeeId, employeeId));
    if (user != null) {
      return String.valueOf(user.getId());
    }
    return "";
  }


  /**
   * 根据字典类型查询字典数据信息
   *
   * @param dictType 字典类型
   * @return 参数键值
   */
  public List<DictData> getType(String dictType) {
//		TSysDictDataExample example=new TSysDictDataExample();
//		if(dictType!=null) {
//			example.createCriteria().andDictTypeEqualTo(dictType);
//			return dictDataMapper.selectByExample(example);
//		}
    return new ArrayList<DictData>();
  }

  /**
   * 根字典类型查询字典
   */
  public DictType getSysDictType(String dictType) {
//		TSysDictTypeExample dictTypeExample=new TSysDictTypeExample();
//		 dictTypeExample.createCriteria().andDictTypeEqualTo(dictType);
//		 List<DictType> dictTypes =dictTypeMapper.selectByExample(dictTypeExample);
//		 if(dictTypes !=null&& dictTypes.size()>0) {
//			 return dictTypes.get(0);
//		 }
    return null;

  }

  public List<DictType> getDictTypes() {
    return dictTypeMapper.selectList(null);
  }


}
