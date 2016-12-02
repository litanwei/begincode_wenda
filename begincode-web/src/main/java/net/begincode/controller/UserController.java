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
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public String indexByNickName(@PathVariable(value = "user") String user, Model model) {
        BegincodeUser begincodeUser = problemHandler.selectByNickName(user);
        if(begincodeUser == null){
            fillUserView(model,Integer.parseInt(user));
        }else{
            fillUserView(model,begincodeUser.getBegincodeUserId());
        }
        return "user_index";
    }

    /**
     * 填充用户界面
     *
     * @param model
     * @param userId
     */
    private void fillUserView(Model model,Integer userId){
        model.addAttribute("problemSize", problemHandler.problemSizeByUserId(userId));
        model.addAttribute("answerSize", answerHandler.selectAnswerNumByUserId(userId));
        model.addAttribute("collectSize", proAttentionHandler.selectCollectNumByUserId(userId));
        model.addAttribute("user", userHandler.selectByUserId(userId));
    }

    /**
     * 用户对应的问题集合
     *
     * @param user
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/problem/{user}", method = RequestMethod.GET)
    @ResponseBody
    public Object findProblemByNickName(@PathVariable(value = "user") String user, BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        BegincodeUser begincodeUser = problemHandler.selectByNickName(user);
        if(begincodeUser == null){
            problemHandler.selectMyProblems(Integer.parseInt(user), page);
        }else{
            problemHandler.selectMyProblems(begincodeUser.getBegincodeUserId(), page);
        }
        return page;
    }

    /**
     * 用户对应的回答数列表
     *
     * @param user
     * @param answer
     * @return
     */
    @RequestMapping(value = "/answer/{user}", method = RequestMethod.GET)
    @ResponseBody
    public Object findAnswerByNickName(@PathVariable(value = "user") String user, Answer answer) {
        Page<Answer> page = new Page<Answer>();
        page.setCurrentNum(answer.getPage());
        BegincodeUser begincodeUser = problemHandler.selectByNickName(user);
        if(begincodeUser == null){
            answerHandler.selectAnswerByUserId(Integer.parseInt(user), page);
        }else{
            answerHandler.selectAnswerByUserId(begincodeUser.getBegincodeUserId(), page);
        }
        return page;
    }

    /**
     * 用户收藏的问题列表
     *
     * @param user
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/collect/{user}", method = RequestMethod.GET)
    @ResponseBody
    public Object findCollProByNickName(@PathVariable(value = "user") String user, BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        BegincodeUser begincodeUser = problemHandler.selectByNickName(user);
        if(begincodeUser == null){
            problemHandler.selectCollProblemsById(Integer.parseInt(user), page);
        }else{
            problemHandler.selectCollProblemsById(begincodeUser.getBegincodeUserId(), page);
        }
        return page;
    }

    /**
     * 图表异步加载使用
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/echarts/{user}", method = RequestMethod.GET)
    @ResponseBody
    public Object echartsCreate(@PathVariable(value = "user") String user) {
        Map map = new HashMap<>();
        BegincodeUser begincodeUser = problemHandler.selectByNickName(user);
        if(begincodeUser == null){
            map.put("label", labelHandler.selLabelNameListByUserId(Integer.parseInt(user)));
            map.put("seriesData", labelHandler.selLabelUseNumByUserId(Integer.parseInt(user)));
        }else{
            map.put("label", labelHandler.selLabelNameListByUserId(begincodeUser.getBegincodeUserId()));
            map.put("seriesData", labelHandler.selLabelUseNumByUserId(begincodeUser.getBegincodeUserId()));
        }
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
    public Object findOrCreateUser(HttpServletResponse response, BegincodeUser user) {
        user.setUserSourceId(1);
        user.setDeleteFlag("1");
        user = userHandler.createUserAndFind(user);
        CookieOperation.addCookie(response, user);
        return 1;
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
