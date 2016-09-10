package net.begincode.core.handler;

import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author kangliang
 * @date 2016年8月25日
 */
@Component
public class UserHandler {
    private Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Resource
    private BegincodeUserService begincodeUserService;

    /**
     * 新增User
     *
     * @param begincodeUser
     */
    public void addUser(BegincodeUser begincodeUser) {
        begincodeUserService.addBegincodeUser(begincodeUser);
    }

    /**
     * Id修改修改user
     *
     * @parm begincodeUser
     */
    public void updateBegincodeUserById(BegincodeUser begincodeUser) {
        begincodeUserService.updateBegincodeUserById(begincodeUser);
    }

    /**
     * 根据标识删除user
     *
     * @param id begincodeUser标识
     */
    public void delUser(Integer id) {
        begincodeUserService.delBegincodeUserById(id);
    }

    /**
     * 查询所有user列表
     */
    public List<BegincodeUser> selectAll() {
        return begincodeUserService.selectAll();
    }

    /**
     * 查询活跃用户
     */
    public List<BegincodeUser> selectActiveUser() {
        return begincodeUserService.selectActiveUser();
    }

    /**
     * 根据标识查询user
     *
     * @param id
     */
    public BegincodeUser selectById(Integer id) {
        return begincodeUserService.selectById(id);
    }

    public BegincodeUser createUser(BegincodeUser user) {
        if (user == null) {
            logger.error("注册用户，参数不能为 null");
            throw new IllegalArgumentException("注册用户，参数不能为 null");
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


    /**
     * 根据nickName查找用户
     * @param nickName
     * @return
     */
    public BegincodeUser selectByNickName(String nickName) {
        return begincodeUserService.selectByNickName(nickName);
    }
}
