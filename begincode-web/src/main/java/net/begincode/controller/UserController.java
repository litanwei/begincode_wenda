package net.begincode.controller;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@Controller
public class UserController {

	@Resource
	UserHandler userHandler;
	/**
	 * qq查找或注册用户
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void findOrCreateUser(HttpServletResponse response, BegincodeUser user) {
		user.setUserSourceId(1);
		user.setDeleteFlag("1");
		user = userHandler.createUserAndFind(user);
		CookieOperation.addCookie(response, user);
	}
	/**
	 * qq注销用户
	 */
	@RequestMapping(value = "loginClean",method = RequestMethod.POST)
	public void cleanUser(HttpServletResponse response){
		CookieOperation.delCookie(response);
	}
}
