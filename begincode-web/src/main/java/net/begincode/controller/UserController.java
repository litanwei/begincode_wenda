package net.begincode.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.begincode.bean.Page;
import net.begincode.bean.Response;
import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.LabelHandler;
import net.begincode.core.handler.ProAttentionHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.BizFrontProblem;
import net.begincode.enums.CommonResponseEnum;

/**
 * 用户
 *
 * @author kangliang
 * @date 2016年8月25日
 */
@RequestMapping("/user")
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserHandler userHandler;
    @Resource
    private ProblemHandler problemHandler;
    @Resource
    private AnswerHandler answerHandler;
    @Resource
    private ProAttentionHandler proAttentionHandler;
    @Resource
    private LabelHandler labelHandler;


    /**
     * summernote @提示获取后台用户
     *
     * @return 后台用户的nickname的list集合json
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserList(){
        List<BegincodeUser> list = userHandler.selectAll();
        List<String> nameList = new ArrayList<>(list.size());
        for (BegincodeUser user : list) {
            nameList.add(user.getBegincodeUserId()+","+user.getNickname());
        }
        return nameList;
    }

    /**
     * 根据用户名返回用户主界面
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String indexByNickName(@PathVariable(value = "userId") String userId, Model model) {
        model.addAttribute("problemSize", problemHandler.problemSizeByUserId(Integer.valueOf(userId)));
        model.addAttribute("answerSize", answerHandler.selectAnswerNumByUserId(Integer.valueOf(userId)));
        model.addAttribute("collectSize", proAttentionHandler.selectCollectNumByUserId(Integer.valueOf(userId)));
        model.addAttribute("user", userHandler.selectByUserId(Integer.valueOf(userId)));
        return "user_index";
    }

    /**
     * 用户对应的问题集合
     *
     * @param userId
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/problem/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Object findProblemByNickName(@PathVariable(value = "userId") Integer userId, BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectMyProblems(userId, page);
        return page;
    }

    /**
     * 用户对应的回答数列表
     *
     * @param userId
     * @param answer
     * @return
     */
    @RequestMapping(value = "/answer/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Object findAnswerByNickName(@PathVariable(value = "userId") Integer userId, Answer answer) {
        Page<Answer> page = new Page<Answer>();
        page.setCurrentNum(answer.getPage());
        answerHandler.selectAnswerByUserId(userId, page);
        return page;
    }

    /**
     * 用户收藏的问题列表
     *
     * @param userId
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/collect/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Object findCollProByNickName(@PathVariable(value = "userId") Integer userId, BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectCollProblemsById(userId, page);
        return page;
    }

    /**
     * 图表异步加载使用
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/echarts/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Object echartsCreate(@PathVariable(value = "userId") Integer userId) {
        Map map = new HashMap<>();
        map.put("label", labelHandler.selLabelNameListByUserId(userId));
        map.put("seriesData", labelHandler.selLabelUseNumByUserId(userId));
        return map;
    }


    /**
     * 活跃用户
     */
    @RequestMapping("activer")
    @ResponseBody
    public Object activeUser() {
        return userHandler.selectActiveUser();
    }

    /**
     * qq查找或注册用户
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public void findOrCreateUser(HttpServletResponse response, BegincodeUser user) {
        user.setUserSourceId(1);
        user.setDeleteFlag("1");
        user = userHandler.createUserAndFind(user);
        CookieOperation.addCookie(response, user);
    }

    /**
     * qq注销用户
     */
    @RequestMapping(value = "loginClean", method = RequestMethod.POST)
    @ResponseBody
    public void cleanUser(HttpServletResponse response) {
        CookieOperation.delCookie(response);
    }


}
