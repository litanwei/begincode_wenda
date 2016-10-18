package net.begincode.aspect;

import net.begincode.bean.Response;
import net.begincode.common.BizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * Created by saber on 2016/10/17.
 */

@Aspect
@Component
public class ResponseBodyAspect {

    @Pointcut("execution(* net.begincode.controller.*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void responseBodyPointCut() {
    }


    @Around(value = "responseBodyPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature joinPointObject = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(ResponseBody.class) ;
        Object returnObject ;
        try {
            returnObject = proceedingJoinPoint.proceed();
        }catch (BizException e){
            return Response.failed(e.getStatus());
        }
        if(flag){
            //是ResponseBody
            return Response.success(returnObject);
        }else{
            //非ResponseBody
            return returnObject;
        }
    }



}
