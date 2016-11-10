package net.begincode.core.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by saber on 2016/11/10.
 */
public interface BizAnswerMapper {
    /**
     * 根据回复标识增加赞同数量
     *
     * @param answerId
     * @return
     */
    int updateAgreeAddByAnswerId(@Param("answerId") Integer answerId);
    /**
     * 根据回复标识减少赞同数量
     *
     * @param answerId
     * @return
     */
    int updateAgreeReduceByAnswerId(@Param("answerId") Integer answerId);
    /**
     * 根据回复标识增加反对数量
     *
     * @param answerId
     * @return
     */
    int updateOppositionAddByAnswerId(@Param("answerId") Integer answerId);
    /**
     * 根据回复标识减少反对数量
     *
     * @param answerId
     * @return
     */
    int updateOppositionReduceByAnswerId(@Param("answerId") Integer answerId);
}
