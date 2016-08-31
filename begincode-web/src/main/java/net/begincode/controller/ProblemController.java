package net.begincode.controller;

import net.begincode.core.handler.*;
import net.begincode.core.model.Label;
import net.begincode.core.model.Message;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.param.ProblemLableParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Stay on 2016/8/26  21:48.
 */
@RequestMapping("/problem")
@Controller
public class ProblemController {

    private Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Resource
    private UserHandler userHandler;

    @Resource
    private ProblemHandler problemHandler;
    @Resource
    private LabelHandler labelHandler;
    @Resource
    private MessageHandler messageHandler;
    @Resource
    private ProLabHandler proLabHandler;

    /**
     * 添加问题
     * 关键字的保存(以逗号分隔)
     *  保存时正则获取问题的内容
     * @param problemLableParam
     * @return
     */
    @RequestMapping(value="",method= RequestMethod.POST)
    @ResponseBody
    public Map addProblem(ProblemLableParam problemLableParam)
    {
        Problem problem = problemLableParam.getProblem();
        Label label = problemLableParam.getLabel();
        Message message = new Message();
        ProblemLabel problemLabel = new ProblemLabel();
        //问题
        problem.setCreateTime(new Date()); //问题创建时间
        problem.setUpdateTime(new Date());  //问题修改时间
        problemHandler.addProblem(problem);
        //过滤@后面的用户名 把html标签去掉
        Set<String> stringSet = problemLableParam.filterContent(problem.getContent());
        if(stringSet!=null && stringSet.size()>0){
            for(String nickName : stringSet){
                //消息
                message.setBegincodeUserId(problem.getBegincodeUserId());
                message.setProId(problem.getProblemId());
                messageHandler.addMessage(message);
            }
        }
        //标签
        //切割标签 返回被切割的标签集合
        Set<String> set = problemLableParam.splitLabelName(label.getLabelName());
        for(String labelName:set) {
            if(labelHandler.selectByLabelName(labelName)!=null) {  //检验是否是重名标签
                //问题和标签对应传入数据库
                problemLabel.setLabelId(labelHandler.selectByLabelName(labelName).getLabelId());
                problemLabel.setProblemId(problem.getProblemId());
                proLabHandler.addProLab(problemLabel);
            } else if(problemLableParam.checkLabelName(labelName)) {  //检验标签是否有特殊字符
                label.setLabelName(labelName);
                labelHandler.addLabel(label);
                //问题和标签对应传入数据库
                problemLabel.setLabelId(label.getLabelId());
                problemLabel.setProblemId(problem.getProblemId());
                proLabHandler.addProLab(problemLabel);
            }
        }

        Map<String,String> map = new HashMap<String,String>();
        map.put("msg","提交成功");
        return map;

    }
}
