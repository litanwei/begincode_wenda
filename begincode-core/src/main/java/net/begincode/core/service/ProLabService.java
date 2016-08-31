package net.begincode.core.service;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.model.ProblemLabel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/31  20:09.
 */
@Service
public class ProLabService {
    @Resource
    private ProblemLabelMapper problemLabelMapper;

    public void createProLab(ProblemLabel problemLabel)
    {
        problemLabelMapper.insertSelective(problemLabel);
    }
}
