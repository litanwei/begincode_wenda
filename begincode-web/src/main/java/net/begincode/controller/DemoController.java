package net.begincode.controller;

import java.util.List;


import net.begincode.core.handler.DemoHandler;
import net.begincode.core.model.Demo;
import net.begincode.core.param.DemoAddParam;
import net.begincode.core.support.AuthPassport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:AdminController.java
 * @Description:测试DEMO
 * @author Stay
 * @date 2016年8月19日
 */
@RequestMapping("/test")
@Controller
public class DemoController {

	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Resource
	DemoHandler demoHandler;

	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(DemoAddParam admin)
	{
		demoHandler.updateDemoById(admin);
		return "redirect:/test/list.htm";
	}


	/**
	 * 修改用户跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/modifySkip",method=RequestMethod.GET)
	public String modifySkip(Integer id,Model model)
	{
		if(id==null)
		{
			return "redirect:/test/list.htm";
		}
		Demo demo  = demoHandler.selectById(id);
		model.addAttribute("admin", demo);
		return "/modify";
	}

	/**
	 * 测试删除
	 * @param id
	 * @return
	 */
	@AuthPassport
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String deleteAdmin(@RequestParam(value="id") Integer id, HttpServletRequest request)
	{
		demoHandler.delDemo(id);
		logger.warn("删除用户"+id);
		return "redirect:/test/list.htm";

	}

	/**
	 * 测试增加
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAdmin(DemoAddParam admin)
	{
		logger.info("用户增加");
		demoHandler.addDemo(admin);
		return "redirect:/test/list.htm";
	}



	/**
	 * 测试查找集合
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String findAdminList(Model model)
	{
		List<Demo> list = demoHandler.selectAll();
		model.addAttribute("list", list);
		return "list";
	}

	/**
	 * 登录
	 * @param model
	 * @return
	 */
	public String loginForAdmin(Model model)
	{
		return "list";
	}

}
