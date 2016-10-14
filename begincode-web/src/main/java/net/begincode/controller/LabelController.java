package net.begincode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.handler.LabelHandler;
import net.begincode.core.handler.ProblemLabelHandler;
import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/label")
@Controller
public class LabelController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private LabelHandler labelHandler;
	
	@Resource
	private ProblemLabelHandler problemLabelHandler;
	

	/**
	 * 获取所有标签
	 * @return List
	 */
	@RequestMapping("/getLabel")
	public @ResponseBody
	List<Label> getLabel() {

		List<Label> list = new ArrayList<Label>();
		logger.info("开始查询标签");
		list = labelHandler.getAllLabel();
		logger.info("查询标签完毕");
		return list;
	}
	
	@RequestMapping("/selectProblemLabel")
	public String selectLabel(@RequestParam("id") Integer labelId, Model model) {
		
		List<Problem> proLabel = problemLabelHandler.getLabelByLabelId(labelId);
		model.addAttribute("proLabel",proLabel);
		model.addAttribute("label",labelHandler.getLabelById(labelId));
		return "question_list";
	}
	
	
}
