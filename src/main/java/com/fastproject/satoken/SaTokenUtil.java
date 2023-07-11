package com.fastproject.satoken;

import com.fastproject.model.User;
import com.fastproject.util.BeanUtils;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 封装 Sa-Token 常用操作
 * 
 * @author kong
 *
 */
public class SaTokenUtil {

	/**
	 * 获取登录用户model
	 */
	public static User getUser() {

//		Object object = StpUtil.getSession().get("user");
//		if (object != null) {
//			User user = new User();
//			BeanUtils.copyBeanProp(user, object);
//			return user;
//		}
		return (User) StpUtil.getSession().get("user");
	}

	/**
	 * set用户
	 */
	public static void setUser(User user) {
		StpUtil.getSession().set("user", user);
	}

	/**
	 * 获取登录用户id
	 */
	public static Long getUserId() {
		return StpUtil.getLoginIdAsLong();
	}

	/**
	 * 获取登录用户name
	 */
	public static String getLoginName() {
		User user = getUser();
		if (user == null) {
			throw new RuntimeException("用户不存在！");
		}
		return user.getUsername();
	}

	/**
	 * 获取登录用户ip
	 */
	public static String getIp() {
		return StpUtil.getTokenSession().getString("login_ip");
	}

	/**
	 * 判断是否登录
	 * 
	 */
	public static boolean isLogin() {
		return StpUtil.isLogin();
	}

}
