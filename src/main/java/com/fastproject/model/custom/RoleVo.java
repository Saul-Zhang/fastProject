package com.fastproject.model.custom;

import com.fastproject.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色自定义数据
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleVo extends Role {
	private static final long serialVersionUID = 1L;
	/**
	 * 判断是否有这个权限
	 */
	private boolean checked;

	public RoleVo(String id, String name, boolean checked) {
		super(id, name);
		this.checked = checked;
	}
}
