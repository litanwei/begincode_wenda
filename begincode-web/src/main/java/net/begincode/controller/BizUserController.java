package net.begincode.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.utils.ImageUtil;

@Controller
@RequestMapping("/user")
public class BizUserController {
	@Resource
	private UserHandler userHandler;
	@RequestMapping(value="/login_view")
	public String login_view(){
		return "login";
	}
	@RequestMapping(value="/register_view")
	public String register_view(){
		return "register";
	}
	@RequestMapping(value="/loginByUsername")
	@ResponseBody
	public Object loginByUsername(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		BegincodeUser user=new BegincodeUser();
		Map<Object, Object> reData=new HashMap<>();
		user=userHandler.selectUserByUsernamePassword(username, password);
		if(user==null){
			reData.put("error", "用户名或密码不正确");
			return reData;
		}
		CookieOperation.addCookie(response, user);
		reData.put("success", "登录成功");
		session.setAttribute("nickname", user.getNickname());
		return reData;
	}
	@RequestMapping(value="/registerByUsername")
	@ResponseBody
	public Object register(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String username=request.getParameter("username");
		String nickname=request.getParameter("nickname");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String vcode=request.getParameter("vcode").toLowerCase();
		String checkvcode=session.getAttribute("imageCode").toString().toLowerCase();
		Map<Object, Object> reData=new HashMap<>();
		if(!vcode.equals(checkvcode)){
			reData.put("error", "验证码错误");
			return reData;
		}
		//1.判读是否重复存在数据
		//2.存在直接返回不成功
		if(userHandler.selectCountIsExist("login_name", username)){
			reData.put("error", "用户名已存在");
			return reData;
		};
		if(userHandler.selectCountIsExist("nickname", nickname)){
			reData.put("error", "用户昵称已存在");
			return reData;
		};
		if(userHandler.selectCountIsExist("email", email)){
			reData.put("error", "邮箱已存在");
			return reData;
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
		CookieOperation.addCookie(response, user);
		userHandler.addUser(user);
		reData.put("success", "注册成功");
		return reData;
		//3.不存在进行数据库添加
		//可选：验证码验证
	}
	/*
	 * 生成验证码
	 */
	@RequestMapping("/valicode") 
	public void valicode(HttpServletResponse response,HttpSession session) throws Exception{  
	    //利用图片工具生成图片  
	    //第一个参数是生成的验证码，第二个参数是生成的图片  
	    Object[] objs = ImageUtil.createImage();  
	    //将验证码存入Session  
	    session.setAttribute("imageCode",objs[0]);  
	    //将图片输出给浏览器  
	    BufferedImage image = (BufferedImage) objs[1];  
	    response.setContentType("image/png");  
	    OutputStream os = response.getOutputStream();  
	    ImageIO.write(image, "png", os);  
	}  
	/*
	 * 获取登錄用户的信息
	 */
	@RequestMapping("/getLoginUser")
	@ResponseBody
	public Object checkLoginStatus(HttpServletRequest request,HttpSession session){
		String nickname=session.getAttribute("nickname").toString();
		Map<Object, Object> reData=new HashMap<>();
		reData.put("nickname", nickname);
		return reData;

	}
	
}
