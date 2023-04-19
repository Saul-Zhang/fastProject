package com.fastproject.controller.admin;

import com.fastproject.common.base.BaseController;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.model.auto.SysArea;
import com.fastproject.model.auto.SysCity;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.service.SysAreaService;
import com.fastproject.service.SysCityService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 地区Controller
 * @ClassName: SysAreaController
 * @author fuce
 * @date 2019-11-20 22:34
 */
@Api(value = "地区设置")
@Controller
@RequestMapping("/SysAreaController")
public class SysAreaController extends BaseController{
	
	private String prefix = "admin/province/sysArea";
	@Autowired
	private SysAreaService sysAreaService;
	
	@Autowired
	private SysCityService sysCityService;
	
	/**
	 * 地区页面展示
	 * @param model
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:05:04
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@SaCheckPermission("gen:sysArea:view")
    public String view(ModelMap model)
    {
        return prefix + "/list";
    }
	
	/**
	 * list集合
	 * @param tablepar
	 * @param searchText
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:04:53
	 */
	//@Log(title = "地区设置集合查询", action = "111")
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/list")
	@SaCheckPermission("gen:sysArea:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText, SysArea area){
		PageInfo<SysArea> page=sysAreaService.list(tablepar,searchText, area) ;
		return pageTable(page.getList(),page.getTotal());
	}
	
	/**
     * 新增跳转
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
		List<SysCity> sysCities=sysCityService.selectByExample(null);
		modelMap.put("sysCities", sysCities);
        return prefix + "/add";
    }
	
    /**
     * 新增保存
     * @param sysArea
     * @return
     * @author fuce
     * @Date 2019年11月11日 下午4:04:41
     */
	//@Log(title = "地区设置新增", action = "111")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@SaCheckPermission("gen:sysArea:add")
	@ResponseBody
	public AjaxResult add(SysArea sysArea){
		int b=sysAreaService.insertSelective(sysArea);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 地区设置删除
	 * @param ids
	 * @return
	 */
	//@Log(title = "地区设置删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@SaCheckPermission("gen:sysArea:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysAreaService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查
	 * @param SysArea
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(SysArea sysArea){
		int b=sysAreaService.checkNameUnique(sysArea);
		if(b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("SysArea", sysAreaService.selectByPrimaryKey(id));
        List<SysCity> sysCities=sysCityService.selectByExample(null);
        mmap.put("sysCities", sysCities);
        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "地区设置修改", action = "111")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @SaCheckPermission("gen:sysArea:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysArea record)
    {
        return toAjax(sysAreaService.updateByPrimaryKeySelective(record));
    }

    
    

	
}
