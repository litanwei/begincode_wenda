package net.begincode.controller;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户
 * @author kangliang
 *@date 2016年8月25日
 */
@RequestMapping("/user")
@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	UserHandler userHandler;
	
	/**
	 * 活跃用户
	 */
	@RequestMapping("activer")
	public @ResponseBody List<BegincodeUser> activeUser(){
		
			logger.debug("");
			List<BegincodeUser> list = userHandler.selectActiveUser();
			
		return list;
	}
	
}
