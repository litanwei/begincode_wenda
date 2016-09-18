package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.ProblemResponseEnum;
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
        ProblemLabel problemLabel = new ProblemLabel();
        Message message = new Message();
        //创建问题如果成功返回整数
        int problemNum = problemService.createProblem(problem);
        if (problemNum < 0) {
            throw new BizException(ProblemResponseEnum.PROBLEM_ADD_ERROR);
        }
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
     * 传入问题集合 返回一个map
     * map里面有对应的问题id 和一个list数组  数组里面有问题id所对应的标签名
     * 如:  当问题号为1时 对应的value是(spring,springmvc,框架)
     *
     * @param list
     * @return
     */
    public Map problemToLabel(List<Problem> list) {
        Map<Integer, ArrayList<String>> map = new HashMap<>();
        for (Problem problem : list) {
            ArrayList<String> array = new ArrayList<>();
            List<ProblemLabel> lt = proLabService.findByProblemId(problem.getProblemId());
            for (ProblemLabel problemLabel : lt) {
                array.add(labelService.selectById(problemLabel.getLabelId()).getLabelName());
            }
            map.put(problem.getProblemId(), array);
        }
        return map;
    }

    /**
     * 传入问题集合 返回对应的问题数集合
     *
     * @param list
     * @return
     */
    public List<Integer> problemToAnswerSize(List<Problem> list) {
        ArrayList<Integer> lt = new ArrayList<>();
        for (Problem problem : list) {
            lt.add(answerService.findByProblemIdNum(problem.getProblemId()));
        }
        return lt;
    }


    /**
     * 查找后15条问题记录
     *
     * @return
     */
    public List<Problem> selectNewProblems() {
        return problemService.findNewProblem();
    }

    /**
     * 根据用户名查找问题
     *
     * @param userName
     * @return 返回问题集合
     */
    public List<Problem> selectMyProblems(String userName) {
        return problemService.findMyProblem(userName);
    }

    /**
     * 查找创建时间最近的15条问题集合
     *
     * @return
     */
    public List<Problem> selectHotProblems() {
        return problemService.findHotProblem();
    }

    /**
     * 查找问题集合
     *
     * @return
     */
    public List<Problem> selectAllProblem() {
        return problemService.findAllProblem();
    }

    /**
     * 根据传入的问题集合查找对应的回答 并排序 取创建时间最近的回答
     * 依次加入集合
     *
     * @param list
     * @return
     */
    public List<Answer> selectOrderByProblemId(List<Problem> list) {
        List<Answer> answerList = new ArrayList<Answer>();
        for (Problem problem : list) {
            answerList.add(answerService.findOrderByProblemId(problem.getProblemId()));
        }
        return answerList;
    }

    /**
     * 查找未回答的问题集合
     *
     * @return
     */
    public List<Problem> selectNoAnswerProblems() {
        return problemService.findNoAnswerProblem();
    }

    /**
     * 通过userId 查找@该用户的问题
     *
     * @param userId
     * @return
     */
    public List<Problem> selectByUserIdWithMessage(Integer userId) {
        return problemService.selectByUserIdWithMessage(userId);
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


}
