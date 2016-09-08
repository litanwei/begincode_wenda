package net.begincode.core.cookie;

import net.begincode.core.model.BegincodeUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CookieOperation
 * @Description: cookie操作
 * @author yangsj
 * @date 2015年8月4日 下午11:13:16
 *
 */
public class CookieOperation {

	/**
	 * @Title: addCookie
	 * @Description: 新增cookie，保存用户信息
	 * @param response
	 * @param user void
	 * @throws
	 */
	public static void addCookie(HttpServletResponse response, BegincodeUser user){
		Cookie accessToken = new Cookie("accessToken",user.getAccessToken());
		Cookie openId = new Cookie("openId",user.getOpenId());
		Cookie check = new Cookie("check",user.getCheckFlag());
		Cookie userId = new Cookie("userId",user.getBegincodeUserId()+"");
		accessToken.setPath("/");
		openId.setPath("/");
		check.setPath("/");
		userId.setPath("/");
		response.addCookie(accessToken);
		response.addCookie(openId);
		response.addCookie(check);
		response.addCookie(userId);

	}
	/**
	 * @Title: delCookie
	 * @Description: 删除cookie,设置立刻超时
	 * @param response void
	 * @throws
	 */
	public static void delCookie(HttpServletResponse response){

		Cookie accessToken = new Cookie("accessToken", null);
		Cookie openId = new Cookie("openId",null);
		Cookie check = new Cookie("check",null);
		Cookie userId = new Cookie("userId",null);
		accessToken.setMaxAge(0);
		openId.setMaxAge(0);
		check.setMaxAge(0);
		userId.setMaxAge(0);
		accessToken.setPath("/");
		openId.setPath("/");
		check.setPath("/");
		userId.setPath("/");
		response.addCookie(accessToken);
		response.addCookie(openId);
		response.addCookie(check);
		response.addCookie(userId);
	}


	/**
	 * @Title: getUser
	 * @Description: 获取登录人的信息
	 * @param request
	 * @return BegincodeUser
	 * @throws
	 */
	public static BegincodeUser getUser(HttpServletRequest request){
		Map<String,String> map = getCookie(request);
		if(map != null && !map.get("userId").equals("")){
			BegincodeUser user = new BegincodeUser();
			user.setBegincodeUserId(Integer.valueOf(map.get("userId")));
			return user;
		}else{
			return null;
		}
	}

	/**
	 * @Title: getCookie
	 * @Description: 获取cookie并返回map
	 * @param request
	 * @return Map<String,String>
	 * @throws
	 */
	public static Map<String,String> getCookie(HttpServletRequest request){

		int exist = 0;
		Map<String,String> cookieMap = new HashMap();

		Cookie[] cookies = request.getCookies();//获取cookie
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("accessToken")){
					cookieMap.put("accessToken",  cookie.getValue());
					exist++;
				}
				if(cookie.getName().equals("openId")){
					cookieMap.put("openId", cookie.getValue());
					exist++;
				}
				if(cookie.getName().equals("userId")){
					cookieMap.put("userId", cookie.getValue());
				}
				if(cookie.getName().equals("check")){
					cookieMap.put("check", cookie.getValue());
				}
			}
		}else{
			return null;
		}
		if(exist == 2){
			return cookieMap;
		}else{
			return null;
		}

	}
}
