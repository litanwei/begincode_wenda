package net.begincode.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
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
	@ResponseBody
	public Object loginUser(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		BegincodeUser user=userHandler.selectByLoginName(username, password);
		if(user!=null){//密码账号验证成功
			CookieOperation.addCookie(response, user);
			if(userHandler.getLoginUserMap().keySet().contains(username)){
				userHandler.getLoginUserMap().get(username).invalidate();//清空原有session
			}
			userHandler.getLoginUserMap().put(username, request.getSession());//添加新session
		}else{
			return false;
		}
		return true;
	}
	/**
	 * 检查Cookie中是否存在openid判断登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkLogin")
	@ResponseBody
	public Object checkLogin(HttpServletRequest request){
		String opend=getOpenId(request);
		if(opend==null){
			return false;
		}
		return true;
	}
	/**
	 * 用于返回用户的信息 ajax
	 * @return
	 */
	@RequestMapping(value="/userInfo")
	@ResponseBody
	public Object userInfo(HttpServletRequest request){
		BegincodeUser user=null;
		String opend=getOpenId(request);
		user=userHandler.selectByOpenId(opend);
		if(user==null){
			return null;
		}
		return user;
	}
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param session
	 * @return  成功返回true
	 */
	@RequestMapping(value="/registerUser")
	@ResponseBody
	public Object registerUser(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String nickname=request.getParameter("nickname");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String vcode=request.getParameter("vcode").toLowerCase();
		String checkvcode=request.getSession().getAttribute("imageCode").toString().toLowerCase();
		if(!vcode.equals(checkvcode)){
			return false;
		}
		if(userHandler.IsExistByRow("login_name", username)){
			return false;
		};
		if(userHandler.IsExistByRow("nickname", nickname)){
			return false;
		};
		if(userHandler.IsExistByRow("email", email)){
			return false;
		}
		String open_id="Ro"+RandomStringUtils.randomAlphanumeric(30);
		String access_token="Ra"+RandomStringUtils.randomAlphanumeric(30);
		BegincodeUser user=new BegincodeUser();
		user.setLoginName(username);
		user.setNickname(nickname);
		user.setPwd(password);
		user.setEmail(email);
		user.setCdate(new Date());
		user.setGag("1");
		user.setOpenId(open_id);
		user.setAccessToken(access_token);
		CookieOperation.addCookie(response, user);//添加cookie信息 用于@AuthPassport验证登录
		userHandler.getLoginUserMap().put(username, request.getSession()); //添加map 用于map储存session进行异地登录销毁
		userHandler.addUser(user);
		return true;
	}
	//request获得OpendId
	public String getOpenId(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals("openId")){
				return c.getValue();
			}
		}
		return null;
	}
}
