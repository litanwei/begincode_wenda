package net.begincode.core.service;

import net.begincode.core.enums.AdoptEnum;
import net.begincode.core.enums.FeedbackEnum;
import net.begincode.core.mapper.AnswerMapper;
import net.begincode.core.model.Answer;
import net.begincode.core.model.AnswerExample;
import net.begincode.core.model.ProblemExample;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Stay on 2016/9/15  15:47.
 */
@Service
public class AnswerService {
    private Logger logger = LoggerFactory.getLogger(AnswerService.class);
    @Resource
    private AnswerMapper answerMapper;


    /**
     * 传入问题id 返回对应的回答数
     *
     * @param problemId
     * @return
     */
    public Integer findByProblemIdNum(Integer problemId) {
        AnswerExample answerExample = new AnswerExample();
        AnswerExample.Criteria criteria = answerExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return answerMapper.countByExample(answerExample);
    }

    /**
     * 根据problemId查找回答集合
     *
     * @param problemId
     * @return
     */
    public List<Answer> findByProblemId(Integer problemId) {
        AnswerExample answerExample = new AnswerExample();
        AnswerExample.Criteria criteria = answerExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return answerMapper.selectByExample(answerExample);
    }

    /**
     * 根据problemId查找回答 并排序 取创建时间最近的回答
     *
     * @param problemId
     * @return 单个回答实体
     */
    public Answer findOrderByProblemId(Integer problemId) {
        if (findByProblemId(problemId).size() > 0) {
            AnswerExample answerExample = new AnswerExample();
            answerExample.setOrderByClause("create_time desc limit 1");
            AnswerExample.Criteria criteria = answerExample.createCriteria();
            criteria.andProblemIdEqualTo(problemId);
            return answerMapper.selectByExample(answerExample).get(0);
        } else {
            return null;
        }
    }

    /**
     * 插入回答
     *
     * @param：answer
     * @return：int
     */
    public int insertAnswer(Answer record) {
        return answerMapper.insertSelective(record);
    }

    /**
     * 获取所有回答
     *
     * @param：answer
     * @return：List<Answer>
     */
    public List<Answer> selectAllAnswer(Answer answer) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.createCriteria()
                .andProblemIdEqualTo(answer.getProblemId())
                .andFeedbackNotEqualTo(Integer.parseInt(FeedbackEnum.FEED_BACK.getCode()));
        return answerMapper.selectByExampleWithBLOBs(answerExample);
    }

    /**
     * 查询回答
     *
     * @param：answerId
     * @return：Answer
     */
    public Answer selAnswerByAnswerId(Integer answerId) {
        return answerMapper.selectByPrimaryKey(answerId);
    }

    /**
     * 添加回复更新
     *
     * @param：
     * @return：
     */
    public int updateAnswer(Answer record) {
        return answerMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 根据problemId查找非采纳回答 按时间排序
     *
     * @param problemId
     * @return 问题所有回答实体
     */
    public List<Answer> findNotAdoptByProblemId(Integer problemId) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.setOrderByClause("create_time desc");
        answerExample.createCriteria()
                .andProblemIdEqualTo(problemId).andAdoptEqualTo(0)
                .andFeedbackNotEqualTo(Integer.parseInt(FeedbackEnum.FEED_BACK.getCode()));
        return answerMapper.selectByExampleWithBLOBs(answerExample);
    }

    /**
     * 根据problemId查找采纳回答 按时间排序
     *
     * @param problemId
     * @return 问题所有回答实体
     */

    public List<Answer> findAdoptByProblemId(Integer problemId) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.setOrderByClause("create_time desc");
        answerExample.createCriteria()
                .andProblemIdEqualTo(problemId).andAdoptEqualTo(Integer.parseInt(AdoptEnum.ADOPT.getCode()))
                .andFeedbackNotEqualTo(Integer.parseInt(FeedbackEnum.FEED_BACK.getCode()));
        return answerMapper.selectByExampleWithBLOBs(answerExample);
    }


    /**
     * 根据nickName查找对应的回答数
     *
     * @param nickName
     * @return
     */
    public Integer findAnswerNumByNickName(String nickName) {
        AnswerExample answerExample = new AnswerExample();
        AnswerExample.Criteria criteria = answerExample.createCriteria();
        criteria.andUserNameEqualTo(nickName);
        return answerMapper.countByExample(answerExample);
    }

    /**
     * 根据nickName返回回答集合
     * @param nickName
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Answer> findAnswerListByNickName(String nickName, Integer currentNum, Integer eachSize) {
        AnswerExample answerExample = new AnswerExample();
        answerExample.setOrderByClause("answer_id");
        AnswerExample.Criteria criteria = answerExample.createCriteria();
        criteria.andUserNameEqualTo(nickName);
        return answerMapper.selectByExampleWithBLOBsWithRowbounds(answerExample, new RowBounds((currentNum - 1) * eachSize, eachSize));
    }


}
