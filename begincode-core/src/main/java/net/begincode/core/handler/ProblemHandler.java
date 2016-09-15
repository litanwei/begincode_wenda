package net.begincode.core.handler;

import net.begincode.core.model.Problem;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by saber on 2016/9/15.
 */

@Component
public class ProblemHandler {

    @Resource
    private ProblemService problemService;

    /**
     * 根据id查询问题
     */
    public Problem selectById(int answerId){
        return problemService.selectByPrimaryKey(answerId);
    }
}
