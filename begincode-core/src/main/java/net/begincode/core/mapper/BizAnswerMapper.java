package net.begincode.core.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by saber on 2016/11/16.
 */
public interface BizAnswerMapper {

    /**
     *  根据回复id增加赞同人数
     *
     * @param answerId
     * @return
     */
    int updateAgrCountAddByAnswerId(@Param("answerId") Integer answerId);

    /**
     * 根据回复id减少赞同人数
     *
     * @param answerId
     * @return
     */
    int updateAgrCountReduceByProblemId(@Param("answerId") Integer answerId);

    /**
     * 根据回复id增加反对人数
     *
     * @param answerId
     * @return
     */
    int updateOppoCountAddByProblemId(@Param("answerId") Integer answerId);

    /**
     * 根据回复id减少反对人数
     *
     * @param answerId
     * @return
     */
    int updateOppoCountReduceByProblemId(@Param("answerId") Integer answerId);
}
