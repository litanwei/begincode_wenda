package net.begincode.aspect;

import net.begincode.bean.Param;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Stay on 2016/9/13  18:31.
 */
@Aspect
@Component
public class RequestAspect {
    @Pointcut("execution(* net.begincode.controller.*.*(..))")
    public void pointCut_() {
    }


    @Around("pointCut_()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] objects = proceedingJoinPoint.getArgs();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Param) {
                Param param = (Param) objects[i];
                param.check();
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
