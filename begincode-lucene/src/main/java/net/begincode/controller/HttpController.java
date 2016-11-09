package net.begincode.controller;

import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.Problem;
import net.begincode.core.support.AuthPassport;
import net.begincode.utils.LuceneUtil;
import net.begincode.utils.PatternUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stay on 2016/10/7  15:45.
 */
@Controller
@RequestMapping(value="/http")
public class HttpController {
    @Resource
    private ProblemHandler problemHandler;

    @RequestMapping(value="/createReceive",method = RequestMethod.POST)
    @ResponseBody
    public Object httpReceive(Problem problem){
        Map map = new HashMap();
        Problem pro= problemHandler.selectById(problem.getProblemId());
        pro.setContent(PatternUtil.filterIndexContent(pro.getContent()));
        LuceneUtil.createIndex(pro);
        return map;
    }
}
