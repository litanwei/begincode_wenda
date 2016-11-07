package net.begincode.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.begincode.core.handler.UserHandler;

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

}
