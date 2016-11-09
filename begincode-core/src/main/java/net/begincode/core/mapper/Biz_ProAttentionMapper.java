package net.begincode.core.mapper;

public interface Biz_ProAttentionMapper extends ProAttentionMapper {
	/**
	 * 根据userId返回用户收藏的问题总数
	 * @param userId
	 * @return
	 */
	int selectCollectNumByUserId(Integer userId);
}