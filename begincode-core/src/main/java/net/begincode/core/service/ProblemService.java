package net.begincode.core.service;

import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/26  20:18.
 */
@Service
public class ProblemService {
    @Resource
    private ProblemMapper problemMapper;

    public void addProblem(Problem problem)
    {
        problemMapper.insertSelective(problem);
    }

}
