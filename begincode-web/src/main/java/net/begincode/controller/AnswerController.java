package net.begincode.controller;


import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.param.AnswerParam;
import net.begincode.core.support.AuthPassport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by saber on 2016/9/11.
 */
@RequestMapping("/answer")
@Controller
public class AnswerController {

    @Resource
    AnswerHandler answerHandler;
    @Resource
    AccountContext accountContext;
    @Resource
    ProblemHandler problemHandler;

    /**
     * 添加问题回复
     *
     * @param：answer,request
     * @return：
     */
    @AuthPassport
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    @ResponseBody
    public Object reply(AnswerParam answerParam, HttpServletRequest request) {
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        Answer answer = answerParam.getAnswer();
        answer.setBegincodeUserId(begincodeUser.getBegincodeUserId());
        answer.setUserName(begincodeUser.getNickname());
        return answerHandler.creatAnswer(answer);
    }

    /**
     * 回复反馈
     *
     * @param：answer,request
     * @return：
     */

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseBody
    public Object feedback(int answerId) {
        answerHandler.answerFeedback(answerId);
        return null;
    }

    /**
     * 回复采纳
     *
     * @param：answer,request
     * @return：
     */
    @AuthPassport
    @RequestMapping(value = "/answerAdopt", method = RequestMethod.POST)
    @ResponseBody
    public Object answerAdopt(int answerId, HttpServletRequest request) {
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        return  answerHandler.answerAdopt(answerId, begincodeUser.getBegincodeUserId());
    }
}
