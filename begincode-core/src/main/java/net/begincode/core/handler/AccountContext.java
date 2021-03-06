package net.begincode.core.handler;

import net.begincode.core.cookie.CookieOperation;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static net.begincode.core.cookie.CookieOperation.getUser;

/**
 * Created by saber on 2016/9/9.
 */
@Component
public class AccountContext {
    private Logger logger = LoggerFactory.getLogger(AccountContext.class);

    /**
     * 根据request查询user
     *
     * @param request
     */
    @Resource
    BegincodeUserService begincodeUserService;

    public BegincodeUser getCurrentUser(HttpServletRequest request) {
        BegincodeUser begincodeUser = CookieOperation.getUser(request);
        if (begincodeUser == null) {
            logger.info("无当前用户");
            return null;
        } else {
            return begincodeUserService.findUserByOpenId(begincodeUser.getOpenId());
        }
    }
}
