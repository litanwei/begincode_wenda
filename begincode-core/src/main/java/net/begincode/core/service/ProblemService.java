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
     * 新问题查询
     *
     * @return
     */
    public void findNewProblem(PageParam<Problem> pageParam) {
        Page<Problem> page = pageParam.getPage();
        page.setPageCount(this.findAllProblem().size());
        int currentPage = page.getPageNum();
        int index = (currentPage - 1) * page.getPageSize();   //分页起始位置
        int count = page.getPageSize() * currentPage;         //分页结束位置
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("problem_id desc");
        List<Problem> list = problemMapper.selectByExampleWithRowbounds(problemExample, new RowBounds(index, count));
        page.setData(list);
    }

    /**
     * 根据用户名查找对应的后15条问题记录
     *
     * @param userName
     * @return
     */
    public List<Problem> findMyProblem(String userName) {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("problem_id desc limit 15");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        return problemMapper.selectByExample(problemExample);
    }

    /**
     * 查找问题
     * 按照浏览人数大小排序
     * 并且大于或等于指定时间的15条问题集合
     *
     * @return
     */
    public List<Problem> findHotProblem() {
        ProblemExample problemExample = new ProblemExample();
        problemExample.setOrderByClause("view_count desc limit 15");
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = dateFormat.parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1 - BeginCodeConstant.HOTPROBLEM_SUBTRACT_MONTH) + "-01"
                    + " 00:00:00");
            criteria.andCreateTimeGreaterThanOrEqualTo(date);   //查找大于或等于这个日期的问题集合
            return problemMapper.selectByExample(problemExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 查找没有答案的问题集合
     *
     * @return
     */
    public List<Problem> findNoAnswerProblem() {
        return bizProblemMapper.selectProblemWithNoAnswer();
    }

    /**
     * 传入问题id  返回@我的问题列表
     *
     * @param userId
     * @return
     */
    public List<Problem> selectByUserIdWithMessage(Integer userId) {
        return bizProblemMapper.selectByUserIdWithMessage(userId);
    }


}
