package net.begincode.core.service;

import net.begincode.core.enums.AnswerEnum;
import net.begincode.core.mapper.AnswerMapper;
import net.begincode.core.model.Answer;
import net.begincode.core.model.AnswerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by saber on 2016/9/12.
 */

@Service
public class AnswerService {

    @Resource
    private AnswerMapper answerMapper;

    /**
     * 插入回答
     *@param：answer
     *@return：int
     */
    public int insertSelective(Answer record){
        return answerMapper.insertSelective(record);
    }
    /**
     * 获取所有回答
     *@param：answer
     *@return：List<Answer>
     */
    public List<Answer> selectByExample(Answer answer){
        AnswerExample answerExample = new AnswerExample();
        answerExample.createCriteria()
                .andProblemIdEqualTo(answer.getProblemId())
                .andFeedbackNotEqualTo(AnswerEnum.FEED_BACK.getIntVlue());
        return answerMapper.selectByExampleWithBLOBs(answerExample);
    }

    /**
     * 查询回答
     *@param：answerId
     *@return：Answer
     */
    public Answer selectByPrimaryKey(Integer answerId){
        return answerMapper.selectByPrimaryKey(answerId);
    }
    /**
     * 添加回复更新
     *@param：
     *@return：
     */
    public int updateByPrimaryKeySelective(Answer record){
        return answerMapper.updateByPrimaryKeySelective(record);
    }

}
