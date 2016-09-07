package net.begincode.core.handler;

import net.begincode.core.model.Label;
import net.begincode.core.model.Message;
import net.begincode.core.model.Problem;
import net.begincode.core.service.LabelService;
import net.begincode.core.service.MessageService;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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


    /**
     * 新增问题
     * @param problem
     */
    public void addProblem(Problem problem)
    {
        problemService.createProblem(problem);
    }

    /**
     * 查找问题集合
     * @return
     */
    public List<Problem> selectAllProblem()
    {
        return problemService.findAllProblem();
    }

    /**
     * 新增标签
     * @param label
     */
    public void addLabel(Label label)
    {
        labelService.createLabel(label);
    }


    /**
     * 用标签名查看是否存在相同数据
     * @param labelName 传入的标签名
     * @return
     */
    public Label selectByLabelName(String labelName)
    {
        Label label = labelService.selectByName(labelName);
        return label;
    }

    /**
     * 添加消息
     * @param message
     */
    public void addMessage(Message message)
    {
        messageService.createMessage(message);
    }



}
