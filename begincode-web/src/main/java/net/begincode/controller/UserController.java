package net.begincode.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.handler.UserHandler;
import net.begincode.core.model.BegincodeUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    UserHandler userHandler;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public void CreateUser(BegincodeUser begincodeUser) {
        System.out.print(begincodeUser.getBegincodeUserId());
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
