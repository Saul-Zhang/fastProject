package com.fastproject.mapper;

import com.fastproject.model.User;
import com.fastproject.model.custom.TsysTables;
import com.fastproject.model.custom.autocode.BeanColumn;
import java.util.List;
import java.util.Map;


public interface GeneratorMapper {

  /**
   * 查询当前所有表
   *
   * @param tableName 条件搜索
   * @return
   */
  List<TsysTables> queryList(String tableName);

  /**
   * 查询具体某表信息
   *
   * @param tableName
   * @return
   */
  TsysTables queryTable(String tableName);

  /**
   * 查询表详情
   *
   * @param tableName
   * @return
   */
  List<Map<String, String>> queryColumns(String tableName);

  /**
   * 查询表详情
   *
   * @param tableName
   * @return
   */
  List<BeanColumn> queryColumns2(String tableName);

  List<Map<String, String>> queryColumns3(String tableName);

  /**
   * @author fuce
   * @ClassName: TsysUserDao
   * @date 2018年8月25日
   */
  interface TsysUserMapper {

    /**
     * 根据用户名字查询用户
     *
     * @param username
     * @return
     */
    public User queryUserName(String username);

    /**
     * 查询用户详情 String name 如果没用 注解@Param("") 它到mapper里面为_parameter
     *
     * @return
     * @author fuce
     * @Date 2020年12月6日 下午9:02:20
     */
    public List<User> queryUserInfo(String username);
  }
}
