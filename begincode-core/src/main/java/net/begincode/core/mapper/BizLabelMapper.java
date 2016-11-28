package net.begincode.core.mapper;

import java.util.List;

import net.begincode.core.model.Label;
import net.begincode.core.param.LabelAndProblemId;

import org.apache.ibatis.annotations.Param;

public interface BizLabelMapper {

	/**
	 * 查询热门标签
	 * @return
	 */
	List<Label> selectHotLabel();

	/**
	 * @desc 问题标识查询标签
	 * @param problemId
	 * @return
	 */
	List<Label> selectLabelByProblemId(@Param("problemId") Integer problemId);
	
	/**
	 *  如果为Null 返回所有P_L表的数据
	 * @param problemIds 一组problemId
	 * @return	返回problemId和对应lable的类列表
	 */
	List<LabelAndProblemId> selectLabelAndProblemIdByProblemId(List<Integer> problemIds);
}
