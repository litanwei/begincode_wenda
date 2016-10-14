package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.begincode.core.mapper.Bzi_ProAttentionMapper;
import net.begincode.core.mapper.ProAttentionMapper;
import net.begincode.core.model.ProAttention;

@Service
public class ProAttentionService {
	@Resource
	private Bzi_ProAttentionMapper bzi_ProAttentionMapper;
	@Resource
	private ProAttentionMapper proAttentionMapper;
	
	public List<ProAttention> statisticsByProblem(String condition){
		return bzi_ProAttentionMapper.statisticsByProblem(condition);
	}
	
	public int selectByViewCount(Integer problem_id){
		return bzi_ProAttentionMapper.selectByViewCount(problem_id);
	}
	
	public int updateByProattention(ProAttention proAttention){
		return bzi_ProAttentionMapper.updateByProattention(proAttention);
	}
	
	public int updateByProblemViewS(Integer views,Integer problem_id){
		return bzi_ProAttentionMapper.updateByProblemViewS(views, problem_id);
	}
	public int insert(ProAttention record){
		return proAttentionMapper.insert(record);
	};
	
}