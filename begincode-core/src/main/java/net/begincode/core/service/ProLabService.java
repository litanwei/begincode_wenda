package net.begincode.core.service;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 根据问题标示查找对应的标签问题集合
     * @param problemId
     * @return
     */
    public List<ProblemLabel> findByProblemId(Integer problemId){
        ProblemLabelExample problemLabelExample = new ProblemLabelExample();
        ProblemLabelExample.Criteria criteria = problemLabelExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return problemLabelMapper.selectByExample(problemLabelExample);
    }
}
