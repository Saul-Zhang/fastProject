package com.fastproject.service;

import cn.hutool.json.JSONUtil;
import com.fastproject.mapper.GeneratorMapper;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.model.custom.TsysTables;
import com.fastproject.model.custom.autocode.BeanColumn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author fastProject
 */
@Service
@RequiredArgsConstructor
public class GeneratorService {

  private final GeneratorMapper generatorMapper;

  /**
   * 分页查询
   */
  public PageInfo<TsysTables> list(Tablepar tablepar, String searchText) {
    PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
    List<TsysTables> list = generatorMapper.queryList(searchText);
    return new PageInfo<>(list);
  }


  /**
   * 查询具体某表信息
   */
  public List<TsysTables> queryList(String tableName) {
    return generatorMapper.queryList(tableName);
  }

  /**
   * 查询表详情
   */
  public List<BeanColumn> queryColumns2(String tableName) {
    System.out.println(
        "queryColumns2>>>" + JSONUtil.toJsonPrettyStr(generatorMapper.queryColumns3(tableName)));
    return generatorMapper.queryColumns2(tableName);
  }

}
