package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.begincode.core.mapper.BegincodeUserMapper;
import net.begincode.core.model.BegincodeUser;
import net.begincode.core.model.BegincodeUserExample;

/**
 * @author kangLiang
 * @date 2016年8月25日
 */
@Service
public class BegincodeUserService {
    @Resource
    private BegincodeUserMapper begincodeUserMapper;

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
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        begincodeUserExample.setOrderByClause("begincode_user_id ASC LIMIT 5");
        
        return begincodeUserMapper.selectByExample(begincodeUserExample);
    }

    /**
     * 根据nickName查找BegincodeUserId
     * @param nickName
     * @return    返回用户id
     */
    public int selectByNickName(String nickName){
        BegincodeUserExample begincodeUserExample = new BegincodeUserExample();
        BegincodeUserExample.Criteria criteria = begincodeUserExample.createCriteria();
        criteria.andNicknameEqualTo(nickName);
        List<BegincodeUser> list = begincodeUserMapper.selectByExample(begincodeUserExample);
        return list.get(0).getBegincodeUserId();
    }
}
