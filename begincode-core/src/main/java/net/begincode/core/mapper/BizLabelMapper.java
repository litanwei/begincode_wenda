package net.begincode.core.mapper;

import java.util.List;

import net.begincode.core.model.Label;

public interface BizLabelMapper {

	/**
	 * 查询热门标签
	 * @return
	 */
	List<Label> selectHotLabel();
}
