package net.begincode.core.handler;

import net.begincode.core.exception.ProblemException;
import net.begincode.core.model.Label;
import net.begincode.core.model.Message;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.service.LabelService;
import net.begincode.core.service.MessageService;
import net.begincode.core.service.ProLabService;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static javafx.scene.input.KeyCode.R;

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
     *
     * @param problem       前台传入的问题
     * @param labelNameList 传入的标签名集合
     * @param userId        如果问题内容中有@的人 则传入对应的id集合
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProblem(Problem problem, Set<String> labelNameList, Integer[] userId) {
        ProblemLabel problemLabel = new ProblemLabel();
        Label label = new Label();
        Message message = new Message();
        if (problem.getTitle().trim() == "") {
            throw new ProblemException("问题插入失败！");
        }
        int problemNum = problemService.createProblem(problem);
        if (problemNum < 0) {
            throw new ProblemException("问题插入失败！");
        }
        if (labelNameList != null && labelNameList.size() > 0) {
            for (String labelName : labelNameList) {
                Label seleLabel = labelService.selectByName(labelName);
                if (seleLabel == null) {
                    label.setLabelName(labelName);
                    labelService.createLabel(label);
                    problemLabel.setLabelId(label.getLabelId());
                    problemLabel.setProblemId(problem.getProblemId());
                } else {
                    problemLabel.setProblemId(problem.getProblemId());
                    problemLabel.setLabelId(seleLabel.getLabelId());
                    proLabService.createProLab(problemLabel);
                }
            }
        }
        if (userId != null && userId.length > 0) {
            for (int i = 0; i < userId.length - 1; i++) {
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
     * 新增标签
     *
     * @param label
     */
    public void addLabel(Label label) {
        labelService.createLabel(label);
    }


    /**
     * 用标签名查看是否存在相同数据
     *
     * @param labelName 传入的标签名
     * @return
     */
    public Label selectByLabelName(String labelName) {
        Label label = labelService.selectByName(labelName);
        return label;
    }

    /**
     * 添加消息
     *
     * @param message
     */
    public void addMessage(Message message) {
        messageService.createMessage(message);
    }


}