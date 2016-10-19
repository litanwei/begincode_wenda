package net.begincode.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.begincode.core.mapper.Biz_ProAttentionMapper;
import net.begincode.core.mapper.ProAttentionMapper;
import net.begincode.core.model.ProAttention;

@Service
public class ProAttentionService {
	@Resource
	private Biz_ProAttentionMapper biz_ProAttentionMapper;
	@Resource
	private ProAttentionMapper proAttentionMapper;
	
	public List<ProAttention> statisticsByProblem(String condition){
		return biz_ProAttentionMapper.statisticsByProblem(condition);
	}
	
	public int selectByViewCount(Integer problem_id){
		return biz_ProAttentionMapper.selectByViewCount(problem_id);
	}
	
	public int updateByProattention(ProAttention proAttention){
		return biz_ProAttentionMapper.updateByProattention(proAttention);
	}
	
	public int updateByProblemViewS(Integer views,Integer problem_id){
		return biz_ProAttentionMapper.updateByProblemViewS(views, problem_id);
	}
	public int insert(ProAttention record){
		return proAttentionMapper.insert(record);
	};

	/**
	 * 根据userId返回用户收藏的问题总数
	 * @param userId
	 * @return
	 */
	public int selectCollectNumByUserId(Integer userId){
		return biz_ProAttentionMapper.selectCollectNumByUserId(userId);
	}
	
}
