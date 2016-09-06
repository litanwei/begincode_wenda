package net.begincode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.begincode.core.handler.MessageHandler;
import net.begincode.core.model.MessageRemind;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Resource
	private MessageHandler messageHandler;
	
	@RequestMapping(value="/messageremind")
	@ResponseBody
	public Map<String, Object> getpage(HttpServletRequest request,HttpServletResponse response){
		/**
		 * user_id=当前用户id
		 * nowpage=当前页面
		 * pagesize=分页大小 handler 设置为15
		 */
		Integer user_id=null;
		Integer nowpage=null;
		Integer pagesize=null;
		List<MessageRemind> ls=messageHandler.selectByMessageRemind(1, nowpage, pagesize);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("data", ls);
		return map;
	}
	
}
