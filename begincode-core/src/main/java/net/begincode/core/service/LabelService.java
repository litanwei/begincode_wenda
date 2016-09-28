package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import net.begincode.core.mapper.BizLabelMapper;
import net.begincode.core.mapper.LabelMapper;
import net.begincode.core.model.Label;
import net.begincode.core.model.LabelExample;
import net.begincode.core.model.LabelExample.Criteria;

import org.springframework.stereotype.Service;

@Service
public class LabelService {

	@Resource
	private LabelMapper labelMapper;
	
	@Resource
	private BizLabelMapper bizLabelMapper;

	/**
	 * 查询所有标签
	 * @return 
	 */
	public List<Label> selectAllLabel() {

		return bizLabelMapper.selectHotLabel();
	}
}
