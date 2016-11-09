package net.begincode.controller;


import net.begincode.core.enums.AgreeEnum;
import net.begincode.core.handler.*;
import net.begincode.core.model.AnsAgree;
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
    @Resource
    MessageHandler messageHandler;
    @Resource
    AnsAgreeHandler ansAgreeHandler;

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
        answer = answerHandler.creatAnswer(answer);
        messageHandler.createMessage(null, answer.getAnswerId(), answer.getContent());
        return answer;
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
        answerHandler.feedbackAnswer(answerId);
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
        AnsAgree ansAgree = new AnsAgree();
        ansAgree.setAgree(Integer.parseInt(AgreeEnum.AGREE.getCode()));
        ansAgree.setBegincodeUserId(begincodeUser.getBegincodeUserId());
        ansAgree.setAnswerId(answerId);
        ansAgreeHandler.selectAndUpdate(ansAgree);
        return answerHandler.adoptAnswer(answerId, begincodeUser.getBegincodeUserId());
    }
}
