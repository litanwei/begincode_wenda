package net.begincode.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	
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
	@AuthPassport
	@RequestMapping(value="/messageremind",method=RequestMethod.POST)
	@ResponseBody
	public Object getPage(HttpServletRequest request,HttpServletResponse response){
		/**
		 * user_id=当前用户id
		 * nowpage=当前页面
		 * pagesize=分页大小 默认设置为15
		 */
		BegincodeUser begincodeUser;
		try {
			begincodeUser=accountContext.getCurrentUser(request);
		} catch (Exception e) {
			System.out.println("错误的用户请求");
			return "";
		}
		Integer user_id=begincodeUser.getBegincodeUserId();
		Integer nowpage=Integer.valueOf(request.getParameter("nowpage"));
		Integer pagesize=null;//这里的null可以通过值传递分页大小 否则就用默认10页 在handler控制默认分页大小
		List<MessageRemind> ls=messageHandler.selectByMessageRemind(user_id, nowpage, pagesize);
		return ls;
	}
	/*
	 * 改变message表的已读状态
	 */
	@AuthPassport
	@RequestMapping(value = "/{message_id}",method = RequestMethod.POST)
	public void monitoringMessageClick(@PathVariable("message_id") int message_id){
		try {
			messageHandler.updatemessagedelete(message_id);
		} catch (Exception e) {
			logger.warn("message表已读状态修改出错");
		}
	}

	/**
	 * 获得message总数据数
	 * @param request
	 * @return
	 */
	@AuthPassport
	@RequestMapping(value="/pagesize",method=RequestMethod.POST)
	@ResponseBody
	public Object countMessage(HttpServletRequest request){
		BegincodeUser begincodeUser;
		begincodeUser=accountContext.getCurrentUser(request);
		return messageHandler.countByMessageRemind(begincodeUser.getBegincodeUserId());
	}
}
