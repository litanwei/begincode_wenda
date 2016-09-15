package net.begincode.core.service;

import net.begincode.common.BizException;
import net.begincode.core.enums.OpenIdResponseEnum;
import net.begincode.core.mapper.BegincodeUserMapper;
import net.begincode.core.mapper.BizBegincodeUserMapper;
import net.begincode.core.model.BegincodeUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
