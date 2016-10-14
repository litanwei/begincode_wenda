package net.begincode.controller;

import net.begincode.bean.Page;
import net.begincode.common.BeginCodeConstant;
import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.*;
import net.begincode.core.param.ProblemLabelParam;
import net.begincode.core.support.AuthPassport;
import net.begincode.core.handler.*;
import net.begincode.core.model.*;
import net.begincode.core.param.ProblemLabelParam;
import net.begincode.core.support.AuthPassport;
import net.begincode.core.httpclient.HttpUtil;
import net.begincode.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Stay on 2016/8/26  21:48.
 */
@RequestMapping("/problem")
@Controller
public class ProblemController {

    private Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Resource
    private AnswerHandler answerHandler;
    @Resource
    private ProblemHandler problemHandler;
    @Resource
    private AccountContext accountContext;
    @Resource
    private LabelHandler labelHandler;
    @Resource
    private MessageHandler messageHandler;

    @AuthPassport
    @RequestMapping("/create")
    public String problemSkip() {
        return "question_add";
    }

    /**
     * 主页新问题列表
     *
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/newProblems", method = RequestMethod.GET)
    @ResponseBody
    public Object findNewProblem(BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectNewProblems(page);
        return page;
    }

    /**
     * 热门问题列表
     *
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/hotProblems", method = RequestMethod.GET)
    @ResponseBody
    public Object findHotProblem(BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectHotProblems(page);
        return page;
    }

    /**
     * 查找未回答的问题列表
     *
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/noAnswerProblems", method = RequestMethod.GET)
    @ResponseBody
    public Object findNoAnswerProblem(BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectNoAnswerProblems(page);
        return page;
    }

    /**
     * 查找"我"的问题列表
     *
     * @param bizFrontProblem
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/myProblems", method = RequestMethod.POST)
    @ResponseBody
    public Object findMyProblem(BizFrontProblem bizFrontProblem, HttpServletRequest request) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        BegincodeUser user = accountContext.getCurrentUser(request);
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectMyProblems(user.getNickname(), page);
        return page;
    }



    /**
     * 添加问题
     * 关键字的保存(以逗号分隔)
     * 保存时正则获取问题的内容
     *
     * @param problemLableParam
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public void addProblem(ProblemLabelParam problemLableParam, HttpServletRequest request) {
        Problem problem = problemLableParam.getProblem();
        BegincodeUser user = accountContext.getCurrentUser(request);
        problem.setUserName(user.getNickname());
        problem.setBegincodeUserId(user.getBegincodeUserId());
        problem.setCreateTime(new Date());
        Label label = problemLableParam.getLabel();
        int problemId = problemHandler.addProblem(problem, label);
        messageHandler.createMessage(problemId,null,problem.getContent());
    }

    /**
     * 查询问题和所有回复
     *@param：answer,model
     *@return：S
     */
    @RequestMapping(value = "/{problemId}",method = RequestMethod.GET)
    public String selectAllAnswer(Model model, @PathVariable("problemId") int problemId){
        List<Answer> answerAdoptList = answerHandler.selAdoptAnswerByProblemId(problemId);
        List<Answer> answerNoAdoptList = answerHandler.selNoAdoptAnswerByProblemId(problemId);
        List<String> newAdoptTime = new ArrayList<>();
        for(int a = 0 ; a < answerAdoptList.size(); a++) {
            newAdoptTime.add(DateUtil.getTimeFormatText(answerAdoptList.get(a).getCreateTime()));
        }
        List<String> newNoAdoptTime = new ArrayList<>();
        for(int a = 0 ; a < answerNoAdoptList.size(); a++) {
            newNoAdoptTime.add(DateUtil.getTimeFormatText(answerNoAdoptList.get(a).getCreateTime()));
        }
        Problem problem  = problemHandler.selectById(problemId);
        String problemTime = DateUtil.getTimeFormatText(problem.getCreateTime());
        model.addAttribute("answerAdoptList", answerAdoptList);
        model.addAttribute("newAdoptTime", newAdoptTime);
        model.addAttribute("answerNoAdoptList", answerNoAdoptList);
        model.addAttribute("newNoAdoptTime", newNoAdoptTime);
        model.addAttribute("problem",problem);
        model.addAttribute("labels", labelHandler.getLabelByProblemId(problemId));
        model.addAttribute("problemTime",problemTime);
        return "question_view";
    }

}
