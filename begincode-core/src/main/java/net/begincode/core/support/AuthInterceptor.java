package net.begincode.core.support;

import net.begincode.core.cookie.CookieOperation;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by saber on 2016/8/25.
 * 用户登录拦截器AuthInterceptor
 * cookie验证，查找数据库验证用户
 * 验证成功通过，验证失败返回登录
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public static final String NOLOGIN_JSON = "{\"msg\":\"未登录或已登出，请重新登录\"}";
    public static final String NOLOGIN_MSG = "检测到您的操作处于未登录或登录时间超时状态，请重新登录。";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            //没有声明需要权限,或者声明不验证权限validate = false
            if (authPassport == null || authPassport.validate() == false) {
                return true;
            }
            //cookie验证账户权限
            String openId = CookieOperation.getUser(request);
            if (openId != null){
                return true;
            }
            //跳转到首页
            String requestType = request.getHeader("X-Requested-With");
            if (requestType == null) {
                request.getRequestURI();
                request.setAttribute("msg", NOLOGIN_MSG);
                request.getRequestDispatcher("/page/index.jsp").forward(request,response);
                return false;
            }
            else{
                //返回json
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(NOLOGIN_JSON);
                response.getWriter().flush();
                return false;
            }
        }
        return true;
    }

}
