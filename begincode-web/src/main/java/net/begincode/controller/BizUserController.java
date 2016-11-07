package net.begincode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class BizUserController {
	@RequestMapping(value="/view_login")
	public String view_log(){
		return "login/login";
	}
	@RequestMapping("/view_register")
	public String view_reg(){
		return "login/register";
	}

}
