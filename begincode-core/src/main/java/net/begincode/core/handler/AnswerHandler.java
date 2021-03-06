package net.begincode.core.handler;

import net.begincode.bean.Page;
import net.begincode.common.BizException;
import net.begincode.core.enums.*;
import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.Answer;
import net.begincode.core.model.Problem;
import net.begincode.core.service.*;
import net.begincode.utils.JsoupUtil;
import net.begincode.utils.PatternUtil;
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
    @Resource
    private AnsAgreeService ansAgreeService;
    @Resource
    private MessageService messageService;
    @Resource
    private BegincodeUserService begincodeUserService;


    /**
     * 创建回答 并返回回答
     *
     * @param answer
     * @return
     */
    public Answer creatAnswer(Answer answer) {
        answer.setCreateTime(new Date());
        Integer answerNum = answerService.insertAnswer(answer);
        if (answerNum < 0) {
            throw new BizException(AnswerResponseEnum.ANSWER_ADD_ERROR);
        }
        problemService.updateAnswerAddByProblemId(answer.getProblemId());
        messageService.createMessage(answer.getProblemId(),answer.getAnswerId(), JsoupUtil.matchMessageUserId(answer.getContent()));
        return answerService.selAnswerByAnswerId(answer.getAnswerId());
    }

    /**
     * 回答反馈
     * 正常状态（0）设为审核状态（2）
     * 审核通过状态（3）无操作
     *
     * @param answerId
     * @return
     */
    public Answer feedbackAnswer(int answerId) {
        Answer ans = answerService.selAnswerByAnswerId(answerId);
        if (ans.getFeedback() == 0) {
            ans.setFeedback(Integer.parseInt(FeedbackEnum.IS_FEED_BACK.getCode()));
            answerService.updateAnswer(ans);
        }
        return ans;
    }

    /**
     * 回答采纳
     * 验证提问人身份 正确返回采纳回答 错误返回null
     * 提问人不能采纳自己的回答
     * 更改问题状态为已解决
     *
     * @param answerId,begincodeUserId
     * @return Answer
     */
    public Answer adoptAnswer(int answerId, int begincodeUserId) {
        Answer ans = answerService.selAnswerByAnswerId(answerId);
        Problem pro = problemService.selProblemById(ans.getProblemId());
        if (pro.getBegincodeUserId() == begincodeUserId && ans.getBegincodeUserId() != begincodeUserId) {
            ans.setAdopt(Integer.parseInt(AdoptEnum.ADOPT.getCode()));
            if (pro.getSolve() == Integer.parseInt(SolveEnum.NO_SOLVE.getCode())) {
                pro.setSolve(Integer.parseInt(SolveEnum.SOLVE.getCode()));
                problemService.updateProblem(pro);
            }
            AnsAgree ansAgree = new AnsAgree();
            ansAgree.setAnswerId(answerId);
            ansAgree.setAgree(Integer.parseInt(AgreeEnum.AGREE.getCode()));
            ansAgree.setBegincodeUserId(begincodeUserId);
            ansAgreeService.selAndUpdateAnsAgree(ansAgree);
            ans.setAgreeCount(ansAgreeService.selAgreeCountById(answerId));
            answerService.updateAnswer(ans);
            return ans;
        } else {
            return null;
        }
    }

    /**
     * 获取所有回答
     *
     * @param answer
     * @return List<Answer>
     */
    public List<Answer> selAllAnswerByExample(Answer answer) {
        return answerService.selectAllAnswer(answer);
    }

    /**
     * 根据userID返回回答数
     *
     * @param userId
     * @return
     */
    public Integer selectAnswerNumByUserId(Integer userId) {
        return answerService.findAnswerNumByUserId(userId);
    }

    /**
     * 根据userId 返回回答实体集合
     *
     * @param userId
     * @param page
     */
    public void selectAnswerByUserId(Integer userId, Page<Answer> page) {
        page.setTotalNum(selectAnswerNumByUserId(userId));
        List<Answer> list = answerService.findAnswerListByUserId(userId, page.getCurrentNum(), page.getPageEachSize());
        page.setData(list);
    }
    /**
     * 获取问题所对应的采纳回答
     * 并按时间降序排序
     *
     * @param problemId
     * @return List<Answer>
     */
    public List<Answer> selAdoptAnswerByProblemId(int problemId) {
        List<Answer> answerList = answerService.findAdoptByProblemId(problemId);
        for(Answer answer:answerList){
            answer.setAgreeCount(ansAgreeService.selAgreeCountById(answer.getAnswerId()));
            answer.setOppositionCount(ansAgreeService.selOppositionCountById(answer.getAnswerId()));
            answerService.updateAnswer(answer);
        }
        return answerList;
    }

    /**
     * 获取问题所对应的未采纳回答
     * 并按时间降序排序
     *
     * @param problemId
     * @return List<Answer>
     */
    public List<Answer> selNoAdoptAnswerByProblemId(int problemId) {
        List<Answer> answerList = answerService.findNotAdoptByProblemId(problemId);
        for(Answer answer:answerList){
            answer.setAgreeCount(ansAgreeService.selAgreeCountById(answer.getAnswerId()));
            answer.setOppositionCount(ansAgreeService.selOppositionCountById(answer.getAnswerId()));
            answerService.updateAnswer(answer);
        }
        return answerList;
    }



}