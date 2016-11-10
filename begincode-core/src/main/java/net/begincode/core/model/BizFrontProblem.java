package net.begincode.core.model;

import net.begincode.bean.PageParam;

import java.util.Date;
import java.util.List;

/**
 * Created by Stay on 2016/9/23  12:34.
 */
public class BizFrontProblem extends PageParam {
    private Problem problem;
    private String answerName;
    private List<String> labelNameList;
    private Date answerTime;

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public List<String> getLabelNameList() {
        return labelNameList;
    }

    public void setLabelNameList(List<String> labelNameList) {
        this.labelNameList = labelNameList;
    }
}
