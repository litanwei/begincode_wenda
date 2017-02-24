package net.begincode.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.begincode.bean.Param;
import net.begincode.bean.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Stay on 2016/9/13  18:31.
 */
@Aspect
@Component
public class RequestAspect {
    Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    @Pointcut("execution(* net.begincode.controller.*.*(..))")
    public void pointCut_() {
    }


    @Around("pointCut_()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] objects = proceedingJoinPoint.getArgs();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Param) {
                Param param = (Param) objects[i];
                param.check();
            }
        }
        MethodSignature joinPointObject = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(ResponseBody.class);
        Object returnObject = null;
        try {
            returnObject = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (flag && method.getReturnType().equals(Void.TYPE)) {
            voidResponse();
        }
        if (flag && !method.getReturnType().equals(Void.TYPE)) {
            return Response.success(returnObject);
        } else {
            return returnObject;
        }

    }

    private void voidResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(response.getOutputStream(), Response.success());
        } catch (IOException e) {
            logger.error("Response error", e);

        }
    }
}
