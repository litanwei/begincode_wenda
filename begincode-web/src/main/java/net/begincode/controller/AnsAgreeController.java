package net.begincode.controller;

import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.AnsAgreeHandler;
import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.support.AuthPassport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by saber on 2016/11/5.
 */
@Controller
@RequestMapping("/ansAgree")
public class AnsAgreeController {

    @Resource
    private AnsAgreeHandler ansAgreeHandler;
    @Resource
    private AccountContext accountContext;


    /**
     *更改当前用户点击赞同按钮的问题的agree
     *
     * @param：answerId,agreeFlag，request
     * @return:Object
     * */
    @ResponseBody
    @RequestMapping(value = "/setAgree", method = RequestMethod.POST)
    @AuthPassport
    public void setAnswerAgreeFlag(int answerId,int agreeFlag,HttpServletRequest request){
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        AnsAgree ansAgree = new AnsAgree();
        ansAgree.setAnswerId(answerId);
        ansAgree.setAgree(agreeFlag);
        ansAgree.setBegincodeUserId(begincodeUser.getBegincodeUserId());
        ansAgreeHandler.updateAnswerAgrCountAndAnsAgree(ansAgree);
    }


    /**
     *更改当前用户点击反对按钮的问题的agree
     *
     * @param：answerId,agreeFlag，request
     * @return:Object
     * */
    @ResponseBody
    @RequestMapping(value = "/setOpposition", method = RequestMethod.POST)
    @AuthPassport
    public Object setAnswerOppositionFlag(int answerId,int agreeFlag,HttpServletRequest request){
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        AnsAgree ansAgree = new AnsAgree();
        ansAgree.setAnswerId(answerId);
        ansAgree.setAgree(agreeFlag);
        ansAgree.setBegincodeUserId(begincodeUser.getBegincodeUserId());
        ansAgreeHandler.updateAnswerOppoCountAndAnsAgree(ansAgree);
        return ansAgree;
    }
}
