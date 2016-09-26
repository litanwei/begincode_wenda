package net.begincode.controller;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.handler.LabelHandler;
import net.begincode.core.model.Label;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/label")
@Controller
public class LabelController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private LabelHandler labelhandler;

	public @ResponseBody
	List<Label> getLabel() {

		logger.info("开始查询标签");

		return null;
	}
}
