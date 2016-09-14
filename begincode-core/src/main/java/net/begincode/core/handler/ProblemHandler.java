package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.ProblemResponseEnum;
import net.begincode.core.model.Label;
import net.begincode.core.model.Message;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.service.LabelService;
import net.begincode.core.service.MessageService;
import net.begincode.core.service.ProLabService;
import net.begincode.core.service.ProblemService;
import net.begincode.utils.PatternUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        //拆解标签集合
        List<Label> list = operateLabelNameSet(labelNameSet);
        for (Label lb : list) {
            problemLabel.setLabelId(lb.getLabelId());
            problemLabel.setProblemId(problem.getProblemId());
            proLabService.createProLab(problemLabel);
        }
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
     * 查找问题集合
     *
     * @return
     */
    public List<Problem> selectAllProblem() {
        return problemService.findAllProblem();
    }


    /**
     * 传入的labelName集合 判断是否有存在
     * 如果存在加入集合
     * 不存在先加入数据库 再加入集合
     *
     * @param labelNameSet
     * @return 集合标签
     */
    private List<Label> operateLabelNameSet(Set<String> labelNameSet) {
        ArrayList<Label> list = new ArrayList<Label>();
        Label label = new Label();
        if (labelNameSet != null && labelNameSet.size() > 0) {
            for (String labelName : labelNameSet) {
                Label seleLabel = labelService.selectByName(labelName);
                if (seleLabel != null) {
                    list.add(seleLabel);
                } else {
                    label.setLabelName(labelName);
                    labelService.createLabel(label);
                    list.add(label);
                }
            }
        }
        return list;
    }

}
