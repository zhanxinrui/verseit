package com.screwmachine55open.verseit.config;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 19:19
 * @description：拦截器 用于在调用controller前后进行的处理
 * @modified By：
 * @version: $version$
 */


public class VerseitInterception implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(VerseitInterception.class);//作为打印日志信息的标识

    /**
     *preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * 这里就是记录了一下登录的信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handler2 = (HandlerMethod) handler;
            Log annotation = handler2.getMethodAnnotation(Log.class);
            if (annotation != null) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("loginUser");
                String name = user != null ? "[" + user.getUserName() + "]" : "";
                logger.info(name + "[" + annotation.value() + "][" + handler2.getMethod().getDeclaringClass().getName() + "." + handler2.getMethod().getName() + "][" + request.getRemoteAddr() + "]");
            }
        }
        String token = CookieUtil.getCookieByKey(request,"token");
        logger.info("cookies token : "+token);
        return true;
    }
    /**
     *postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，
     * 但未进行页面渲染），有机会修改ModelAndView ；
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("postHandle1");

    }
/**
 *
 * afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("afterCompletion1");
    }


}


