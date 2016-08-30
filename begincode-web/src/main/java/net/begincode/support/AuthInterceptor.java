package net.begincode.support;

import net.begincode.cookie.CookieOperation;
import net.begincode.core.model.BegincodeUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by saber on 2016/8/25.
 * 用户登录拦截器AuthInterceptor
 * cookie验证，查找数据库验证用户
 * 验证成功通过，验证失败返回登录
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public static final String NOLOGIN_JSON = "{\"msg\":\"未登录或已登出，请重新登录\"}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            //没有声明需要权限,或者声明不验证权限validate = false
            if (authPassport == null || authPassport.validate() == false) {
                return true;
            }
            //非ajax请求跳转登陆页面，ajax请求继续验证
            String requestType = request.getHeader("X-Requested-With");
            if (requestType == null) {
                doGet(request,response);
                return false;
            }
            //调用CookieOperation.getUser验证cookie,Ajax返回json
            BegincodeUser a = CookieOperation.getUser(request);
            if (a != null)
                return true;
            else {
                //弹窗提示请登录
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(NOLOGIN_JSON);
                response.getWriter().flush();
                return false;
            }
        }
        return true;
    }
    /**
     * doGet
     * @param： response
     * @throws： ServletException, IOException
     * */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg","需要登陆");
        // 设置跳转页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/page/login.jsp");
        dispatcher.forward(request, response);
    }

}