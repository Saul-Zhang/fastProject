package com.fastproject.common.base;

import cn.hutool.core.util.StrUtil;
import com.fastproject.common.conf.FastProperties;
import com.fastproject.common.domain.AjaxResult;
import com.fastproject.common.domain.ResultTree;
import com.fastproject.common.domain.ResultTable;
import com.fastproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * web层通用数据处理
* @ClassName: BaseController
* @author fuce
* @date 2018年8月18日
*
 */
@Controller
public class BaseController
{
	//系统用户
	@Autowired
	public UserService sysUserService;
	
	//系统角色
	@Autowired
	public SysRoleService sysRoleService; 
	
	//权限
	@Autowired
	public PermissionService permissionService;

	//日志操作
	@Autowired
	public SysOperLogService sysOperLogService;
	//公告
	@Autowired
	public NoticeService noticeService;

    /**
     * 文件上传
     */
	@Autowired
	public SysFileService sysFileService;
	//配置文件
	@Autowired
	public FastProperties fastProperties;

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }
    

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(int code, String message)
    {
        return AjaxResult.error(code, message);
    }
    
    /**
     * 返回object数据
     */
    public AjaxResult retobject(int code, Object  data)
    {
        return AjaxResult.successData(code, data);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
    	return StrUtil.format("redirect:{}", url);
    }




/**
     * Describe: 返回 Tree 数据
     * Param data
     * Return Tree数据
     * */
    protected  static ResultTree dataTree(Object data){
         ResultTree resultTree = new ResultTree();
         resultTree.setData(data);
         return resultTree;
    }
    /**
     * Describe: 返回数据表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable pageTable(Object data, long count){
        return ResultTable.pageTable(count,data);
    }

    /**
     * Describe: 返回数据表格数据
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable dataTable(Object data){
        return ResultTable.dataTable(data);
    }

    /**
     * Describe: 返回树状表格数据 分页
     * Param data
     * Return 表格分页数据
     * */
    protected  static ResultTable treeTable(Object data){
        return ResultTable.dataTable(data);
    }
    
    
    
   
}
