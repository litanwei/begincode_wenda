package net.begincode.controller;


import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.Problem;
import net.begincode.core.support.AuthPassport;
import net.begincode.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by saber on 2016/9/11.
 */
@RequestMapping("/problem")
@Controller
public class AnswerController {

    @Resource
    AnswerHandler answerHandler;
    @Resource
    AccountContext accountContext;
    @Resource
    ProblemHandler problemHandler;

    /**
     * 问题回复
     *@param：answer,request
     *@return：
     */
    @AuthPassport
    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    @ResponseBody
    public Map reply(Answer answer, HttpServletRequest request){
        Map map = new HashMap();
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        answer.setBegincodeUserId(begincodeUser.getBegincodeUserId());
        answer.setUserName(begincodeUser.getNickname());
        answerHandler.creatAnswer(answer);
        map.put("msg", "提交成功");
        return map;
    }


    /**
     * 查询问题和所有回复
     *@param：answer,model
     *@return：S
     */
    @RequestMapping(value = "/{problemid}",method = RequestMethod.GET)
    public String selectAllAnswer(Model model,@PathVariable("problemid") int problemid){
     Answer answer = new Answer();
        answer.setProblemId(problemid);
        List<Answer> answerList = answerHandler.selectAllByExample(answer);
        List<String> newTime = new ArrayList<>();
        for(int a = 0 ; a < answerList.size(); a++) {
            newTime.add(DateUtil.getTimeFormatText(answerList.get(a).getCreateTime()));
        }
        Problem problem  = problemHandler.selectById(problemid);
        String problemTime = DateUtil.getTimeFormatText(problem.getCreateTime());
        model.addAttribute("answerList", answerList);
        model.addAttribute("newTime", newTime);
        model.addAttribute("problem",problem);
        model.addAttribute("problemTime",problemTime);
        return "question_view";
    }

    /**
     * 回复反馈
     *@param：answer,request
     *@return：
     */
    @AuthPassport
    @RequestMapping(value = "/feedback",method = RequestMethod.POST)
    @ResponseBody
    public Map feedback(Answer answer){
        Map map = new HashMap();
        answerHandler.feedback(answer.getAnswerId());
        map.put("msg", "感谢您的反馈。");
        return map;
    }
}
