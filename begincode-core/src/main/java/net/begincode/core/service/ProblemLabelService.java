package net.begincode.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import net.begincode.core.model.ProblemLabelExample.Criteria;

import org.springframework.stereotype.Service;

@Service
public class ProblemLabelService {

	@Resource
	private ProblemLabelMapper problemLabelMapper;

	@Resource
	private ProblemMapper problemMapper;

	/**
	 * 查询一个标签的问题
	 * @return 
	 */
	public List<Problem> selectAllLabel(Integer labelId) {

		ProblemLabelExample example = new ProblemLabelExample();
		Criteria criteria = example.createCriteria();
		criteria.andLabelIdEqualTo(labelId);
		List<ProblemLabel> proLabel = problemLabelMapper.selectByExample(example );
		List<Problem> pro = new ArrayList<Problem>();
		if(proLabel != null){
			for (ProblemLabel problemLabel : proLabel) {
				Problem problem = problemMapper.selectByPrimaryKey(problemLabel.getProLabelId());
				if(null != problem){
					pro.add(problem);
				}
			}
		}
		return pro;
	}

}
