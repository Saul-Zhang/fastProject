package com.fastproject.controller.admin;

import com.fastproject.common.base.BaseController;
import com.fastproject.model.response.AjaxResult;
import com.fastproject.model.response.PageResult;
import com.fastproject.model.Position;
import com.fastproject.model.custom.Tablepar;
import com.fastproject.service.PositionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/SysPositionController")
@Api(value = "岗位表")
public class SysPositionController extends BaseController{
	
	private final String prefix = "admin/sysPosition";
	@Autowired
	private PositionService positionService;
	
	/**
	 * list展示
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@SaCheckPermission("gen:sysPosition:view")
    public String view(ModelMap model)
    {
		return prefix + "/list";
    }
	
	/**
	 * 分页集合
	 */
	//@Log(title = "岗位表集合查询", action = "111")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@PostMapping("/list")
	@SaCheckPermission("gen:sysPosition:list")
	@ResponseBody
	public PageResult list(Tablepar tablepar, String searchText){
		PageInfo<Position> page= positionService.list(tablepar,searchText) ;
		return pageTable(page.getList(),page.getTotal());
	}
	
	/**
     * 新增跳转
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	
    /**
     * 新增
     */
	//@Log(title = "岗位表新增", action = "111")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("add")
	@SaCheckPermission("gen:sysPosition:add")
	@ResponseBody
	public AjaxResult add(Position position){
		int b= positionService.insertSelective(position);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	//@Log(title = "岗位表删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@SaCheckPermission("gen:sysPosition:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b= positionService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查用户
	 * @param tsysUser
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(Position position){
		int b= positionService.checkNameUnique(position);
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
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("SysPosition", positionService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "岗位表修改", action = "111")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @SaCheckPermission("gen:sysPosition:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Position record)
    {
        return toAjax(positionService.updateByPrimaryKeySelective(record));
    }

    
    /**
   	 * 根据主键查询
   	 * 
   	 * @param id
   	 * @param mmap
   	 * @return
   	 */
   	@PostMapping("/get/{id}")
   	@ApiOperation(value = "根据id查询唯一", notes = "根据id查询唯一")
   	public Position edit(@PathVariable("id") String id) {
   		return positionService.selectByPrimaryKey(id);
   	}


	/**
	 * 修改状态
	 * @param record
	 * @return
	 */
    @PutMapping("/updateVisible")
	@ResponseBody
    public AjaxResult updateVisible(@RequestBody Position record){
		int i= positionService.updateVisible(record);
		 return toAjax(i);
	}



	
}
