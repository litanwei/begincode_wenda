package net.begincode.core.mapper;

import net.begincode.core.model.Problem;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by Stay on 2016/9/17  21:21.
 */
public interface BizProblemMapper extends ProblemMapper {

    /**
     * 查找传入@userId的(@我的)问题集合
     * @param userId
     * @return
     */
    List<Problem> selectByUserIdWithMessageRowbounds(Integer userId,RowBounds rowBounds);

    Integer selectByUserIdWithMsgSize(Integer userId);





}
