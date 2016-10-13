package net.begincode.core.param;

import net.begincode.bean.Param;
import net.begincode.core.enums.AnswerResponseEnum;
import net.begincode.core.enums.ContentResponseEnum;
import net.begincode.core.enums.ProblemIdResponseEnum;
import net.begincode.core.model.Answer;


/**
 * Created by saber on 2016/9/17.
 */
public class AnswerParam extends Param {
    private Answer answer;

    public Answer getAnswer() {
        return answer;
    }
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public void check() {
        checkNotEmpty(answer.getProblemId().toString(), ProblemIdResponseEnum.PROBLEMID_EMPTY_ERROR);
        checkNotEmpty(answer.getContent(), ContentResponseEnum.CONTENT_EMPTY_ERROR);
    }
}
