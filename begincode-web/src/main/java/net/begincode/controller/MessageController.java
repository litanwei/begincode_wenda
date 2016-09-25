package net.begincode.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.MessageHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.MessageRemind;
import net.begincode.core.support.AuthPassport;

@RequestMapping("/message")
@Controller
public class MessageController {
	@Resource
	private MessageHandler messageHandler;
	@Resource
	private AccountContext accountContext;
	
	/**
	 * 获取message信息
	 * @param request
	 * @param response
	 * @return
	 */
//	@AuthPassport
	@RequestMapping(value="/messageremind",method=RequestMethod.POST)
	@ResponseBody
	public Object getpage(HttpServletRequest request,HttpServletResponse response){
		/**
		 * user_id=当前用户id
		 * nowpage=当前页面
		 * pagesize=分页大小 handler 设置为15
		 */
//		try {
//			BegincodeUser begincodeUser=accountContext.getCurrentUser(request);
//		} catch (Exception e) {
//			System.out.println("错误的用户请求");
//			return "";
//		}
//		Integer user_id=begincodeUser.getBegincodeUserId();
		Integer user_id=null;
		Integer nowpage=null;
		Integer pagesize=null;
		List<MessageRemind> ls=messageHandler.selectByMessageRemind(1, nowpage, pagesize);
		return ls;
	}

}
