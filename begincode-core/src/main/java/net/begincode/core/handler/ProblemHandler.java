package net.begincode.core.handler;

import net.begincode.bean.Page;
import net.begincode.common.BizException;
import net.begincode.core.enums.ProblemResponseEnum;
import net.begincode.core.httpclient.HttpUtil;
import net.begincode.core.model.*;
import net.begincode.core.service.*;
import net.begincode.utils.PatternUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Stay on 2016/8/26  21:41.
 */
@Component
public class ProblemHandler {
    @Resource
    private ProblemService problemService;
    @Resource
    private LabelService labelService;
    @Resource
    private MessageService messageService;
    @Resource
    private ProLabService proLabService;
    @Resource
    private AnswerService answerService;

    /**
     * 添加问题
     * 一个问题关联着   标签表(传入标签名集合)    消息表(如果传入的userId不为空 则存入消息表中)
     * 问题和标签对应的表
     *
     * @param problem 前台传入的问题
     * @param label   传入的标签对象  用于标签表的新增
     * @param userId  传入用户id集合  用于消息表的新增
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProblem(Problem problem, Label label, Integer[] userId) {
        Message message = new Message();
        //创建问题如果成功返回整数
        int problemNum = problemService.createProblem(problem);
        if (problemNum < 0) {
            throw new BizException(ProblemResponseEnum.PROBLEM_ADD_ERROR);
        }
        //发送http请求给搜索端
        HttpUtil.createIndexHttp(problem.getProblemId());
        Set<String> labelNameSet = PatternUtil.splitName(label.getLabelName());
        //拆解标签集合,并把对应的参数传入相关表中
        operateLabelNameSet(labelNameSet, problem);
        if (userId != null && userId.length == 1) {
            message.setBegincodeUserId(userId[0]);
            message.setProId(problem.getProblemId());
            messageService.createMessage(message);
        } else if (userId != null && userId.length > 1) {
            for (int i = 0; i < userId.length; i++) {
                //消息添加
                message.setBegincodeUserId(userId[i]);
                message.setProId(problem.getProblemId());
                messageService.createMessage(message);
            }
        }
    }


    /**
     * 传入问题得到对应的标签名集合
     *
     * @param problem
     * @return
     */
    private List problemToLabel(Problem problem) {
        List<String> list = new ArrayList<>();
        List<ProblemLabel> lt = proLabService.findByProblemId(problem.getProblemId());
        for (ProblemLabel problemLabel : lt) {
            list.add(labelService.selectById(problemLabel.getLabelId()).getLabelName());
        }
        return list;
    }


    /**
     * 查找新问题记录
     *
     * @return
     */
    public void selectNewProblems(Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findProblemsSize());    //问题总数
        List<Problem> problemList = problemService.findNewProblem(page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(problemList));
    }

    /**
     * 查找我的问题列表
     *
     * @param userName
     * @param page
     */
    public void selectMyProblems(String userName, Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findMyProblemSize(userName));    //问题总数
        List<Problem> list = problemService.findMyProblem(userName, page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(list));
    }


    /**
     * 查找热点问题
     *
     * @param page
     * @return
     */
    public void selectHotProblems(Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findHotProSize());    //问题总数
        List<Problem> problemList = problemService.findHotProblem(page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(problemList));
    }

    /**
     * 查找未回答的问题集合
     *
     * @return
     */
    public void selectNoAnswerProblems(Page<BizFrontProblem> page) {
        page.setTotalNum(problemService.findNoAnswerSize());    //问题总数
        List<Problem> problemList = problemService.findNoAnswerProblem(page.getCurrentNum(), page.getPageEachSize());
        page.setData(operatePage(problemList));
    }


    /**
     * 根据传入的问题实体 查找是否有回答的人名字
     *
     * @param problem
     * @return
     */
    public String selectOrderByProblemId(Problem problem) {
        Answer answer = answerService.findOrderByProblemId(problem.getProblemId());
        if (answer == null) {
            return "null";
        } else {
            return answer.getUserName();
        }
    }


    /**
     * 传入的labelName集合 判断是否有存在
     * 如果存在加入集合
     * 不存在先加入数据库 再加入集合
     *
     * @param labelNameSet
     * @return 集合标签
     */
    private void operateLabelNameSet(Set<String> labelNameSet, Problem problem) {
        Label label = new Label();
        ProblemLabel problemLabel = new ProblemLabel();
        if (labelNameSet != null && labelNameSet.size() > 0) {
            for (String labelName : labelNameSet) {
                Label seleLabel = labelService.selectByName(labelName);
                if (seleLabel != null) {
                    problemLabel.setLabelId(seleLabel.getLabelId());
                    problemLabel.setProblemId(problem.getProblemId());
                    proLabService.createProLab(problemLabel);
                } else {
                    label.setLabelName(labelName);
                    labelService.createLabel(label);
                    problemLabel.setLabelId(label.getLabelId());
                    problemLabel.setProblemId(problem.getProblemId());
                    proLabService.createProLab(problemLabel);
                }
            }
        }
    }

    /**
     * 根据id查询问题
     */
    public Problem selectById(int answerId) {
        return problemService.selProblemById(answerId);
    }

    /**
     * 传入一个问题列表返回一个分页 List 数据包
     *
     * @param problemList
     * @return
     */
    private List<BizFrontProblem> operatePage(List<Problem> problemList) {
        List<BizFrontProblem> list = new ArrayList<>();
        for (Problem problem : problemList) {
            BizFrontProblem bizFrontProblem = new BizFrontProblem();
            bizFrontProblem.setAnswerName(selectOrderByProblemId(problem));
            bizFrontProblem.setProblem(problem);
            bizFrontProblem.setLabelNameList(problemToLabel(problem));
            list.add(bizFrontProblem);
        }
        return list;
    }
    /**
     * 查找所有问题
     *
     * @return
     */
    public List<Problem> selectAllProblem() {
        return problemService.findProblemList();
    }

}
