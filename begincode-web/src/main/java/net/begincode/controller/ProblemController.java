package net.begincode.controller;

import net.begincode.core.handler.LabelHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;
import net.begincode.core.param.ProblemLableParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Stay on 2016/8/26  21:48.
 */
@RequestMapping("/problem")
@Controller
public class ProblemController {

    private Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Resource
    private ProblemHandler problemHandler;
    @Resource
    private LabelHandler labelHandler;

    /**
     * 添加问题
     * 关键字的保存(以逗号分隔)
     *  保存时正则获取问题的内容
     * @param problem  前台传入的问题
     * @param label   前台传入的标签
     */
    @RequestMapping(value="/addProblem",method= RequestMethod.POST)
    @ResponseBody
    public void addProblem(Problem problem,Label label)
    {
        ProblemLableParam problemLableParam = new ProblemLableParam(problem,label);
        List<String> list = problemLableParam.setLabelName(label.getLabelName());
        for(String labelName:list) {
            if(labelHandler.selectByLabelName(labelName)) {
                continue;
            } else if(problemLableParam.CheckLabelName(labelName))
            {
                    problemLableParam.setLabelName(labelName);
                    labelHandler.addLabel(label);
            }
        }
        //问题创建时间 自动赋值
        problemLableParam.setProblemCreateTime();
    }
}
