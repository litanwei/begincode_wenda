package net.begincode.core.mapper;

import net.begincode.core.model.Problem;

import java.util.List;

/**
 * Created by Stay on 2016/9/17  21:21.
 */
public interface BizProblemMapper extends ProblemMapper {
    /**
     * 查找未回答的问题
     * @return
     */
    List<Problem> selectProblemWithNoAnswer();

    /**
     * 查找@userId的问题集合
     * @param userId
     * @return
     */
    List<Problem> selectByUserIdWithMessage(Integer userId);
}
