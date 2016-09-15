package net.begincode.core.service;

import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param problem
     */
    public int createProblem(Problem problem)
    {
        return problemMapper.insertSelective(problem);
    }

    /**
     * 查找问题列表
     * @return
     */
    public List<Problem> findAllProblem()
    {
        ProblemExample problemExample = new ProblemExample();
        return problemMapper.selectByExample(problemExample);
    }

    /**
     * 查找问题
     * @param problemId
     * @return Problem
     */
    public Problem selectByPrimaryKey(Integer problemId){
        return problemMapper.selectByPrimaryKey(problemId);
    }


}
