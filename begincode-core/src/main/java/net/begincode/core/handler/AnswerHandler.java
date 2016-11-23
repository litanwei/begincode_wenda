package net.begincode.core.handler;

import net.begincode.bean.Page;
import net.begincode.common.BizException;
import net.begincode.core.enums.*;
import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.Answer;
import net.begincode.core.model.Problem;
import net.begincode.core.service.*;
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
        //截取内容中@的用户名 加上url
        answer.setContent(PatternUtil.nickNameUrl(answer.getContent()));
        Integer answerNum = answerService.insertAnswer(answer);
        if (answerNum < 0) {
            throw new BizException(AnswerResponseEnum.ANSWER_ADD_ERROR);
        }
        messageService.createMessage(answer.getProblemId(),answer.getAnswerId(),begincodeUserService.contentFilter(answer.getContent()));
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
    public void feedbackAnswer(int answerId) {
        Answer ans = answerService.selAnswerByAnswerId(answerId);
        if (ans.getFeedback() == 0) {
            ans.setFeedback(Integer.parseInt(FeedbackEnum.IS_FEED_BACK.getCode()));
            answerService.updateAnswer(ans);
        }
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
            ansAgreeService.selectAndUpdate(ansAgree);
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
     * 根据nickName返回回答数
     *
     * @param nickName
     * @return
     */
    public Integer selectAnswerNumByNickName(String nickName) {
        return answerService.findAnswerNumByNickName(nickName);
    }

    /**
     * 根据nickname 返回回答实体集合
     *
     * @param nickName
     * @param page
     */
    public void selectAnswerByNickName(String nickName, Page<Answer> page) {
        page.setTotalNum(selectAnswerNumByNickName(nickName));
        List<Answer> list = answerService.findAnswerListByNickName(nickName, page.getCurrentNum(), page.getPageEachSize());
        page.setData(list);
    }


}