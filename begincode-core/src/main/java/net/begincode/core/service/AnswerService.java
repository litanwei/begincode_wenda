package net.begincode.core.service;

import net.begincode.core.mapper.AnswerMapper;
import net.begincode.core.model.Answer;
import net.begincode.core.model.AnswerExample;
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
     * @param problemId
     * @return  单个回答实体
     */
    public Answer findOrderByProblemId(Integer problemId) {
        if(findByProblemId(problemId).size()>0){
            AnswerExample answerExample = new AnswerExample();
            answerExample.setOrderByClause("create_time desc limit 1");
            AnswerExample.Criteria criteria = answerExample.createCriteria();
            criteria.andProblemIdEqualTo(problemId);
            return answerMapper.selectByExample(answerExample).get(0);
        }else{
            return null;
        }
    }

}
