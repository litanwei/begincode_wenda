package net.begincode.core.service;

import net.begincode.common.BeginCodeConstant;
import net.begincode.common.BizException;
import net.begincode.core.enums.FindProResponseEnum;
import net.begincode.core.mapper.BizProblemMapper;
import net.begincode.core.mapper.ProAttentionMapper;
import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.ProAttention;
import net.begincode.core.model.ProAttentionExample;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Stay on 2016/8/26  20:18.
 */
@Service
public class ProblemService {
    @Resource
    private ProblemMapper problemMapper;

    @Resource
    private ProAttentionMapper proAttentionMapper;
    @Resource
    private BizProblemMapper bizProblemMapper;

    /**
     * 创建新问题
     *
     * @param problem
     */
    public int createProblem(Problem problem) {
        return problemMapper.insertSelective(problem);
    }


    /**
     * 查找总问题数
     *
     * @return
     */
    public Integer findProblemsSize() {
        ProblemExample problemExample = new ProblemExample();
        return problemMapper.countByExample(problemExample);
    }

    /**
     * 查找所有问题
     *
     * @return
     */
    public List<Problem> findProblemList() {
        ProblemExample problemExample = new ProblemExample();
        return problemMapper.selectByExampleWithBLOBs(problemExample);
    }


    /**
     * 我的问题分页
     *
     * @param userName
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Problem> findMyProblem(String userName, Integer currentNum, Integer eachSize) {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("problem_id desc");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        return problemMapper.selectByExampleWithRowbounds(problemExample,
                new RowBounds((currentNum - 1) * eachSize, eachSize));

    }


    /**
     * 新问题查询
     *
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Problem> findNewProblem(Integer currentNum, Integer eachSize) {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("create_time desc");
        return problemMapper.selectByExampleWithRowbounds(problemExample,
                new RowBounds((currentNum - 1) * eachSize, eachSize));
    }

    /**
     * 查找没有答案的问题集合
     *
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Problem> findNoAnswerProblem(Integer currentNum, Integer eachSize) {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("create_time desc");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andAnswerCountEqualTo(0);
        return problemMapper.selectByExampleWithRowbounds(problemExample, new RowBounds((currentNum - 1) * eachSize,
                eachSize));
    }

    /**
     * 查找问题
     * 按照浏览人数大小排序
     * 并且大于或等于指定时间的问题集合
     *
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Problem> findHotProblem(Integer currentNum, Integer eachSize) {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("view_count desc");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.add(Calendar.MONTH, BeginCodeConstant.HOTPROBLEM_SUBTRACT_MONTH);
            criteria.andCreateTimeGreaterThanOrEqualTo(dateFormat.parse(dateFormat.format(calendar.getTime())));   //查找大于或等于这个日期的问题集合
            List<Problem> list = problemMapper.selectByExampleWithRowbounds(problemExample,
                    new RowBounds((currentNum - 1) * eachSize, eachSize));
            return list;
        } catch (Exception e) {
            throw new BizException(FindProResponseEnum.PROBLEM_FIND_ERROR);
        }
    }

    /**
     * 查找id对应的收藏问题集合
     *
     * @param userId
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<Problem> selCollProlemsById(Integer userId, Integer currentNum, Integer eachSize) {
        List<ProAttention> list = selCollProAttById(userId, currentNum, eachSize);
        ArrayList<Problem> arrayList = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            Problem problem = problemMapper.selectByPrimaryKey(list.get(i).getProblemId());
            arrayList.add(problem);
        }
        return arrayList;
    }

    /**
     * 查找id对应的ProAttention集合
     *
     * @param userId
     * @param currentNum
     * @param eachSize
     * @return
     */
    public List<ProAttention> selCollProAttById(Integer userId, Integer currentNum, Integer eachSize) {
        ProAttentionExample proAttentionExample = new ProAttentionExample();
        proAttentionExample.setOrderByClause("problem_id desc");
        ProAttentionExample.Criteria criteria = proAttentionExample.createCriteria();
        criteria.andBegincodeUserIdEqualTo(userId);
        criteria.andCollectEqualTo(1);
        return proAttentionMapper.selectByExampleWithRowbounds(proAttentionExample, new RowBounds((currentNum - 1) * eachSize,
                eachSize));
    }

    /**
     * 根据用户名查找对应的用户集合
     *
     * @param userName
     * @return
     */
    public List<Problem> selectProByUserName(String userName) {
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        return problemMapper.selectByExample(problemExample);
    }


