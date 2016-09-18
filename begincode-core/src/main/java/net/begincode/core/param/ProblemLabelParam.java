package net.begincode.core.param;

import net.begincode.bean.Param;
import net.begincode.core.enums.ProblemResponseEnum;
import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stay on 2016/8/28  21:22.
 */
public class ProblemLabelParam extends Param{
    private Problem problem;
    private Label label;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }




    /**
     * @param name 传入的标签名是逗号分隔
     * @return 返回逗号分隔开的标签名集合
     */
    public Set<String> splitLabelName(String name) {
        HashSet<String> set = new HashSet<String>();
        //前台传入标签名 这里开始切割 替换中文逗号
        String[] labelName = name.replace("，", ",").split(",");
        for (int i = 0; i < labelName.length; i++) {
            set.add(labelName[i]);
        }
        return set;
    }


    @Override
    public void check() {
        checkNotEmpty(problem.getContent(),ProblemResponseEnum.PROBLEM_ADD_ERROR);
        checkNotEmpty(problem.getTitle(),ProblemResponseEnum.PROBLEM_ADD_ERROR);
    }
}
