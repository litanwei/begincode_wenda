package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.mapper.ProblemLabelMapper;
import net.begincode.core.model.ProblemLabel;
import net.begincode.core.model.ProblemLabelExample;
import net.begincode.core.model.ProblemLabelExample.Criteria;

import org.springframework.stereotype.Service;

@Service
public class ProblemLabelService {

	@Resource
	private ProblemLabelMapper problemLabelMapper;
	

	/**
	 * 查询所有标签
	 * @return 
	 */
	public List<ProblemLabel> selectAllLabel(Integer labelId) {

		ProblemLabelExample example = new ProblemLabelExample();
		Criteria criteria = example.createCriteria();
		criteria.andLabelIdEqualTo(labelId);
		return problemLabelMapper.selectByExample(example );
	}

}
