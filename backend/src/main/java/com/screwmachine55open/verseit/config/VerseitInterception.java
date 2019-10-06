package com.screwmachine55open.verseit.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.annotation.PassToken;
import com.screwmachine55open.verseit.annotation.UserLoginToken;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.service.UserService;
import com.screwmachine55open.verseit.serviceImpl.TokenService;
import com.screwmachine55open.verseit.util.CookieUtil;
import com.screwmachine55open.verseit.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 19:19
 * @description：拦截器 用于在调用controller前后进行的处理
 * @modified By：
 * @version: $version$
 */


public class VerseitInterception implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    private UserDao userDao;
    Logger logger = LoggerFactory.getLogger(VerseitInterception.class);//作为打印日志信息的标识

    /**
     *preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * 这里就是记录了一下登录的信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandlerjj");
        String token = request.getHeader("Authorization");//token还是放到header中,其他的信息放到body中
        System.out.println("preHandle:"+token);
        //如果不是映射到方法就直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handler2 = (HandlerMethod) handler;
            Method method = handler2.getMethod();
            Log annotation = handler2.getMethodAnnotation(Log.class);
//            String cookie = CookieUtil.getCookieByKey(request,"token");
//            logger.info("cookies  cookie: "+cookie);
            //检查是否有passtoken 注释，有就跳过认证
            if(method.isAnnotationPresent(PassToken.class)){
                PassToken passToken = method.getAnnotation(PassToken.class);
                if(passToken.required()) {//如果有passtoken注释就跳过认证
                    return true;
                }
            }
            //检查有没有需要用户权限的注解
            if(method.isAnnotationPresent(UserLoginToken.class)){
                System.out.println("userlogintoken need");
                UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
                if(userLoginToken.required()){
                    //执行认证
                    if(token==null) throw new RuntimeException("no token, please login again");
                    String userId;
                    try{
//                        userId = JWT.decode(token).getAudience().get(0);
                        System.out.println("token now:"+token);
                        userId = tokenService.getUserId(token);
//                        userId = JWT.decode(token).getClaim("userId").asString();
//                        System.out.println("getClaim"+JWT.decode(token));
                    }catch(JWTDecodeException j){
                        System.out.println("error 1");
                        throw new RuntimeException("401");
                    }

                    System.out.println("userId"+userId);
//                    User user = userService.getUserByUserId(userId).getData();
//                    User user = userService.findOneById("d5cd486b-afe3-4092-9916-91916785b2f8");
                    User user1 = userService.findOneById(userId);
                    System.out.println("find user:"+user1);
                    if(user1 == null) {
                        System.out.println("error 2");
                        throw new RuntimeException("User not exist, please login again");
                    }

                    //验证token
//                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                    try{
                        System.out.println("error 3");
                        tokenService.verify(token,user1);
                        System.out.println("passed");
                    }catch(JWTVerificationException e){
                        throw new RuntimeException("401");
                    }

                    return true;
                }
            }
            System.out.println("fine 1");
            return true;
//            if (annotation != null) {
//                HttpSession session = request.getSession();
//                User user = (User) session.getAttribute("loginUser");
//                String name = user != null ? "[" + user.getUserName() + "]" : "";
//                logger.info(name + "[" + annotation.value() + "][" + handler2.getMethod().getDeclaringClass().getName() + "." + handler2.getMethod().getName() + "][" + request.getRemoteAddr() + "]");
//            }
        }
        System.out.println("fine 2");

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


