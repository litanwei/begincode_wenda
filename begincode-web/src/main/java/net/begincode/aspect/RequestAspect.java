package net.begincode.aspect;

import net.begincode.bean.Param;
import net.begincode.bean.Response;
import net.begincode.common.BizException;
import net.begincode.enums.CommonResponseEnum;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

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
        HttpServletResponse response=null;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Param) {
                Param param = (Param) objects[i];
                param.check();
            }
            if(objects[i] instanceof HttpServletResponse){
            	response=(HttpServletResponse)objects[i];
            	response.setContentType("text/html");
            	response.setCharacterEncoding("UTF-8");
            }
        }
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
        	if(returnObject==null){
            	ObjectMapper mapper = new ObjectMapper();  
            	mapper.writeValue(response.getOutputStream(),new Response(CommonResponseEnum.SUCCESS.getCode(), CommonResponseEnum.SUCCESS.getMessage(), "success") );
        	}
            return Response.success(returnObject);
        }else{
            //非ResponseBody
            return returnObject;
        }
    }
}
