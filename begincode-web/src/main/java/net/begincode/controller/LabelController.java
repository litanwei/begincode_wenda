package net.begincode.controller;

import java.util.ArrayList;
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

    /**
     * 获取所有标签
     *
     * @return List
     */
    @RequestMapping("/getLabel")
    @ResponseBody
    public Object getLabel() {
        List<Label> list = new ArrayList<Label>();
        list = labelhandler.getAllLabel();
        return list;
    }


}
