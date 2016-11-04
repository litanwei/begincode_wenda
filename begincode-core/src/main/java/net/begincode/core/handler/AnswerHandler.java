package net.begincode.core.handler;

import net.begincode.bean.Page;
import net.begincode.common.BizException;
import net.begincode.core.enums.AdoptEnum;
import net.begincode.core.enums.AnswerResponseEnum;
import net.begincode.core.enums.FeedbackEnum;
import net.begincode.core.enums.SolveEnum;
import net.begincode.core.model.Answer;
import net.begincode.core.model.Problem;
import net.begincode.core.service.AnswerService;
import net.begincode.core.service.ProblemService;
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
        int answerNum = answerService.insertAnswer(answer);
        if (answerNum < 0) {
            throw new BizException(AnswerResponseEnum.ANSWER_ADD_ERROR);
        }
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
            if (pro.getSolve() == Integer.parseInt(SolveEnum.SOLVE.getCode())) {
                pro.setSolve(Integer.parseInt(SolveEnum.SOLVE.getCode()));
                problemService.updateProblem(pro);
            }
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
     * 获取问题所对应的未采纳回答
     * 并按时间降序排序
     *
     * @param problemId
     * @return List<Answer>
     */
    public List<Answer> selNoAdoptAnswerByProblemId(int problemId) {
        return answerService.findNotAdoptByProblemId(problemId);
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
         /*for (int i = 0; i < list.size(); i++) {
            String jsoupContent = "";
            if (list.get(i).getContent().length() > 0) {
                jsoupContent = JsoupUtil.replaceContent(list.get(i).getContent());
            }
            if (jsoupContent.length() > 20) {
                list.get(i).setContent(jsoupContent.substring(0, 20));
            } else if (jsoupContent.length() > 0) {
                list.get(i).setContent(JsoupUtil.replaceContent(jsoupContent));
            }
        }*/
        page.setData(list);
    }


}