package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.mapper.LabelMapper;
import net.begincode.core.model.Label;
import net.begincode.core.model.LabelExample;

import org.springframework.stereotype.Service;

@Service
public class LabelService {

	@Resource
	private LabelMapper labelMapper;

	/**
	 * 查询所有标签
	 * @return 
	 */
	public List<Label> selectAllLabel() {

		
		LabelExample example = new LabelExample();
		
		example.setOrderByClause("label_id desc");
		
		return labelMapper.selectByExample(example);
	}
}
