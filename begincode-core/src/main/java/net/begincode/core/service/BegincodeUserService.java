package net.begincode.core.service;

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
    public void delBegincodeUserById(Integer id){begincodeUserMapper.deleteByPrimaryKey(id);
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
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        begincodeUserExample.setOrderByClause("begincode_user_id ASC LIMIT 5");

        return begincodeUserMapper.selectByExample(begincodeUserExample);
    }
    /**
     * accessToken,openId查找用户
     * @return BegincodeUser
     */
    public BegincodeUser findUserByTokenIdAndOpenId(String accessToken,String openId) {
        if(StringUtils.isNotEmpty(accessToken) && StringUtils.isNotEmpty(openId)){
            BegincodeUser begincodeUser = new BegincodeUser();
            begincodeUser.setOpenId(openId);
            begincodeUser.setAccessToken(accessToken);
            begincodeUser = bizBegincodeUserMapper.selectByTokenIdAndOpenId(begincodeUser);
            if(begincodeUser != null){
                return begincodeUser;
            }else{
                return null;
            }
        }else{
            logger.error(" accessToken ,openId  不能为空 ");
            throw new BizException(OpenIdResponseEnum.OPENID_FIND_ERROR);

        }

    }
    /**
     * openId查找用户
     * @return BegincodeUser
     */
    public BegincodeUser findUserByOpenId(String openId) {
        if(StringUtils.isNotEmpty(openId)){
            BegincodeUser begincodeUser = new BegincodeUser();
            begincodeUser.setOpenId(openId);
            begincodeUser = bizBegincodeUserMapper.selectByTokenIdAndOpenId(begincodeUser);
            if(begincodeUser != null){
                return begincodeUser;
            }else{
                return null;
            }
        }else{
            logger.error(" accessToken ,openId  不能为空 ");
            throw new BizException(OpenIdResponseEnum.OPENID_FIND_ERROR);

        }

    }

}
