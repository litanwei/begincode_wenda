package net.begincode.aspect;


import net.begincode.bean.Response;
import net.begincode.common.BizException;
import net.begincode.core.enums.AnswerResponseEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 切入类@ResponseBody 统一json格式 封装到Response类中
 * Created by saber on 2016/9/17.
 */

@Aspect
@Component
public class ResponseAspect{

    Logger logger = LoggerFactory.getLogger(ResponseAspect.class);

    @Resource
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Pointcut("execution(* net.begincode.controller.*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void responseBodyPointCut() {
    }

    @Around(value = "responseBodyPointCut()")
    public void ResultJSON(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = pjp.proceed();
        Response responseBase;
        if(ret == null || ret.equals("")){
            responseBase = Response.success(ret);
        }else{
            responseBase = Response.success();
        }
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        mappingJackson2HttpMessageConverter.write(responseBase, MediaType.APPLICATION_JSON, outputMessage);
        shutdownResponse(response);

    }

    @AfterThrowing(pointcut = "responseBodyPointCut()",throwing = "throwable")
    public void handleForException(Throwable throwable) throws Throwable{
        Response responseBase;
        if (throwable instanceof BizException) {
            responseBase = Response.failed(((BizException)throwable).getStatus());
        } else{
            logger.error("***操作请求日志记录异常***",throwable);
            responseBase = new Response("1","请求失败",null);
        }
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        mappingJackson2HttpMessageConverter.write(responseBase, MediaType.APPLICATION_JSON, outputMessage);
        shutdownResponse(response);
    }

    private void shutdownResponse(HttpServletResponse response) throws IOException {
        response.getOutputStream().close();
    }




}
