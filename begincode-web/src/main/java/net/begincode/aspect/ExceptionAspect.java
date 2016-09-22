package net.begincode.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * 统一异常处理 不处理@ResponseBody标记方法
 * ajax提交统一注解@ResponseBody 由ResponseAspect管理
 *
 * Created by saber on 2016/9/22.
 */

@Aspect
@Component
public class ExceptionAspect {

    Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("execution(* net.begincode.controller.*.*(..))")
    public void exceptionPointcut() {
    }
    @Around(value = "exceptionPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception be) {
            logger.error("***操作请求日志记录异常***",be);
            throw new RuntimeException("异常");
        }
        return result;
    }

}
