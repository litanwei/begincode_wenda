package net.begincode.core.handler;

import net.begincode.common.BizException;
import net.begincode.core.enums.UserResponseEnum;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.service.BegincodeUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    //用于保存已登录的nickname 和对应的session
	private Map<String, HttpSession> loginUserMap=new HashMap<>();
	
    public Map<String, HttpSession> getLoginUserMap() {
		return loginUserMap;
	}

	public void setLoginUserMap(Map<String, HttpSession> loginUserMap) {
		this.loginUserMap = loginUserMap;
	}

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
    	if(openId==null||openId.equals("")){
    		return null;
    	}
        return begincodeUserService.findUserByOpenId(openId);
    }
    /**
     * 根据loginname和password查找用户
     * @param username
     * @param password
     * @return 
     */
    public BegincodeUser selectByLoginName(String username,String password){
    	if(username==null||password==null||username.trim().equals("")||password.trim().equals("")){
    		return null;
    	}
    	return begincodeUserService.selectByLoginName(username, password);
    }
    public Boolean IsExistByRow(String rowName,String rowData){
    	if(rowName==null||rowName.trim().equals("")||rowData==null||rowData.trim().equals("")){
    		return null;
    	}
    	return begincodeUserService.IsExistByRow(rowName, rowData);
    }
}
