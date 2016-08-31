package net.begincode.core.handler;

import net.begincode.core.model.Problem;
import net.begincode.core.service.ProblemService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Stay on 2016/8/26  21:41.
 */
@Component
public class ProblemHandler {
    @Resource
    private ProblemService problemService;

    /**
     * 新增问题
     * @param problem
     */
    public void addProblem(Problem problem)
    {
        problemService.createProblem(problem);
    }

    /**
     * 查找问题集合
     * @return
     */
    public List<Problem> selectAllProblem()
    {
        return problemService.findAllProblem();
    }

}
