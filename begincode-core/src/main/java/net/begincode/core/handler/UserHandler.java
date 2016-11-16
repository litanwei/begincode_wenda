package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.UserResponseEnum;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.BizBegincodeUser;
import net.begincode.core.service.BegincodeUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户事务层
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
    public List<BizBegincodeUser> selectActiveUser() {
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


    /**
     * 根据nickName查找用户
     * @param nickName
     * @return
     */
    public BegincodeUser selectByNickName(String nickName) {
        return begincodeUserService.selectByNickName(nickName);
    }

    /**
     * 根据openId查找用户
     * @param openId
     * @return
     */
    public BegincodeUser selectByOpenId(String openId)
    {
        return begincodeUserService.findUserByOpenId(openId);
    }
    
    /**
     * 用于获取列名的所有值
     */
    public List<String> selectAllByColumn(String columnName){
    	List<String> columns=new ArrayList<>();
    	columns = begincodeUserService.selectAllByColumn(columnName);	
    	return columns;
    }
}
