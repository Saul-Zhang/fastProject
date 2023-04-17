package com.fc.v2.controller.gen;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.auto.Test;
import com.fc.v2.model.custom.Tablepar;
import com.fc.v2.service.TestService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试表Controller
 *
 * @author fuce
 * @date 2023-04-05 17:13:51
 */
@Api(value = "测试表")
@Controller
@RequestMapping("/TestController")
@RequiredArgsConstructor
public class TestController extends BaseController {

  private final String prefix = "gen/test";

  private final TestService testService;


  /**
   * 测试表页面展示
   *
   * @param model
   * @return String
   * @author fuce
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("gen:test:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

  /**
   * list集合
   *
   * @param tablepar
   * @param searchText
   * @return
   */
  //@Log(title = "测试表", action = "111")
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/list")
  @SaCheckPermission("gen:test:list")
  @ResponseBody
  public ResultTable list(Tablepar tablepar, Test test) {
    PageInfo<Test> page = testService.list(tablepar, test);
    return pageTable(page.getList(), page.getTotal());
  }

  /**
   * 新增跳转
   */
  @ApiOperation(value = "新增跳转", notes = "新增跳转")
  @GetMapping("/add")
  public String add(ModelMap modelMap) {
    return prefix + "/add";
  }

  /**
   * 新增保存
   *
   * @param
   * @return
   */
  //@Log(title = "测试表新增", action = "111")
  @ApiOperation(value = "新增", notes = "新增")
  @PostMapping("/add")
  @SaCheckPermission("gen:test:add")
  @ResponseBody
  public AjaxResult add(Test test) {
    int b = testService.insertSelective(test);
    if (b > 0) {
      return success();
    } else {
      return error();
    }
  }

  /**
   * 测试表删除
   *
   * @param ids
   * @return
   */
  //@Log(title = "测试表删除", action = "111")
  @ApiOperation(value = "删除", notes = "删除")
  @DeleteMapping("/remove")
  @SaCheckPermission("gen:test:remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    int b = testService.deleteByPrimaryKey(ids);
    if (b > 0) {
      return success();
    } else {
      return error();
    }
  }


  /**
   * 修改跳转
   *
   * @param id
   * @param mmap
   * @return
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") String id, ModelMap map) {
    map.put("Test", testService.selectByPrimaryKey(id));

    return prefix + "/edit";
  }

  /**
   * 修改保存
   */
  //@Log(title = "测试表修改", action = "111")
  @ApiOperation(value = "修改保存", notes = "修改保存")
  @SaCheckPermission("gen:test:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(Test test) {
    return toAjax(testService.updateByPrimaryKeySelective(test));
  }


  /**
   * 修改状态
   *
   * @param record
   * @return
   */
  @PutMapping("/updateVisible")
  @ResponseBody
  public AjaxResult updateVisible(@RequestBody Test test) {
    int i = testService.updateVisible(test);
    return toAjax(i);
  }
}
