package net.begincode.controller;

import net.begincode.bean.Page;
import net.begincode.core.handler.*;
import net.begincode.core.model.*;
import net.begincode.core.param.ProblemLabelParam;
import net.begincode.core.support.AuthPassport;
import net.begincode.utils.DateUtil;
import net.begincode.utils.PatternUtil;
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
    private UserHandler userHandler;
    @Resource
    private ProblemHandler problemHandler;
    @Resource
    private AccountContext accountContext;
    @Resource
    private LabelHandler labelHandler;
    @Resource
    private CountMapHandler countMapHandler;
    @Resource
    private ProAttentionHandler proAttentionHandler;


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
    @ResponseBody
    public Object addProblem(ProblemLabelParam problemLableParam, HttpServletRequest request) {
        Map map = new HashMap();
        Problem problem = problemLableParam.getProblem();
        BegincodeUser user = accountContext.getCurrentUser(request);
        problem.setUserName(user.getNickname());
        problem.setBegincodeUserId(user.getBegincodeUserId());
        problem.setCreateTime(new Date());
        Label label = problemLableParam.getLabel();
        Integer[] userId = contentFilter(problem.getContent());   //过滤@后面的用户名 把html标签去掉
        problemHandler.addProblem(problem, label, userId);
        return map;
    }


    /**
     * 传进的问题过滤出@ 后面的nickName 返回该用户的id
     *
     * @param content 传入的内容
     * @return 用户id数组
     */
    private Integer[] contentFilter(String content) {
        Set<String> stringSet = PatternUtil.filterNickName(content);
        int i = 0;
        Integer[] userId = new Integer[stringSet.size()];
        if (stringSet != null && stringSet.size() > 0) {
            for (String nickName : stringSet) {
                BegincodeUser begincodeUser = userHandler.selectByNickName(nickName.replace("@", ""));
                if (begincodeUser == null) {
                    continue;
                } else {
                    userId[i] = begincodeUser.getBegincodeUserId();
                    i++;
                }
            }
        }
        return userId;
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
        List<Answer> answerAdoptList = answerHandler.selAdoptAnswerByProblemId(problemId);
        List<Answer> answerNoAdoptList = answerHandler.selNoAdoptAnswerByProblemId(problemId);
        List<String> newAdoptTime = new ArrayList<>();
        for (int a = 0; a < answerAdoptList.size(); a++) {
            newAdoptTime.add(DateUtil.getTimeFormatText(answerAdoptList.get(a).getCreateTime()));
        }
        List<String> newNoAdoptTime = new ArrayList<>();
        for (int a = 0; a < answerNoAdoptList.size(); a++) {
            newNoAdoptTime.add(DateUtil.getTimeFormatText(answerNoAdoptList.get(a).getCreateTime()));
        }
        Problem problem = problemHandler.selectById(problemId);
        if (begincodeUser != null) {
            model.addAttribute("proAttention", setProAttention(begincodeUser, problem));
        }
        //如果是用户进来 则判断用户所是否有收藏或投票此问题
        String problemTime = DateUtil.getTimeFormatText(problem.getCreateTime());
        model.addAttribute("answerAdoptList", answerAdoptList);
        model.addAttribute("newAdoptTime", newAdoptTime);
        model.addAttribute("answerNoAdoptList", answerNoAdoptList);
        model.addAttribute("newNoAdoptTime", newNoAdoptTime);
        model.addAttribute("problem", problem);
        model.addAttribute("labels", labelHandler.getLabelByProblemId(problemId));
        model.addAttribute("problemTime", problemTime);
        return "question_view";
    }

    /**
     * 传进用户实体 和问题实体 返回对应的收藏 或者 投票状态
     * 这两个状态 我们从map中取  取得时候还要强制更新队列 使map为最新获取的值
     * 取得value就是用户最近的收藏 或者 投票状态
     *
     * @param begincodeUser
     * @param problem
     * @return
     */
    private ProAttention setProAttention(BegincodeUser begincodeUser, Problem problem) {
        ProAttention proAttention = countMapHandler.findOrCreateProAtt(problem.getProblemId(), begincodeUser.getBegincodeUserId());
        countMapHandler.updateVoteCollQueue();  //更新队列中的数据进map
        //此时 如果map中有数据 说明还没有进入数据库中
        if (countMapHandler.getMapVoteValueByKey(proAttention.getProAttentionId()) != null) {
            Integer vote = countMapHandler.getMapVoteValueByKey(proAttention.getProAttentionId());
            proAttention.setVote(vote);
            if (vote == 1) {
                Integer newVoteCount = problem.getVoteCount() + 1;
                problem.setVoteCount(newVoteCount);
            } else if (vote == 0 && proAttention.getVote() == 1) {
                Integer newVoteCount = problem.getVoteCount() - 1;
                problem.setVoteCount(newVoteCount);
            }
        }
        if (countMapHandler.getMapCollValueByKey(proAttention.getProAttentionId()) != null) {
            Integer collect = countMapHandler.getMapCollValueByKey(proAttention.getProAttentionId());
            if (collect != null) {
                proAttention.setCollect(collect);
                if (collect == 1) {
                    Integer newCollectCount = problem.getCollectCount() + 1;
                    problem.setCollectCount(newCollectCount);
                } else if (collect == 0 && proAttention.getCollect() == 1) {
                    Integer newCollectCount = problem.getCollectCount() - 1;
                    problem.setCollectCount(newCollectCount);
                }
            }
        }
        return proAttention;
    }


}
