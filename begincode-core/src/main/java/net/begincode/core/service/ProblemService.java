package net.begincode.core.service;

import net.begincode.bean.Page;
import net.begincode.common.BeginCodeConstant;
import net.begincode.core.mapper.BizProblemMapper;
import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemExample;
import net.begincode.core.param.PageParam;
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
     * 查找问题列表
     *
     * @return
     */
    public List<Problem> findAllProblem() {
        ProblemExample problemExample = new ProblemExample();
        return problemMapper.selectByExample(problemExample);
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
     * 根据用户名查找对应的后15条问题记录
     *
     * @param userName
     * @return
     */
    public void findMyProblem(String userName,Page<Problem> page) {
        Page<Problem> pg = myProblemPageSet(page,userName);
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("problem_id desc");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<Problem> list = problemMapper.selectByExampleWithRowbounds(problemExample,
                new RowBounds((pg.getCurrentNum() - 1) * pg.getPageEachSize(), pg.getPageEachSize() * pg.getCurrentNum()));
        page.setData(list);
    }
    /**
     * 传入问题id  返回@我的问题列表
     *
     * @param userId
     * @return
     */
    public void selectByUserIdWithMessage(Integer userId,Page<Problem> page) {
        Page<Problem> pg = problemWithMsgPageSet(page,userId);
        List<Problem> list = bizProblemMapper.selectByUserIdWithMessageRowbounds(userId,
                new RowBounds((pg.getCurrentNum() - 1) * pg.getPageEachSize(), pg.getPageEachSize() * pg.getCurrentNum()));
        page.setData(list);
    }

    /**
     * 新问题查询
     *
     * @return
     */
    public void findNewProblem(Page<Problem> page) {
        Page<Problem> pg = hotOrNewPageSet(page);
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("create_time desc");
        List<Problem> list = problemMapper.selectByExampleWithRowbounds(problemExample,
                new RowBounds((pg.getCurrentNum() - 1) * pg.getPageEachSize(), pg.getPageEachSize() * pg.getCurrentNum()));
        page.setData(list);
    }

    /**
     * 查找没有答案的问题集合
     *
     * @return
     */
    public void findNoAnswerProblem(Page<Problem> page) {
        page.setTotalNum(findNoAnswerSize());
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("create_time desc");
        List<Problem> list = bizProblemMapper.selectProblemWithNoAnswerRowbounds(new RowBounds((page.getCurrentNum() - 1) * page.getPageEachSize(),
                page.getPageEachSize() * page.getCurrentNum()));
        page.setData(list);
    }

    /**
     * 查找问题
     * 按照浏览人数大小排序
     * 并且大于或等于指定时间的问题集合
     * @param page
     */
    public void findHotProblem(Page<Problem> page) {
        Page<Problem> pg = hotOrNewPageSet(page);
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("view_count desc");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = dateFormat.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1 - BeginCodeConstant.HOTPROBLEM_SUBTRACT_MONTH) + "-01"
                    + " 00:00:00");
            criteria.andCreateTimeGreaterThanOrEqualTo(date);   //查找大于或等于这个日期的问题集合
            List<Problem> list = problemMapper.selectByExampleWithRowbounds(problemExample,
                    new RowBounds((pg.getCurrentNum() - 1) * pg.getPageEachSize(), pg.getPageEachSize() * pg.getCurrentNum()));
            page.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     *  查找未回答问题总数
     * @return
     */
    public Integer findNoAnswerSize(){
        return bizProblemMapper.selectNoAnswerSize();
    }

    /**
     * 根据userName返回问题大小
     * @param userName
     * @return
     */
    public Integer findMyProblemSize(String userName){
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        return problemMapper.countByExample(problemExample);
    }

    public Integer problemWithMsgSize(Integer userId){
        return bizProblemMapper.selectByUserIdWithMsgSize(userId);
    }



    /**
     * 新问题和热点问题分页设置
     *
     * @param page
     * @return
     */
    private Page<Problem> hotOrNewPageSet(Page<Problem> page) {
        page.setTotalNum(findProblemsSize());    //设置问题总数
        return page;
    }

    /**
     * 根据userName 的分页设置
     * @param page
     * @param userName
     * @return
     */
    private  Page<Problem> myProblemPageSet(Page<Problem> page,String userName){
        page.setTotalNum(findMyProblemSize(userName));
        return page;
    }

    /**
     * @ 我的问题分页设置
     * @param page
     * @param userId
     * @return
     */
    private Page<Problem> problemWithMsgPageSet(Page<Problem> page,Integer userId){
        page.setTotalNum(problemWithMsgSize(userId));
        return page;
    }

}
