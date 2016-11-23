package net.begincode.core.mapper;

import net.begincode.core.model.Problem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by Stay on 2016/10/31 10:35.
 */
public interface BizProblemMapper extends ProblemMapper {

	/**
	 * 批量更新浏览次数
	 *
	 * @param list
	 * @return
	 */
	int batchUpdateViewAdd(@Param("list") List<Problem> list);

	/**
	 * 根据问题号增加收藏次数
	 *
	 * @param problemId
	 * @return
	 */
	int updateCollAddByProblemId(@Param("problemId") Integer problemId);

	/**
	 * 根据问题号减少收藏次数
	 *
	 * @param problemId
	 * @return
	 */
	int updateCollReduceByProblemId(@Param("problemId") Integer problemId);

	/**
	 * 根据问题标识增加投票次数
	 *
	 * @param problemId
	 * @return
	 */
	int updateVoteAddByProblemId(@Param("problemId") Integer problemId);

	/**
	 * 根据问题标识减少投票次数
	 *
	 * @param problemId
	 * @return
	 */
	int updateVoteReduceByProblemId(@Param("problemId") Integer problemId);

	/**
	 * 根据问题标识增加回复数量
	 *
	 * @param problemId
	 * @return
	 */
	int updateAnswerAddByProblemId(@Param("problemId") Integer problemId);

	/**
	 * 根据问题标识减少回复数量
	 *
	 * @param problemId
	 * @return
	 */
	int updateAnswerReduceByProblemId(@Param("problemId") Integer problemId);

	/**
	 * 通过pro_attention中的UserId获取对应的问题列表
	 * 
	 * @param pUserId
	 * @return
	 */
	List<Problem> selCollProlemsByPorUserId(@Param("pUserId") Integer pUserId, RowBounds rowBounds);
	
    /**
     * 通过关联表查询problem
     * @param labelId
     * @return
     */
    List<Problem> selectByProblemLabel(@Param(value="labelId")Integer labelId);
}
