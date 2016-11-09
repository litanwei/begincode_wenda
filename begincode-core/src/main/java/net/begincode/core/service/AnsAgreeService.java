package net.begincode.core.service;

import net.begincode.core.enums.AgreeEnum;
import net.begincode.core.mapper.AnsAgreeMapper;
import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.AnsAgreeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by saber on 2016/11/4.
 * 用户与问题回复 赞同/反对 操作
 */

@Service
public class AnsAgreeService {

    @Resource
    private AnsAgreeMapper ansAgreeMapper;



    /**
     *插入用户和回复 赞同/反对
     *
     * @param：ansAgree
     * @return: int
     */
    public int insertSelective(AnsAgree ansAgree){
        return ansAgreeMapper.insertSelective(ansAgree);
    }


    /**
     *根据用户id，问题id列表获取数据
     *
     * @param：begincodeUserId，answerIdList
     * @return: List<AnsAgree>
     */
    public List<AnsAgree> selectByExample(int begincodeUserId,List<Integer> answerIdList){
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andBegincodeUserIdEqualTo(begincodeUserId).andAnswerIdIn(answerIdList);
        return ansAgreeMapper.selectByExample(ansAgreeExample);
    }


    /**
     *根据ansAgree的用户id和问题id更新数据
     *
     * @param：ansAgree
     * @return: int
     */
    public int updateByExample(AnsAgree ansAgree){
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andBegincodeUserIdEqualTo(ansAgree.getBegincodeUserId()).andAnswerIdEqualTo(ansAgree.getAnswerId());
        return ansAgreeMapper.updateByExampleSelective(ansAgree,ansAgreeExample);
    }

    /**
     *根据回复id获取赞同数量
     *
     * @param：answerId
     * @return: int
     */
    public int selAgreeCountById(int answerId){
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andAnswerIdEqualTo(answerId).andAgreeEqualTo(Integer.parseInt(AgreeEnum.AGREE.getCode()));
        return ansAgreeMapper.countByExample(ansAgreeExample);
    }
    /**
     *根据回复id获取反对数量
     *
     * @param：answerId
     * @return: int
     */
    public int selOppositionCountById(int answerId){
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andAnswerIdEqualTo(answerId).andAgreeEqualTo(Integer.parseInt(AgreeEnum.OPPOSITION.getCode()));
        return ansAgreeMapper.countByExample(ansAgreeExample);
    }

}