package net.begincode.controller;

import net.begincode.core.handler.AccountContext;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.Label;
import net.begincode.core.model.Problem;
import net.begincode.core.param.PageParam;
import net.begincode.core.param.ProblemLabelParam;
import net.begincode.core.support.AuthPassport;
import net.begincode.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static net.begincode.utils.PatternUtil.filterNickName;

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
    private AccountContext accountContext;

    @AuthPassport
    @RequestMapping("/create")
    public String problemSkip() {
        return "question_add";
    }

    /**
     * 主页新问题列表
     *
     * @return
     */
    @RequestMapping(value = "/newProblems", method = RequestMethod.GET)
    @ResponseBody
    public Map findNewProblem(@RequestParam(value = "page", defaultValue = "1") int page) {
        Map map = new HashMap();
        PageParam<Problem> pageParam = new PageParam<>(page);
        problemHandler.selectNewProblems(pageParam);
        map.put("problems", pageParam);
        putForProblems(map, pageParam.getPage().getData());
        return map;
    }

    /**
     * 热门的问题列表
     *
     * @return
     */
    @RequestMapping(value = "/hotProblems", method = RequestMethod.GET)
    @ResponseBody
    public Map findHotProblem() {
        Map map = new HashMap();
        List<Problem> list = problemHandler.selectHotProblems();
        map.put("problems", list);
        putForProblems(map, list);
        return map;
    }

    /**
     * 未回答问题列表
     *
     * @return
     */
    @RequestMapping(value = "/noAnswerProblems", method = RequestMethod.GET)
    @ResponseBody
    public Map findNoAnswerProblem() {
        Map map = new HashMap();
        List<Problem> list = problemHandler.selectNoAnswerProblems();
        map.put("problems", list);
        putForProblems(map, list);
        return map;
    }


    /**
     * 我的问题列表
     *
     * @param request
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/myProblems", method = RequestMethod.POST)
    @ResponseBody
    public Map findMyProblem(HttpServletRequest request) {
        Map map = new HashMap();
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        List<Problem> list = problemHandler.selectMyProblems(begincodeUser.getNickname());
        map.put("problems", list);
        putForProblems(map, list);
        return map;
    }

    @AuthPassport
    @RequestMapping(value = "/problemWithMessage", method = RequestMethod.POST)
    @ResponseBody
    public Map findByUserIdWithMessage(HttpServletRequest request) {
        Map map = new HashMap();
        BegincodeUser begincodeUser = accountContext.getCurrentUser(request);
        List<Problem> list = problemHandler.selectByUserIdWithMessage(begincodeUser.getBegincodeUserId());
        map.put("problems", list);
        putForProblems(map, list);
        return map;
    }

    /**
     * 添加问题
     * 关键字的保存(以逗号分隔)
     * 保存时正则获取问题的内容
     *
     * @param problemLableParam
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    @ResponseBody
    public Map addProblem(ProblemLabelParam problemLableParam, HttpServletRequest request) {
        Map map = new HashMap();
        Problem problem = problemLableParam.getProblem();
        BegincodeUser user = accountContext.getCurrentUser(request);
        problem.setUserName(user.getNickname());
        problem.setBegincodeUserId(user.getBegincodeUserId());
        problem.setCreateTime(new Date());
        Label label = problemLableParam.getLabel();
        Integer[] userId = contentFilter(problem.getContent());   //过滤@后面的用户名 把html标签去掉
        problemHandler.addProblem(problem, label, userId);
        map.put("success", "提交成功");
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
        Set<String> stringSet = filterNickName(content);
        int i = 0;
        Integer[] userId = new Integer[stringSet.size()];
        if (stringSet != null && stringSet.size() > 0) {
            for (String nickName : stringSet) {
                BegincodeUser begincodeUser = userHandler.selectByNickName(nickName.replace("@", ""));
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

    /**
     * 要获取问题列表 使用这个方法 结合js :  getProblems.js  使用
     * 只要调用这个方法 传入一个map 和 你要展示的问题集合
     *
     * @param map  一个普通的Map对象
     * @param list 要展示的问题集合
     */
    private void putForProblems(Map map, List<Problem> list) {
        map.put("labelName", problemHandler.problemToLabel(list));
        map.put("answerSize", problemHandler.problemToAnswerSize(list));
        map.put("answer", problemHandler.selectOrderByProblemId(list));
    }

}
