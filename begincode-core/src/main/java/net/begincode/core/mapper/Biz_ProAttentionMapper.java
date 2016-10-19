package net.begincode.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.begincode.core.model.ProAttention;

public interface Biz_ProAttentionMapper extends ProAttentionMapper {
	
	List<ProAttention> statisticsByProblem(String condition);
	
	int selectByViewCount(Integer problem_id);
	
	int updateByProattention(ProAttention proAttention);
	
	int updateByProblemViewS(@Param("views")Integer views,@Param("problem_id")Integer problem_id);

	/**
	 * 根据userId返回用户收藏的问题总数
	 * @param userId
	 * @return
	 */
	int selectCollectNumByUserId(Integer userId);
}