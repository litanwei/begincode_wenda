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


	public List<ProblemLabel> getPrlblemLabelByLabelId(Integer labelId){
		ProblemLabelExample example = new ProblemLabelExample();
		ProblemLabelExample.Criteria criteria = example.createCriteria();
		criteria.andLabelIdEqualTo(labelId);
		//通过labelId查proLabel的所有对应关系
		List<ProblemLabel> proLabel = problemLabelMapper.selectByExample(example );
		return proLabel;
	}


}
