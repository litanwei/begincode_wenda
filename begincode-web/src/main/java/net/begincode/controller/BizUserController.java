package net.begincode.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * 用于显示login中的jsp页面（PS：个人觉得可以建一个view的页面进行各种跳转显示）
	 * @param folder page下的子文件夹名
	 * @param pageName 对应的jsp文件名
	 * @return
	 */
	@RequestMapping(value="/view/{folder}/{pageName}")
	public String viewPage(@PathVariable(value = "folder") String folder,@PathVariable(value = "pageName") String pageName){
		return folder+"/"+pageName;
	}
	/*
	 * username登录用户
	 */
	@RequestMapping(value="/loginUser")
	public void login(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		BegincodeUser user=userHandler.selectByLoginname(username, password);
		if(user!=null){
			CookieOperation.addCookie(response, user);
			if(userHandler.getLoginUserMap().keySet().contains(username)){
				userHandler.getLoginUserMap().get(username).invalidate();//清空原有session
			}
			userHandler.getLoginUserMap().put(username, request.getSession());//添加新session
		}
		request.getSession().setAttribute("checkLogin", true);
	}
	/**
	 * 检查Session中是否存在checklogin判断登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkLogin")
	@ResponseBody
	public Object checkLogin(HttpServletRequest request){
		Object checkLogin=request.getSession().getAttribute("checkLogin");
		if(checkLogin==null){
			return false;
		}
		return true;
	}

}
