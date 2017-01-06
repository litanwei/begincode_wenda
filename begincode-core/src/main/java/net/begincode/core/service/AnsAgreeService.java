package net.begincode.core.service;

import net.begincode.common.BizException;
import net.begincode.core.enums.AgreeEnum;
import net.begincode.core.enums.AnsAgreeResponseEnum;
import net.begincode.core.mapper.AnsAgreeMapper;
import net.begincode.core.model.AnsAgree;
import net.begincode.core.model.AnsAgreeExample;
import net.begincode.core.model.Answer;
import net.begincode.core.model.BegincodeUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saber on 2016/11/4.
 * 用户与问题回复 赞同/反对 操作
 */

@Service
public class AnsAgreeService {

    private Logger logger = LoggerFactory.getLogger(AnsAgreeService.class);
    @Resource
    private AnsAgreeMapper ansAgreeMapper;


    /**
     * 根据用户id，问题回复id判断AnsAgree是否存在 存在修改内容 不存在增加
     *
     * @param：ansAgree
     * @return：
     */
    public int selAndUpdateAnsAgree(AnsAgree ansAgree) {
        AnsAgree ans = selAnsAgreeByAnsId(ansAgree.getBegincodeUserId(), ansAgree.getAnswerId());
        if (ansAgree.getAgree() == 0 && ans.getAgree() == 0) {
            return 0;
        }
        if (ans != null) {
            updateAnsAgree(ansAgree);
            return ans.getAgree();
        } else {
            insertSelective(ansAgree);
            return 0;
        }
    }


    /**
     * 插入用户和回复 赞同/反对
     *
     * @param：ansAgree
     * @return: int
     */
    public int insertSelective(AnsAgree ansAgree) {
        return ansAgreeMapper.insertSelective(ansAgree);
    }


    /**
     * 根据用户id，问题id列表获取数据
     *
     * @param begincodeUserId
     * @param answerId
     * @return
     */
    public AnsAgree selAnsAgreeByAnsId(int begincodeUserId, Integer answerId) {
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andBegincodeUserIdEqualTo(begincodeUserId).andAnswerIdEqualTo(answerId);
        List<AnsAgree> list = ansAgreeMapper.selectByExample(ansAgreeExample);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据用户id，回答列表集合获取数据
     *
     * @param begincodeUserId
     * @param answerList
     * @return
     */
    public List<AnsAgree> selAnsAgreeByAnsList(int begincodeUserId, List<Integer> answerList) {
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andBegincodeUserIdEqualTo(begincodeUserId).andAnswerIdIn(answerList);
        return ansAgreeMapper.selectByExample(ansAgreeExample);
    }


    /**
     * 根据ansAgree的用户id和问题id更新数据
     *
     * @param：ansAgree
     * @return: int
     */
    public int updateAnsAgree(AnsAgree ansAgree) {
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andBegincodeUserIdEqualTo(ansAgree.getBegincodeUserId()).andAnswerIdEqualTo(ansAgree.getAnswerId());
        try {
            return ansAgreeMapper.updateByExampleSelective(ansAgree, ansAgreeExample);
        }catch (BizException bizException){
            logger.error(AnsAgreeResponseEnum.ANSAGREE_UPDATE_ERROR.getMessage());
            throw new BizException(AnsAgreeResponseEnum.ANSAGREE_UPDATE_ERROR);
        }
    }

    /**
     * 根据回复id获取赞同数量
     *
     * @param：answerId
     * @return: int
     */
    public int selAgreeCountById(int answerId) {
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andAnswerIdEqualTo(answerId).andAgreeEqualTo(Integer.parseInt(AgreeEnum.AGREE.getCode()));
        return ansAgreeMapper.countByExample(ansAgreeExample);
    }


    /**
     * 根据用户id，问题回复id列表获取AnsAgree列表
     *
     * @param：ansAgree
     * @return：
     */
    public List<Integer> selectAnsAgreeList(BegincodeUser begincodeUser, List<Answer> answerList) {
        List<Integer> answerIdList = new ArrayList<>();
        if (answerList.size() != 0 && begincodeUser != null) {
            for (Answer answer : answerList) {
                answerIdList.add(answer.getAnswerId());
            }
            List<AnsAgree> ansAgreeList = selAnsAgreeByAnsList(begincodeUser.getBegincodeUserId(), answerIdList);
            for (int x = 0; x < answerList.size(); x++) {
                answerIdList.add(x, 0);
                for (int y = 0; y < ansAgreeList.size(); y++) {
                    int answerId = answerList.get(x).getAnswerId();
                    if (answerId == ansAgreeList.get(y).getAnswerId()) {
                        answerIdList.add(x, ansAgreeList.get(y).getAgree());
                        continue;
                    }
                }
            }

        }
        return answerIdList;

    }

    /**
     * 根据回复id获取反对数量
     *
     * @param：answerId
     * @return: int
     */
    public int selOppositionCountById(int answerId) {
        AnsAgreeExample ansAgreeExample = new AnsAgreeExample();
        ansAgreeExample.createCriteria().andAnswerIdEqualTo(answerId).andAgreeEqualTo(Integer.parseInt(AgreeEnum.OPPOSITION.getCode()));
        return ansAgreeMapper.countByExample(ansAgreeExample);
    }

}
