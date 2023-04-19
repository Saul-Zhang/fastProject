package com.fastproject.model.custom;

import com.fastproject.model.auto.Permission;

import java.util.List;

/**
 * 权限树
 * @author fuce 
 * @date: 2018年9月8日 下午6:40:29
 */
public class PermissionTreeModelVo {
	private Permission permission;
	
	List<PermissionTreeModelVo> childList;//子类

	public Permission getTsysPermission() {
		return permission;
	}

	public void setTsysPermission(Permission permission) {
		this.permission = permission;
	}

	public List<PermissionTreeModelVo> getChildList() {
		return childList;
	}

	public void setChildList(List<PermissionTreeModelVo> childList) {
		this.childList = childList;
	}

	public PermissionTreeModelVo(Permission permission,
								 List<PermissionTreeModelVo> childList) {
		super();
		this.permission = permission;
		this.childList = childList;
	}

	public PermissionTreeModelVo() {
		super();
	}
	

	

	
	
}
