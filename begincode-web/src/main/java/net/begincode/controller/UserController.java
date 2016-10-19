package net.begincode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.begincode.bean.Page;
import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.handler.AnswerHandler;
import net.begincode.core.handler.ProAttentionHandler;
import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.BizFrontProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.sun.tools.javac.jvm.ByteCodes.ret;

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

    /**
     * summernote @提示获取后台用户
     *
     * @return 后台用户的nickname的list集合json
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserList() {
        List<String> nameList = new ArrayList<>();
        List<BegincodeUser> list = userHandler.selectAll();
        for (BegincodeUser user : list) {
            nameList.add(user.getNickname());
        }
        return nameList;
    }

    /**
     * 根据用户名返回用户主界面
     *
     * @param nickName
     * @param model
     * @return
     */
    @RequestMapping(value = "/{nickName}", method = RequestMethod.GET)
    public String indexByNickName(@PathVariable(value = "nickName") String nickName, Model model) {
        model.addAttribute("problemSize", problemHandler.problemSizeByNickName(nickName));
        model.addAttribute("answerSize", answerHandler.selectAnswerNumByNickName(nickName));
        model.addAttribute("collectSize", proAttentionHandler.selectCollectNumByUserId(userHandler.selectByNickName(nickName).getBegincodeUserId()));
        model.addAttribute("user", userHandler.selectByNickName(nickName));
        return "user_index";
    }

    /**
     *  用户对应的问题集合
     * @param nickName
     * @param bizFrontProblem
     * @return
     */
    @RequestMapping(value = "/problem/{nickName}", method = RequestMethod.GET)
    @ResponseBody
    public Object findProblemByNickName(@PathVariable(value = "nickName") String nickName, BizFrontProblem bizFrontProblem) {
        Page<BizFrontProblem> page = new Page<BizFrontProblem>();
        page.setCurrentNum(bizFrontProblem.getPage());
        problemHandler.selectMyProblems(nickName, page);
        return page;
    }

    /**
     *
     * @param nickName
     * @param answer
     * @return
     */
    @RequestMapping(value = "/answer/{nickName}", method = RequestMethod.GET)
    @ResponseBody
    public Object findAnswerByNickName(@PathVariable(value = "nickName") String nickName, Answer answer) {
        Page<Answer> page = new Page<Answer>();
        page.setCurrentNum(answer.getPage());
        answerHandler.selectAnswerByNickName(nickName,page);
        return page;
    }


    /**
     * 活跃用户
     */
    @RequestMapping("/activer")
    @ResponseBody
    public Object activeUser() {
        List<BegincodeUser> list = userHandler.selectActiveUser();
        return list;
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
