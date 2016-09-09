package net.begincode.controller;

import net.begincode.core.handler.ProLabHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;
import net.begincode.core.param.ProblemLableParam;
import net.begincode.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Stay on 2016/8/26  21:48.
 */
@RequestMapping("/problem")
@Controller
public class ProblemController {

    private Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Resource
    private UserHandler userHandler;
    @Resource
    private ProblemHandler problemHandler;
    @Resource
    private ProLabHandler proLabHandler;

    /**
     * 添加问题
     * 关键字的保存(以逗号分隔)
     * 保存时正则获取问题的内容
     *
     * @param problemLableParam
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Map addProblem(ProblemLableParam problemLableParam) {
        Map map = new HashMap();
        Problem problem = problemLableParam.getProblem();
        Label label = problemLableParam.getLabel();
        //问题
        problem.setCreateTime(new Date()); //问题创建时间
        problem.setUpdateTime(new Date());  //问题修改时间
        //标签
        Set<String> set = splitLabelName(label.getLabelName());  //切割标签 返回被切割的标签集合
        Integer[] userId = contentFilter(problem.getContent());   //过滤@后面的用户名 把html标签去掉
        problemHandler.addProblem(problem, set, userId);
        map.put("msg", "提交成功");
        return map;
    }

    /**
     * 以逗号切割传入标签名
     *
     * @param name 传入的内容
     * @return 返回包含标签名的set集合
     */
    private Set<String> splitLabelName(String name) {
        HashSet<String> set = new HashSet<String>();
        //前台传入标签名 这里开始切割 替换中文逗号
        String[] labelNames = name.replace("，", ",").split(",");
        for (int i = 0; i < labelNames.length; i++) {
            set.add(labelNames[i]);
        }
        for (String labelName : set) {
            //判断是否符合只有数字 字母 下划线 中文
            if (PatternUtil.checkStr(labelName)) {
                continue;
            } else {
                set.remove(labelName);
            }
        }
        return set;
    }


    /**
     * 传进的问题过滤出@ 后面的nickName 返回该用户的id
     *
     * @param content 传入的内容
     * @return 用户id数组
     */
    private Integer[] contentFilter(String content) {
        Set<String> stringSet = PatternUtil.filterNickName(content);
        int i = 0;
        Integer[] userId = new Integer[stringSet.size()];
        if (stringSet != null && stringSet.size() > 0) {
            for (String nickName : stringSet) {
                BegincodeUser begincodeUser = userHandler.selectByNickName(nickName);
                if (begincodeUser == null) {
                    continue;
                } else {
                    userId[i] = begincodeUser.getBegincodeUserId();
                    i++;
                }
            }
        }
        return userId;
    }

}