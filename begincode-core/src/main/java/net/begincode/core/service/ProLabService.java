package net.begincode.core.service;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.model.ProblemLabel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/26  20:24.
 */
@Service
public class ProLabService {
    @Resource
    private ProblemLabelMapper problemLabelMapper;

    /**
     * 新增标签对应的问题
     * @param problemLabel
     */
    public void addProLab(ProblemLabel problemLabel)
    {
        problemLabelMapper.insertSelective(problemLabel);
    }
}
