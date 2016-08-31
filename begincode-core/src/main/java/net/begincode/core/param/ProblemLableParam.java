package net.begincode.core.param;

import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stay on 2016/8/28  21:22.
 */
public class ProblemLableParam {
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
     * 过滤问题中@后面的用户名 不允许有重复的名进入 把html标签去掉
     * @param content  传入问题的内容
     * @return 用户名集合
     */
    public Set<String> filterContent(String content)
    {
        Set<String> set = new HashSet<String>();
        String pt = "@[^\\\\@ ]{1,20}";
        Matcher matcher = Pattern.compile(pt).matcher(content.trim().replaceAll("</?[^>]+>","").replace("&nbsp;",""));
        while(matcher.find())
        {
            set.add(matcher.group().trim().replace("@",""));
        }
        return set;
    }

    /**
     *
     * @param name 传入的标签名是逗号分隔
     * @return  返回逗号分隔开的标签名集合
     */
    public Set<String> splitLabelName(String name)
    {
        HashSet<String> set = new HashSet<String>();
        //前台传入标签名 这里开始切割 替换中文逗号
        String[] labelName = name.replace("，",",").split(",");
        for(int i=0;i<labelName.length;i++)
        {
            set.add(labelName[i]);
        }
        return set;
    }

    /**
     * 检查传入的标签名是否符合正则表达式
     * @param name 传入标签名
     * @return 符合返回true 不符合返回false
     */
    public boolean checkLabelName(String name)
    {
        //标签 正则表达式 只能匹配数字 字母 下划线 中文
        String pt = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
        Matcher matcher = Pattern.compile(pt).matcher(name.trim());
        if(matcher.matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }





}
