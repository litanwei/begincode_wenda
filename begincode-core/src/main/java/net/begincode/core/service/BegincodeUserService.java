package net.begincode.core.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import net.begincode.common.BizException;
import net.begincode.core.enums.OpenIdResponseEnum;
import net.begincode.core.mapper.BegincodeUserMapper;
import net.begincode.core.mapper.BizBegincodeUserMapper;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.BegincodeUserExample;

/**
 * @author kangLiang
 * @date 2016年8月25日
 */
@Service
public class BegincodeUserService {

    private Logger logger = LoggerFactory.getLogger(BegincodeUserService.class);

    @Resource
    private BegincodeUserMapper begincodeUserMapper;
    @Resource
    private BizBegincodeUserMapper bizBegincodeUserMapper;

    /**
     * 新增BegincodeUser
     * @param user
     */
    public void addBegincodeUser(BegincodeUser user){
    	begincodeUserMapper.insertSelective(user);
    }

    /**
     * Id修改修改BegincodeUser
     * @parm user
     */
    public void updateBegincodeUserById(BegincodeUser user){
        begincodeUserMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据标识删除BegincodeUser
     * @param id  BegincodeUser标识
     */
    public void delBegincodeUserById(Integer id){
        begincodeUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * @return 查询所有BegincodeUser列表
     */
    public List<BegincodeUser> selectAll(){
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        return begincodeUserMapper.selectByExample(begincodeUserExample);
    }

    /**
     * 根据标识查询BegincodeUser
     * @param id
     * @return
     */
    public BegincodeUser selectById(Integer id){
        return begincodeUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取活跃用户列表
     * @return
     */
    
    public List<BegincodeUser> selectActiveUser(){
        /** --查询参数，取当前时间的前一个月的时间-- **/
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date dateBefore = calendar.getTime();
        return bizBegincodeUserMapper.getActiverUser(dateBefore);
    }
    /**
     * 根据nickName查找BegincodeUser
     * @param nickName
     * @return 不存在返回空 存在就返回此对象
     */
    public BegincodeUser selectByNickName(String nickName) {
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        BegincodeUserExample.Criteria criteria = begincodeUserExample.createCriteria();
        criteria.andNicknameEqualTo(nickName);
        List<BegincodeUser> list = begincodeUserMapper.selectByExample(begincodeUserExample);
        return list.size()>0?list.get(0):null;

    }

    /**
     * openId查找用户
     * @return BegincodeUser
     */
    public BegincodeUser findUserByOpenId(String openId) {
        if(StringUtils.isNotEmpty(openId)){
            BegincodeUser begincodeUser = new BegincodeUser();
            BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
            begincodeUserExample.createCriteria().andOpenIdEqualTo(openId);
            List<BegincodeUser> begincodeUserList = begincodeUserMapper.selectByExample(begincodeUserExample);
            for(int a = 0 ; a <begincodeUserList.size() ; a++ ){
                begincodeUser = begincodeUserList.get(a);
            }
            if(begincodeUser.getAccessToken() != null){
                return begincodeUser;
            }else{
                return null;
            }
        }else{
            logger.error(" accessToken ,openId  不能为空 ");
            throw new BizException(OpenIdResponseEnum.OPENID_FIND_ERROR);

        }

    }
    /*
     * 通过列表名查询和数据查询是否存在数据
     */
    public Boolean selectCountIsExist(String columnName,Object data){
    	if(bizBegincodeUserMapper.selectCountIsExist(columnName,data)<=0){
    		return false;
    	}
		return true;
    }
    /*
     * 通过login和passwor登录用户
     */
    public BegincodeUser selectUserByUsernamePassword(String username,String password){
    	return bizBegincodeUserMapper.selectUserByUsernamePassword(username, password);
    }

}
