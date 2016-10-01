package net.begincode.core.mapper;

import java.util.List;

import net.begincode.core.model.Label;
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
}
