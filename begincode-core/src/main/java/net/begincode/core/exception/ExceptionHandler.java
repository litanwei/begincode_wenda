package net.begincode.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by saber on 2016/10/17.
 */
public class ExceptionHandler extends DefaultHandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        logger.error("执行出错，堆栈信息如下：", ex);
        ex.printStackTrace();
        return new ModelAndView("redirect:/page/list.jsp");
    }
}
