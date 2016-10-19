package net.begincode.core.service;

import net.begincode.common.BeginCodeConstant;
import net.begincode.common.BizException;
import net.begincode.core.enums.FindProResponseEnum;
import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
     * @return
     */
    public List<Problem> findProblemList(){
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
     * @param problemId
     * @return Problem
     */
    public Problem selProblemById(Integer problemId){
        return problemMapper.selectByPrimaryKey(problemId);
    }

    /**
     * 查找问题
     * @param record
     * @return Problem
     */
    public int updateProblem(Problem record){
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



}