    /**
     * 查找热门问题总数
     *
     * @return
     */
    public Integer findHotProSize() {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("view_count desc");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.add(Calendar.MONTH, BeginCodeConstant.HOTPROBLEM_SUBTRACT_MONTH);
            criteria.andCreateTimeGreaterThanOrEqualTo(dateFormat.parse(dateFormat.format(calendar.getTime())));   //查找大于或等于这个日期的问题集合
            return problemMapper.countByExample(problemExample);
        } catch (Exception e) {
            throw new BizException(FindProResponseEnum.PROBLEM_FIND_ERROR);
        }
    }

    /**
     * 查找问题
     *
     * @param problemId
     * @return Problem
     */
    public Problem selProblemById(Integer problemId) {
        return problemMapper.selectByPrimaryKey(problemId);
    }

    /**
     * 查找问题
     *
     * @param record
     * @return Problem
     */
    public int updateProblem(Problem record) {
        return problemMapper.updateByPrimaryKey(record);
    }

    /**
     * 查找未回答问题总数
     *
     * @return
     */
    public Integer findNoAnswerSize() {
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andAnswerCountEqualTo(0);
        return problemMapper.countByExample(problemExample);
    }

    /**
     * 根据userName返回问题大小
     *
     * @param userName
     * @return
     */
    public Integer findByNickNameProblemSize(String userName) {
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        return problemMapper.countByExample(problemExample);
    }

    /**
     * 根据问题id 修改收藏 浏览 投票数
     *
     * @param problemId
     * @param view
     * @param collect
     * @param vote
     * @return
     */
    public Integer updateVoteCollByProId(Integer problemId, Integer view, Integer collect, Integer vote) {
        Problem problem = new Problem();
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        problem.setViewCount(view);
        problem.setCollectCount(collect);
        problem.setVoteCount(vote);
        return problemMapper.updateByExampleSelective(problem, problemExample);
    }

    /**
     * 根据问题id 修改浏览次数
     *
     * @param problemId
     * @param view
     * @return
     */
    public Integer updateViewByProId(Integer problemId, Integer view) {
        Problem problem = new Problem();
        problem.setViewCount(view);
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return problemMapper.updateByExampleSelective(problem, problemExample);
    }

    /**
     * 根据问题id修改收藏数
     *
     * @param problemId
     * @param collectCount
     * @return
     */
    public Integer updateCollByProId(Integer problemId, Integer collectCount) {
        Problem problem = new Problem();
        problem.setCollectCount(collectCount);
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return problemMapper.updateByExampleSelective(problem, problemExample);
    }

    /**
     * 根据问题id修改投票次数
     *
     * @param problemId
     * @param voteCount
     * @return
     */
    public Integer updateVoteByProId(Integer problemId, Integer voteCount) {
        Problem problem = new Problem();
        problem.setVoteCount(voteCount);
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return problemMapper.updateByExampleSelective(problem, problemExample);
    }


    /**
     * 通过问题id查找问题
     *
     * @param problemId
     * @return
     */
    public Problem findByProblemId(Integer problemId) {
        return problemMapper.selectByPrimaryKey(problemId);
    }


    /**
     * 批量增加浏览次数  list里面存着问题id
     *
     * @param list
     * @return
     */
    public Integer batchUpdateView(List<Problem> list){
        return bizProblemMapper.batchUpdateViewAdd(list);
    }

    /**
     * 通过问题id增加收藏次数
     *
     * @param problemId
     * @return
     */
    public Integer updateCollAddByProblemId(Integer problemId){
        return bizProblemMapper.updateCollAddByProblemId(problemId);
    }

    /**
     * 根据问题id减少收藏次数
     *
     * @param problemId
     * @return
     */
    public Integer updateCollReduceByProblemId(Integer problemId){
        return bizProblemMapper.updateCollReduceByProblemId(problemId);
    }

    /**
     * 根据问题id增加投票次数
     *
     * @param problemId
     * @return
     */
    public Integer updateVoteAddByProblemId(Integer problemId){
        return bizProblemMapper.updateVoteAddByProblemId(problemId);
    }

    /**
     * 根据问题id减少投票次数
     *
     * @param problemId
     * @return
     */
    public Integer updateVoteReduceByProblemId(Integer problemId){
        return bizProblemMapper.updateVoteReduceByProblemId(problemId);
    }

    /**
     * 根据问题id增加回复数量
     *
     * @param problemId
     * @return
     */
    public Integer updateAnswerAddByProblemId(Integer problemId){
        return bizProblemMapper.updateAnswerAddByProblemId(problemId);
    }


}
