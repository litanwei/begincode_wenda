package net.begincode.controller;

import net.begincode.bean.Page;
import net.begincode.core.enums.CollectEnum;
import net.begincode.core.enums.VoteEnum;
import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.*;
import net.begincode.core.param.ProblemLabelParam;
import net.begincode.core.support.AuthPassport;
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
import java.util.ArrayList;
import java.util.Date;
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
    private AccountContext accountContext;


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
        problemHandler.selectMyProblems(user.getBegincodeUserId(), page);
        return page;
    }


    /**
     * 添加问题
     * 关键字的保存(以逗号分隔)
     * 保存时正则获取问题的内容
     *
     * @param problemLableParam
     * @param request
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    @ResponseBody
    public Object addProblem(ProblemLabelParam problemLableParam, HttpServletRequest request) {
        Problem problem = problemLableParam.getProblem();
        BegincodeUser user = accountContext.getCurrentUser(request);
        problem.setUserName(user.getNickname());
        problem.setBegincodeUserId(user.getBegincodeUserId());
        problem.setCreateTime(new Date());
        problemHandler.addProblem(problem, problemLableParam.getLabel());
        return 1;
    }


    /**
     * 查询问题和所有回复
     *
     * @param model
     * @param problemId
     * @param request
     * @return
     */
    @RequestMapping(value = "/{problemId}", method = RequestMethod.GET)
    public String selectAllAnswer(Model model, @PathVariable("problemId") int problemId, HttpServletRequest request) {
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        fillProblem(model, problemId, begincodeUser);
        return "question_view";
    }

    /**
     * @param model
     * @param problemId
     * @param request
     * @return
     * @ 我的 问题入口
     */
    @AuthPassport
    @RequestMapping(value = "/message/{problemId}", method = RequestMethod.GET)
    public String messageProblem(Model model, @PathVariable("problemId") int problemId, HttpServletRequest request) {
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        fillProblem(model, problemId, begincodeUser);
        problemHandler.updateMessageByProblemId(begincodeUser.getBegincodeUserId(), problemId);
        return "question_view";
    }

    /**
     * @param model
     * @param problemId
     * @param answerId
     * @param request
     * @return
     * @ 我的回答入口
     */
    @AuthPassport
    @RequestMapping(value = "/answer/{answerId}/{problemId}", method = RequestMethod.GET)
    public String messageAnswer(Model model, @PathVariable(value = "problemId") int problemId, @PathVariable(value = "answerId") int answerId, HttpServletRequest request) {
        BegincodeUser begincodeUser = problemHandler.getCurrentUser(request);
        fillProblem(model, problemId, begincodeUser);
        problemHandler.updateMessageByAnswerId(begincodeUser.getBegincodeUserId(), answerId);
        return "question_view";
    }


    /**
     * 填充问题详情界面
     *
     * @param model
     * @param problemId
     * @param begincodeUser
     */
    private void fillProblem(Model model, int problemId, BegincodeUser begincodeUser) {
        List<Answer> answerAdoptList = new ArrayList<>();
        List<Answer> answerNoAdoptList = new ArrayList<>();
        List<String> newAdoptTime = new ArrayList<>();
        List<String> newNoAdoptTime = new ArrayList<>();
        List<Integer> answerAdoptAgreeFlag = new ArrayList<>();
        List<Integer> answerNoAdoptAgreeFlag = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        //如果是用户进来 则判断用户所是否有收藏或投票此问题
        StringBuffer problemTime = new StringBuffer();
        Problem problem = problemHandler.selectProblemAndAnswerdsById(problemId, labels, problemTime, begincodeUser,
                answerAdoptList, answerNoAdoptList,
                newAdoptTime, newNoAdoptTime,
                answerAdoptAgreeFlag, answerNoAdoptAgreeFlag);
        if (begincodeUser != null) {
            model.addAttribute("proAttention", fillProAttention(begincodeUser, problem));
            if (answerAdoptList.size() != 0 || answerNoAdoptList.size() != 0) {
                model.addAttribute("answerAdoptAgreeFlag", answerAdoptAgreeFlag);
                model.addAttribute("answerNoAdoptAgreeFlag", answerNoAdoptAgreeFlag);
            }
        }
        //采纳回复
        model.addAttribute("answerAdoptList", answerAdoptList);
        model.addAttribute("newAdoptTime", newAdoptTime);
        //未采纳回复
        model.addAttribute("answerNoAdoptList", answerNoAdoptList);
        model.addAttribute("newNoAdoptTime", newNoAdoptTime);
        //问题 标签
        model.addAttribute("problem", problem);
        model.addAttribute("labels", labels);
        model.addAttribute("problemTime", problemTime);
    }

    /**
     * 传入用户 和 问题 实体 首先查找map中有无数据 再查找数据库
     *
     * @param begincodeUser
     * @param problem
     * @return
     */
    private ProAttention fillProAttention(BegincodeUser begincodeUser, Problem problem) {
        //此时 如果map中有数据 说明还没有进入数据库中
        if (problemHandler.getMapCollValue(begincodeUser.getBegincodeUserId() + "-" + problem.getProblemId()) == null && problemHandler.getMapVoteValue(begincodeUser.getBegincodeUserId() + "-" + problem.getProblemId()) == null) {
            return problemHandler.selectProAttById(problem.getProblemId(), begincodeUser.getBegincodeUserId());
        } else {
            Integer collState = problemHandler.getMapCollValue(begincodeUser.getBegincodeUserId() + "-" + problem.getProblemId());
            Integer voteState = problemHandler.getMapVoteValue(begincodeUser.getBegincodeUserId() + "-" + problem.getProblemId());
            ProAttention proAttention = new ProAttention();
            if (collState == null) {
                proAttention.setCollect(problemHandler.selectProAttById(problem.getProblemId(), begincodeUser.getBegincodeUserId()).getCollect());
            } else {
                //当map中有数据的时候 就说明此数据还未进入数据库
                proAttention.setCollect(collState);
                Integer collCount = problem.getCollectCount();
                if (collState == Integer.parseInt(CollectEnum.COLLECT.getCode())) {
                    problem.setCollectCount(collCount + 1);
                }
            }
            if (voteState == null) {
                proAttention.setVote(problemHandler.selectProAttById(problem.getProblemId(), begincodeUser.getBegincodeUserId()).getVote());
            } else {
                proAttention.setVote(voteState);
                Integer voteCount = problem.getVoteCount();
                if (voteState == Integer.parseInt(VoteEnum.VOTE.getCode())) {
                    problem.setVoteCount(voteCount + 1);
                }
            }
            return proAttention;
        }
    }

    /**
     * 收藏状态改变
     *
     * @param request
     * @param problemId
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/collect/{problemId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateColl(HttpServletRequest request, @PathVariable(value = "problemId") Integer problemId) {
        BegincodeUser user = problemHandler.getCurrentUser(request);
        Integer begincodeUserId = user.getBegincodeUserId();
        problemHandler.initCollMap(problemId, begincodeUserId);  //初始化map  如果数据库没有数据 则先存map里
        return problemHandler.changeCollMap(begincodeUserId + "-" + problemId);   //改变map里的状态
    }

    /**
     * 投票状态改变
     *
     * @param request
     * @param problemId
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/vote/{problemId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateVote(HttpServletRequest request, @PathVariable(value = "problemId") Integer problemId) {
        BegincodeUser user = problemHandler.getCurrentUser(request);
        Integer begincodeUserId = user.getBegincodeUserId();
        problemHandler.initVoteMap(problemId, begincodeUserId);   //初始化map  如果数据库中没有数据 则先存map里
        return problemHandler.changVoteMap(begincodeUserId + "-" + problemId);  //改变map里的状态
    }

    /**
     * 问题浏览增加队列
     *
     * @param problemId
     * @return
     */
    @RequestMapping(value = "/view/{problemId}", method = RequestMethod.GET)
    @ResponseBody
    public Object updateView(@PathVariable(value = "problemId") int problemId) {
        return problemHandler.addViewQueue(problemId);
    }

}
