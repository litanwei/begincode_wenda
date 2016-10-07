package net.begincode.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.begincode.core.model.ProAttention;

public interface Bzi_ProAttentionMapper extends ProAttentionMapper {
	
	List<ProAttention> statisticsByporblem(String condition);
	
	int selectByViewCount(Integer problem_id);
	
	int updateByProattention(@Param("row")String row,@Param("user_id")Integer user_id,@Param("problem_id")Integer problem_id);
	
	int updateByProblemViewS(@Param("views")Integer views,@Param("problem_id")Integer problem_id);
	
}