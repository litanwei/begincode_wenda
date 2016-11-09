package net.begincode.core.service;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stay on 2016/8/31  20:09.
 */
@Service
public class ProLabService {
    @Resource
    private ProblemLabelMapper problemLabelMapper;

    public void createProLab(ProblemLabel problemLabel) {
        problemLabelMapper.insertSelective(problemLabel);
    }

    /**
     * 根据问题标示查找对应的标签问题集合
     *
     * @param problemId
     * @return
     */
    public List<ProblemLabel> findByProblemId(Integer problemId) {
        ProblemLabelExample problemLabelExample = new ProblemLabelExample();
        ProblemLabelExample.Criteria criteria = problemLabelExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return problemLabelMapper.selectByExample(problemLabelExample);
    }


    /**
     * 传问题集合进入 返回对应的十个对应的标签号   如果不足十个 则以实际的为准
     *
     * @param list
     * @return
     */
    public List<Integer> findLabelIdByProblemId(List<Problem> list) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //查找问题id对应的标签问题集合
            List<ProblemLabel> proLabelList = findProLabelByProblemId(list.get(i).getProblemId());
            for (int j = 0; j < proLabelList.size(); j++) {
                ProblemLabel problemLabel = proLabelList.get(j);
                if (!arrayList.contains(problemLabel.getLabelId())) {
                    arrayList.add(problemLabel.getLabelId());
                    if (arrayList.size() > 10) {
                        return arrayList;
                    }
                }
            }
        }
        return arrayList;
    }

    /**
     * 根据问题id查找问题标签集合
     *
     * @param problemId
     * @return
     */
    public List<ProblemLabel> findProLabelByProblemId(Integer problemId) {
        ProblemLabelExample problemLabelExample = new ProblemLabelExample();
        ProblemLabelExample.Criteria criteria = problemLabelExample.createCriteria();
        criteria.andProblemIdEqualTo(problemId);
        return problemLabelMapper.selectByExample(problemLabelExample);
    }

    /**
     * 输入标签号 返回标签问题的数量
     *
     * @param labelId
     * @return
     */
    public Integer findNumByLabelId(Integer labelId) {
        ProblemLabelExample problemLabelExample = new ProblemLabelExample();
        ProblemLabelExample.Criteria criteria = problemLabelExample.createCriteria();
        criteria.andLabelIdEqualTo(labelId);
        return problemLabelMapper.countByExample(problemLabelExample);
    }


}
