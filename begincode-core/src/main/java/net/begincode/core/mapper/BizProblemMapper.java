package net.begincode.core.mapper;

import net.begincode.core.model.Problem;
import org.apache.ibatis.session.RowBounds;

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
     * 查找传入@userId的(@我的)问题集合
     * @param userId
     * @return
     */
    List<Problem> selectByUserIdWithMessageRowbounds(Integer userId,RowBounds rowBounds);

    Integer selectByUserIdWithMsgSize(Integer userId);

    /**
     * 未回答问题分页查询
     * @param rowBounds
     * @return
     */
    List<Problem> selectProblemWithNoAnswerRowbounds(RowBounds rowBounds);

    /**
     * 没有回答的问题大小
     * @return
     */
    Integer selectNoAnswerSize();


}
