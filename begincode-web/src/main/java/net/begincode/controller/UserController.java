package net.begincode.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;

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
	public String activeUser(Model model){
		logger.debug("用户查找");
		List<BegincodeUser> list = userHandler.selectActiveUser();
		model.addAttribute("list",list);
		return "index";
	}
	
}
