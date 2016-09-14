package net.begincode.aspect;

import net.begincode.core.param.ProblemLabelParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Stay on 2016/9/13  18:31.
 */
@Aspect
@Component
public class ControllerProLabAspect {
    @Pointcut("execution(* net.begincode.controller.*.*(..))")
    public void pointCut_() {
    }


    @Around("pointCut_()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] objects = proceedingJoinPoint.getArgs();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof ProblemLabelParam) {
                ProblemLabelParam problemLabelParam = (ProblemLabelParam) objects[i];
                problemLabelParam.check();
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
