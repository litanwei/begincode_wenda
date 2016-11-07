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
import net.begincode.core.model.BegincodeUserExample.Criteria;

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
     *
     * @param user
     */
    public void addBegincodeUser(BegincodeUser user) {
        begincodeUserMapper.insertSelective(user);
    }

    /**
     * Id修改修改BegincodeUser
     *
     * @parm user
     */
    public void updateBegincodeUserById(BegincodeUser user) {
        begincodeUserMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据标识删除BegincodeUser
     *
     * @param id BegincodeUser标识
     */
    public void delBegincodeUserById(Integer id) {
        begincodeUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * @return 查询所有BegincodeUser列表
     */
    public List<BegincodeUser> selectAll() {
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        return begincodeUserMapper.selectByExample(begincodeUserExample);
    }

    /**
     * 根据标识查询BegincodeUser
     *
     * @param id
     * @return
     */
    public BegincodeUser selectById(Integer id) {
        return begincodeUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取活跃用户列表
     *
     * @return
     */

    public List<BegincodeUser> selectActiveUser() {
        /** --查询参数，取当前时间的前一个月的时间-- **/
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date dateBefore = calendar.getTime();
        return bizBegincodeUserMapper.getActiverUser(dateBefore);
    }

    /**
     * 根据nickName查找BegincodeUser
     *
     * @param nickName
     * @return 不存在返回空 存在就返回此对象
     */
    public BegincodeUser selectByNickName(String nickName) {
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        BegincodeUserExample.Criteria criteria = begincodeUserExample.createCriteria();
        criteria.andNicknameEqualTo(nickName);
        List<BegincodeUser> list = begincodeUserMapper.selectByExample(begincodeUserExample);
        return list.size() > 0 ? list.get(0) : null;

    }

    /**
     * openId查找用户
     *
     * @return BegincodeUser
     */
    public BegincodeUser findUserByOpenId(String openId) {
        BegincodeUser begincodeUser = new BegincodeUser();
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        begincodeUserExample.createCriteria().andOpenIdEqualTo(openId);
        List<BegincodeUser> begincodeUserList = begincodeUserMapper.selectByExample(begincodeUserExample);
        for (int a = 0; a < begincodeUserList.size(); a++) {
            begincodeUser = begincodeUserList.get(a);
        }
        if (begincodeUser.getAccessToken() != null) {
            return begincodeUser;
        } else {
            return null;
        }
    }
    
    /**
     * loginname和password 查找用户
     * @param loginname
     * @param password
     * @return
     */
    public BegincodeUser selectByLoginname(String loginname,String password){
    	BegincodeUser begincodeUser = new BegincodeUser();
    	BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
    	Criteria criteria=begincodeUserExample.createCriteria();
    	criteria.andLoginNameEqualTo(loginname);
    	criteria.andPwdEqualTo(password);
    	List<BegincodeUser> begincodeUserList=begincodeUserMapper.selectByExample(begincodeUserExample);
    	if(begincodeUserList.size()==1){
    		begincodeUser = begincodeUserList.get(0);
    		return begincodeUser;
    	}
    	return null;
    }
    public Boolean IsExistByRow(String rowName,String rowData){
    	Integer size=bizBegincodeUserMapper.countByRowName(rowName, rowData);
    	if(size==null||size<=0){
    		return false;
    	}
    	return true;
    }
   
}
