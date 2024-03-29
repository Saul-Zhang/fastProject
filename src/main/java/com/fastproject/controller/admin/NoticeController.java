package com.fastproject.controller.admin;

import com.fastproject.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "公告")
@Controller
@RequestMapping("/SysNoticeController")
public class NoticeController {

  private final String prefix = "view/sysNotice";
  @Autowired
  private NoticeService noticeService;

//	/**
//	 * 展示页面跳转
//	 * @param model
//	 * @return
//	 * @author fuce
//	 * @Date 2019年11月11日 下午4:09:24
//	 */
//	@ApiOperation(value = "分页跳转", notes = "分页跳转")
//	@GetMapping("/view")
//	@SaCheckPermission("gen:sysNotice:view")
//    public String view(ModelMap model)
//    {
//		return prefix + "/list";
//    }
//	/**
//	 * list页面
//	 * @param tablepar
//	 * @param searchText
//	 * @return
//	 * @author fuce
//	 * @Date 2019年11月11日 下午4:09:35
//	 */
//	//@Log(title = "公告集合查询", action = "111")
//	@ApiOperation(value = "分页查询", notes = "分页查询")
//	@GetMapping("/list")
//	@SaCheckPermission("gen:sysNotice:list")
//	@ResponseBody
//	public PageResult list(Tablepar tablepar, String searchText){
//		PageInfo<Notice> page= noticeService.list(tablepar,searchText) ;
//		return pageTable(page.getList(),page.getTotal());
//	}
//
//
//	/**
//	 * 对应的用户的展示页面
//	 * @param model
//	 * @return
//	 * @author fuce
//	 * @Date 2019年11月11日 下午4:09:42
//	 */
//	@ApiOperation(value = "对应的用户的展示页面", notes = "对应的用户的展示页面")
//	@GetMapping("/viewUser")
//    public String viewUser(ModelMap model)
//    {
//		return prefix + "/list_view";
//    }
//	/**
//	 * 根据公告id查询跳转到公告详情页面
//	 * @param tablepar
//	 * @param searchText
//	 * @return
//	 */
//	@ApiOperation(value = "table根据公告id查询跳转到公告详情页面", notes = "table根据公告id查询跳转到公告详情页面")
//	@PostMapping("/viewUserlist")
//	@ResponseBody
//    public PageResult viewUserlist(Tablepar tablepar,String searchText)
//    {
//		PageInfo<Notice> page= noticeService.list(SaTokenUtil.getUser(), tablepar, searchText);
//		return pageTable(page.getList(),page.getTotal());
//    }
//
//	/**
//	 * 新增跳转
//	 * @param modelMap
//	 * @return
//	 */
//	@ApiOperation(value = "新增跳转", notes = "新增跳转")
//    @GetMapping("/add")
//    public String add(ModelMap modelMap)
//    {
//        return prefix + "/add";
//    }
//	/**
//	 * 新增保存
//	 * @param notice
//	 * @return
//	 * @author fuce
//	 * @Date 2019年11月11日 下午4:07:09
//	 */
//	//@Log(title = "公告新增", action = "111")
//    @ApiOperation(value = "新增", notes = "新增")
//	@PostMapping("/add")
//	@SaCheckPermission("gen:sysNotice:add")
//	@ResponseBody
//	public AjaxResult add(Notice notice){
//		int b= noticeService.insertSelective(notice);
//		if(b>0){
//			return success();
//		}else{
//			return error();
//		}
//	}
//
//	/**
//	 * 删除
//	 * @param ids
//	 * @return
//	 **/
//	//@Log(title = "公告删除", action = "111")
//	@ApiOperation(value = "删除", notes = "删除")
//	@DeleteMapping("/remove")
//	@SaCheckPermission("gen:sysNotice:remove")
//	@ResponseBody
//	public AjaxResult remove(String ids){
//		int b= noticeService.deleteByPrimaryKey(ids);
//		if(b>0){
//			return success();
//		}else{
//			return error();
//		}
//	}
//
//	/**
//	 * 检查
//	 * @param tsysUser
//	 * @return
//	 */
//	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
//	@PostMapping("/checkNameUnique")
//	@ResponseBody
//	public int checkNameUnique(Notice notice){
//		int b= noticeService.checkNameUnique(notice);
//		if(b>0){
//			return 1;
//		}else{
//			return 0;
//		}
//	}
//
//	/**
//	 * 根据公告id查询跳转到公告详情页面
//	 * @param id
//	 * @param mmap
//	 * @return
//	 */
//	//@Log(title = "字典数据表删除", action = "1")
//	@ApiOperation(value = "根据公告id查询跳转到公告详情页面", notes = " 根据公告id查询跳转到公告详情页面")
//	@GetMapping("/viewinfo/{id}")
//    public String viewinfo(@PathVariable("id") String id,ModelMap mmap)
//    {
//		Notice notice= noticeService.selectByPrimaryKey(id);
//		mmap.addAttribute("notice", notice);
//		//把推送给该用户的公告设置为已读
//		noticeService.editUserState(id);
//        return prefix + "/view";
//    }
//
//
//	/**
//	 * 修改跳转
//	 * @param id
//	 * @param mmap
//	 * @return
//	 */
//	@ApiOperation(value = "修改跳转", notes = "修改跳转")
//	@GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") String id, ModelMap mmap)
//    {
//        mmap.put("SysNotice", noticeService.selectByPrimaryKey(id));
//
//        return prefix + "/edit";
//    }
//
//	/**
//     * 修改保存
//     */
//    //@Log(title = "公告修改", action = "111")
//	@ApiOperation(value = "修改保存", notes = "修改保存")
//    @SaCheckPermission("gen:sysNotice:edit")
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(Notice record)
//    {
//        return toAjax(noticeService.updateByPrimaryKeySelective(record));
//    }


}
