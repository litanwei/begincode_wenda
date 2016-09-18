package net.begincode.core.mapper;

import net.begincode.core.model.Problem;

import java.util.List;

/**
 * Created by Stay on 2016/9/17  21:21.
 */
public interface BizProblemMapper extends ProblemMapper {
    List<Problem> selectProblemWithNoAnswer();
}
