package net.begincode.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;

@RequestMapping("/user")
@Controller
public class BizUserController {
	@Resource
	private UserHandler userHandler;

	/**
	 * 用于显示login中的jsp页面（PS：个人觉得可以建一个viewController的页面进行各种跳转显示）
	 * 
	 * @param folder
	 *            page下的子文件夹名
	 * @param pageName
	 *            对应的jsp文件名
	 * @return
	 */
	@RequestMapping(value = "/view/{folder}/{pageName}")
	public String viewPage(@PathVariable("folder") String folder, @PathVariable(value = "pageName") String pageName) {
		return folder + "/" + pageName;
	}

	/**
	 * 用于返回用户的信息 ajax
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userInfo",method = RequestMethod.POST)
	@ResponseBody
	public Object userInfo(HttpServletRequest request) {
		BegincodeUser user = null;
		String opend = getOpenId(request);
		user = userHandler.selectByOpenId(opend);
		if (user == null) {
			return null;
		}
		return user;
	}

	/**
	 * 登录
	 * 
	 * @param loginType
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login/loginUser/{loginType}")
	@ResponseBody
	public Object login(@PathVariable(value = "loginType") String loginType, HttpServletRequest request,
			HttpServletResponse response) {
		if (loginType.equals("client")) {
			return loginByClient(request, response);
		}
		return false;
	}

	/**
	 * 注册
	 * 
	 * @param registerType
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login/registerUser/{registerType}",method = RequestMethod.POST)
	@ResponseBody
	public Object register(@PathVariable("registerType") String registerType, HttpServletRequest request,
			HttpServletResponse response) {
		if (registerType.equals("client")) { // 客户端注册
			return registerByClient(request, response);
		}
		return false;
	}

	/**
	 * QQ注册和登录
	 * 
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login/loginUser/QQ",method = RequestMethod.POST)
	@ResponseBody
	public Object loginByQQ(HttpServletResponse response, BegincodeUser user) {
		user.setUserSourceId(1);
		user.setDeleteFlag("1");
		user = userHandler.createUserAndFind(user);
		CookieOperation.addCookie(response, user);
		return true;
	}

	/**
	 * 检查登录状态 cookie 检查在页面JS检查
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check/checkLogin")
	@ResponseBody
	public Object checkLoginStatus(HttpServletRequest request) {
		if (CookieOperation.getUser(request) != null) {
			return true;
		}
		return false;
	}

	// -------------------------------------------分割线-------------------------------------

	// 处理client注册
	public Object registerByClient(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String vcode = request.getParameter("vcode").toLowerCase();
		String checkvcode = request.getSession().getAttribute("imageVcode").toString().toLowerCase();
		if (!vcode.equals(checkvcode)) {
			return false;
		}
		if (userHandler.IsExistByRow("login_name", username)) {
			return false;
		}
		;
		if (userHandler.IsExistByRow("nickname", nickname)) {
			return false;
		}
		;
		if (userHandler.IsExistByRow("email", email)) {
			return false;
		}
		String open_id = "Ro" + RandomStringUtils.randomAlphanumeric(30);
		String access_token = "Ra" + RandomStringUtils.randomAlphanumeric(30);
		BegincodeUser user = new BegincodeUser();
		user.setLoginName(username);
		user.setNickname(nickname);
		user.setPwd(password);
		user.setEmail(email);
		user.setCdate(new Date());
		user.setGag("1");
		user.setOpenId(open_id);
		user.setAccessToken(access_token);
		userHandler.addUser(user);
		CookieOperation.addCookie(response, user);
		return true;
	}

	// 处理client登录
	public Object loginByClient(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BegincodeUser user = userHandler.selectByLoginName(username, password);
		if (user == null) {// 登录失败
			return false;
		}
		CookieOperation.addCookie(response, user);
		return true;
	}

	// request获得OpendId
	public String getOpenId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("openId")) {
				return c.getValue();
			}
		}
		return null;
	}
}
