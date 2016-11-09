package net.begincode.core.service;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.mapper.ProblemMapper;
import net.begincode.core.model.Problem;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import net.begincode.core.model.ProblemLabelExample.Criteria;
import net.begincode.core.param.ProblemLabelParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
	public List<ProblemLabelParam> selectAllProblemByLabel(Integer labelId) {

		ProblemLabelExample example = new ProblemLabelExample();
		Criteria criteria = example.createCriteria();
		criteria.andLabelIdEqualTo(labelId);
		//通过labelId查proLabel的所有对应关系
		List<ProblemLabel> proLabel = problemLabelMapper.selectByExample(example );
		//通过proLabel的关联查所有的problem数组
		List<ProblemLabelParam> pro = new ArrayList<ProblemLabelParam>();
		if(proLabel != null){
			for (ProblemLabel problemLabel : proLabel) {
				Problem problem = problemMapper.selectByPrimaryKey(problemLabel.getProblemId());
				if(null != problem){
					ProblemLabelParam problemLabelParam = new ProblemLabelParam();
					problemLabelParam.setProblem(problem);
					//TODO
//					problemLabelParam.setLabell(new LabelService().selectLabelByProblemId(problem.getProblemId()));
					pro.add(problemLabelParam);
				}
			}
		}
		return pro;
	}



}
