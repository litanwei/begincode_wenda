package net.begincode.core.handler;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by saber on 2016/9/9.
 */
@Component
public class AccountContext {
    /**
     * 根据request查询user
     *
     * @param request
     */
    @Resource
    static BegincodeUserService begincodeUserService;



    public static  BegincodeUser getCurrentUser(HttpServletRequest request) {
        return begincodeUserService.selectById(CookieOperation.getUser(request).getBegincodeUserId());
    }
}
