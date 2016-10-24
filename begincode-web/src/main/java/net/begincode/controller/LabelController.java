package net.begincode.controller;

import net.begincode.core.handler.LabelHandler;
import net.begincode.core.handler.ProblemLabelHandler;
import net.begincode.core.model.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import net.begincode.core.param.ProblemLabelParam;

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
     *
     * @return List
     */
    @RequestMapping("/getLabel")
    @ResponseBody
    public Object getLabel() {
        List<Label> list = new ArrayList<Label>();
        list = labelHandler.getAllLabel();
        return list;
    }

    /**
     * 查询标签下的问题
     */
    @RequestMapping("/selectProblemLabel")
    public String selectLabel(@RequestParam("id") Integer labelId, Model model) {

        List<ProblemLabelParam> proLabel = problemLabelHandler.getLabelByLabelId(labelId);
        model.addAttribute("proLabel", proLabel);
//		model.addAttribute("label",labelHandler.getLabelById(labelId));
        return "question_list";
    }


}
