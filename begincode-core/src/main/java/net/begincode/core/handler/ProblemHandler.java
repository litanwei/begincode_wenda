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
     * @param problem       前台传入的问题
     * @param llabel 传入的标签对象  用于标签表的新增
     * @param userId        传入用户id集合  用于消息表的新增
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProblem(Problem problem, Label label, Integer[] userId) {
        ProblemLabel problemLabel = new ProblemLabel();
        Message message = new Message();
        problem.setCreateTime(new Date()); //问题创建时间
        problem.setUpdateTime(new Date());  //问题修改时间
        //判断问题标题是否为空
        if (problem.getTitle().trim() == "") {
            throw new BizException(ProblemResponseEnum.PROBLEM_ADD_ERROR);
        }
        //创建问题如果成功返回整数
        int problemNum = problemService.createProblem(problem);
        if (problemNum < 0) {
            throw new BizException(ProblemResponseEnum.PROBLEM_ADD_ERROR);
        }

        Set<String> labelNameList = PatternUtil.splitName(label.getLabelName());
        //拆解标签集合
        if (labelNameList != null && labelNameList.size() > 0) {
            for (String labelName : labelNameList) {
                Label seleLabel = labelService.selectByName(labelName);
                //查询标签重名
                if (seleLabel == null) {
                    label.setLabelName(labelName);
                    labelService.createLabel(label);
                    problemLabel.setLabelId(label.getLabelId());
                    problemLabel.setProblemId(problem.getProblemId());
                    proLabService.createProLab(problemLabel);           //标签和问题对应 存入数据库
                } else {
                    problemLabel.setProblemId(problem.getProblemId());
                    problemLabel.setLabelId(seleLabel.getLabelId());
                    proLabService.createProLab(problemLabel);      //标签和问题对应 存入数据库
                }
            }
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


}
