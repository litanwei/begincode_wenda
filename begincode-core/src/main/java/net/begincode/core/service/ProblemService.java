package net.begincode.core.service;

import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by saber on 2016/9/15.
 */
@Service
public class ProblemService {

    @Resource
    private ProblemMapper problemMapper;

    public Problem selectByPrimaryKey(Integer problemId){
        return problemMapper.selectByPrimaryKey(problemId);
    }


}
