package net.begincode.core.handler.tools;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by saber on 2016/9/9.
 */
@Component
public class UserAccountTool {
    /**
     * 根据request查询user
     *
     * @param request
     */
    @Autowired
    BegincodeUserService begincodeUserService;
    private static UserAccountTool userAccountTool;

    @PostConstruct
    public void init() {
        userAccountTool = this;
        userAccountTool.begincodeUserService = this.begincodeUserService;
    }

    public static BegincodeUser getAll(HttpServletRequest request) {
        return userAccountTool.begincodeUserService.selectById(CookieOperation.getUser(request).getBegincodeUserId());
    }
}
