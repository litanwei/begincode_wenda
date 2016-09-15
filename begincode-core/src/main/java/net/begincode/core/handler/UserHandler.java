package net.begincode.core.handler;


import net.begincode.common.BizException;
import net.begincode.core.enums.UserResponseEnum;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class UserHandler {

    private Logger logger = LoggerFactory.getLogger(UserHandler.class);
    @Resource
    private BegincodeUserService begincodeUserService;
    /**
     * 创建或查找用户
     * @param：user
     * @return：BegincodeUser
     *
     * */
    public BegincodeUser createUserAndFind(BegincodeUser user) {
        if (user == null) {
            logger.error("注册用户，参数不能为 null");
            throw new BizException(UserResponseEnum.USER_ADD_ERROR);
        }
        BegincodeUser begincodeUser = begincodeUserService.findUserByOpenId(user.getOpenId());
        if(begincodeUser != null){
            return begincodeUser;
        }else{
            user.setLoginName("");
            user.setPwd("");
            user.setCdate(new Date());
            user.setGag("1");
            user.setCheckFlag("0");
            begincodeUserService.addBegincodeUser(user);
            return user;
        }
    }

}
