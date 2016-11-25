package net.begincode.core.mapper;

import net.begincode.core.model.ProblemLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Stay on 2016/11/25  16:45.
 */
public interface BizProblemLabelMapper extends ProblemLabelMapper {
    /**
     * 批量更新问题标签表
     *
     * @param list
     * @return
     */
    int batchInsertProLab(@Param("list") List<ProblemLabel> list);
}
