package com.fastproject.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.fastproject.model.Position;
import com.fastproject.model.request.query.PositionQuery;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResponse;
import com.fastproject.service.PositionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/PositionController")
@Api(value = "岗位表")
public class PositionController {

  private final String prefix = "view/position";
  @Autowired
  private PositionService positionService;

  /**
   * list展示
   */
  @ApiOperation(value = "分页跳转", notes = "分页跳转")
  @GetMapping("/view")
  @SaCheckPermission("system:position:view")
  public String view(ModelMap model) {
    return prefix + "/list";
  }

  /**
   * 分页集合
   */
  //@Log(title = "岗位表集合查询", action = "111")
  @ApiOperation(value = "分页查询", notes = "分页查询")
  @GetMapping("/list")
  @SaCheckPermission("system:position:list")
  @ResponseBody
  public PageResponse list(PositionQuery query) {
    PageInfo<Position> page = positionService.list(query);
    return PageResponse.page(page.getList(), page.getTotal());
  }

  /**
   * 新增跳转
   */
  @GetMapping("/add")
  public String add(ModelMap modelMap) {
    return prefix + "/add";
  }

  /**
   * 新增
   */
  //@Log(title = "岗位表新增", action = "111")
  @PostMapping("add")
  @SaCheckPermission("system:position:add")
  @ResponseBody
  public AjaxResult add(Position position) {
    return positionService.add(position);
  }

  /**
   * 删除岗位
   */
  //@Log(title = "岗位表删除", action = "111")
  @ApiOperation(value = "删除", notes = "删除")
  @DeleteMapping("/remove")
  @SaCheckPermission("system:position:remove")
  @ResponseBody
  public AjaxResult remove(@RequestParam List<Long> ids) {
    return positionService.delete(ids);
  }


  /**
   * 修改跳转
   */
  @ApiOperation(value = "修改跳转", notes = "修改跳转")
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
    modelMap.put("position", positionService.selectById(id));
    return prefix + "/edit";
  }

  /**
   * 修改保存
   */
  //@Log(title = "岗位表修改", action = "111")
  @ApiOperation(value = "修改保存", notes = "修改保存")
  @SaCheckPermission("system:position:edit")
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(Position record) {
    return positionService.updateById(record);
  }

  /**
   * 修改状态
   */
  @PutMapping("/updateStatus")
  @ResponseBody
  public AjaxResult updateStatus(@RequestParam Long id, Character status) {
    return positionService.updateStatus(id, status);
  }
}
