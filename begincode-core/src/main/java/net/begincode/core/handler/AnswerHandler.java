package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.AnswerEnum;
import net.begincode.core.enums.AnswerResponseEnum;
import net.begincode.core.enums.ProblemEnum;
import net.begincode.core.model.Answer;
import net.begincode.core.model.Problem;
import net.begincode.core.service.AnswerService;
import net.begincode.core.service.ProblemService;
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
    @Resource
    private ProblemService problemService;



    /**
     * 创建回答 并返回回答
     * @param answer
     * @return
     */
    public Answer creatAnswer(Answer answer) {
        answer.setCreateTime(new Date());
        int answerNum = answerService.insertAnswer(answer);
        if (answerNum < 1) {
            throw new BizException(AnswerResponseEnum.ANSWER_ADD_ERROR);
        }
        return answerService.selAnswerByAnswerId(answer.getAnswerId());
    }

    /**
     * 回答反馈
     * 正常状态（0）设为审核状态（2）
     * 审核通过状态（3）无操作
     * @param answerId
     * @return
     */
    public void answerFeedback(int answerId) {
        Answer ans = answerService.selAnswerByAnswerId(answerId);
        if (ans.getFeedback() == 0) {
            ans.setFeedback(AnswerEnum.IS_FEED_BACK.getIntVlue());
            answerService.updateAnswer(ans);
        }
    }

    /**
     * 回答采纳
     * 验证提问人身份 正确返回采纳回答 错误返回null
     * 提问人不能采纳自己的回答
     * 更改问题状态为已解决
     * @param answerId,begincodeUserId
     * @return Answer
     */
    public Answer answerAdopt(int answerId,int begincodeUserId){
        Answer ans = answerService.selAnswerByAnswerId(answerId);
        Problem pro = problemService.selProblemById(ans.getProblemId());
        if(pro.getBegincodeUserId() == begincodeUserId && ans.getBegincodeUserId()!= begincodeUserId){
            ans.setAdopt(AnswerEnum.ANSWER_ADOPT.getIntVlue());
            if (pro.getSolve()==ProblemEnum.SOLVE.getIntVlue()){
                pro.setSolve(ProblemEnum.SOLVE.getIntVlue());
                problemService.updateProblem(pro);
            }
            answerService.updateAnswer(ans);
            return ans;
        }else{
            return null;
        }
    }

    /**
     * 获取所有回答
     * @param answer
     * @return List<Answer>
     */
    public List<Answer> selAllAnswerByExample(Answer answer){
        return answerService.selectAllAnswer(answer);
    }

    /**
     * 获取问题所对应的采纳回答
     * 并按时间降序排序
     * @param problemId
     * @return List<Answer>
     */
    public List<Answer> selAdoptAnswerByProblemId(int problemId){
        return answerService.findAdoptByProblemId(problemId);
    }

    /**
     * 获取问题所对应的未采纳回答
     * 并按时间降序排序
     * @param problemId
     * @return List<Answer>
     */
    public List<Answer> selNoAdoptAnswerByProblemId(int problemId){
        return answerService.findNotAdoptByProblemId(problemId);
    }
}