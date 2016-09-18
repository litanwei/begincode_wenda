package net.begincode.core.handler;

import net.begincode.core.model.ProblemLabel;
import net.begincode.core.service.ProLabService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/8/31  20:13.
 */
@Component
public class ProLabHandler {
    @Resource
    private ProLabService proLabService;

    public void addProLab(ProblemLabel problemLabel)
    {
        proLabService.createProLab(problemLabel);
    }
}
