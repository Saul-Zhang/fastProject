package com.fc.v2.satoken;

import com.fc.v2.model.auto.User;
import com.fc.v2.util.BeanUtils;

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

		Object object = StpUtil.getSession().get("user");
		if (object != null) {
			User user = new User();
			BeanUtils.copyBeanProp(user, object);
			return user;
		}
		return null;
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
	public static String getUserId() {
		return StpUtil.getLoginIdAsString();
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
	 * 
	 * @return
	 * @author fuce
	 * @Date 2019年11月21日 上午9:58:26
	 */
	public static String getIp() {
		return StpUtil.getTokenSession().getString("login_ip");
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 * @author fuce
	 * @Date 2019年11月21日 上午9:58:26
	 */
	public static boolean isLogin() {
		return StpUtil.isLogin();
	}

}
