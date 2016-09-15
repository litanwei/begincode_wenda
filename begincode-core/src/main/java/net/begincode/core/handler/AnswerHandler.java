package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.AnswerEnum;
import net.begincode.core.enums.AnswerResponseEnum;
import net.begincode.core.model.Answer;
import net.begincode.core.service.AnswerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by saber on 2016/9/12.
 */

@Component
public class AnswerHandler {

    @Resource
    private AnswerService answerService;

    /**
     * 创建回答
     */
    public void creatAnswer(Answer answer) {
        answer.setCreateTime(new Date());
        if (answer.getContent().trim() == "") {
            throw new BizException(AnswerResponseEnum.ANSWER_ADD_ERROR);
        }
        int answerNum = answerService.insertSelective(answer);
        if (answerNum < 0) {
            throw new BizException(AnswerResponseEnum.ANSWER_ADD_ERROR);
        }

    }

    /**
     * 回答反馈
     */
    public void feedback(int answerId) {
        Answer ans = answerService.selectByPrimaryKey(answerId);
        if (ans.getFeedback() == 0) {
            ans.setFeedback(AnswerEnum.IS_FEED_BACK.getIntVlue());
            answerService.updateByPrimaryKeySelective(ans);
        }
    }

    /**
     * 获取所有回答
     */
    public List<Answer> selectAllByExample(Answer answer){
        return answerService.selectByExample(answer);
    }
}