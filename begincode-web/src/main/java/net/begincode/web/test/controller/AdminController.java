package net.begincode.web.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.begincode.core.mapper.AdminMapper;
import net.begincode.core.model.Admin;
import net.begincode.core.model.AdminExample;
import net.begincode.core.model.AdminExample.Criteria;

/**
 * @ClassName:AdminController.java
 * @Description:测试DEMO
 * @author Stay
 * @date 2016年8月19日
 */
@RequestMapping("/test")
@Controller
public class AdminController {

	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminMapper adminMapper;
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(Admin admin)
	{
		adminMapper.updateByPrimaryKeySelective(admin);
		
		return "redirect:/test/list";
	}
	
	
	/**
	 * 修改用户跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/modifySkip",method=RequestMethod.GET)
	public String modifySkip(@RequestParam(value="id") Integer id,Model model)
	{
		if(id==null)
		{
			return "redirect:/test/list";
		}
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<Admin> list= adminMapper.selectByExample(example);
		Admin ad = new Admin();
		ad = list.get(0);
		model.addAttribute("admin", ad);
		return "/test/modify";
	}
	
	/**
	 * 测试删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String deleteAdmin(@RequestParam(value="id") Integer id)
	{
		adminMapper.deleteByPrimaryKey(id);
		logger.warn("删除用户"+id);
		return "redirect:/test/list";
	}
	
	/**
	 * 测试增加
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAdmin(Admin admin)
	{
		logger.info("用户增加");
		adminMapper.insert(admin);
		return "redirect:/test/list";
	}
	
	
	
	/**
	 * 测试查找集合
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String findAdminList(Model model)
	{
		AdminExample adminExample = new AdminExample();
		List<Admin> list = adminMapper.selectByExample(adminExample);
		model.addAttribute("list", list);
		return "/test/list";
	}
	
	
	
}
