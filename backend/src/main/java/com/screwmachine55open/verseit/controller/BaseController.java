package com.screwmachine55open.verseit.controller;
/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 20:55
 * @description：contoller的基类 定义了一些处理token和获得request的方法 token包含再请求中
 * @modified By：
 * @version: $version$
 */
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.util.CookieUtil;
import com.screwmachine55open.verseit.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;



@Controller
@RequestMapping(Constant.BASE_URL + "/BaseRest")
public class BaseController {

    public static final String TOKEN ="token";
    public static final String TOKEN_USER_ID ="id";
    public static final String TOKEN_USER_EMAIL ="email";
    public static final String TOKEN_USER_NAME = "userName";

    @Resource
    HttpServletRequest request;
    @Resource
    HttpServletResponse response;

    public HttpServletRequest getRequest() {
        if(request==null){
        }
        return request;
    }
    public HttpServletResponse getResponse() {
        if(response==null){
        }
        return response;
    }

    /**
     * 获取浏览器的token,来判断是否是本人和获取本人信息
     * */
    public String getToken()  {
        if(getRequest()!=null){
            return CookieUtil.getCookieByKey(getRequest(),TOKEN);
        }
        return null;
    }


    public String getEmail() throws Exception {
        Map<String,String> token =  JsonUtil.tokenToObject(getToken(),Map.class);//将json转换成obj

        return token.getOrDefault(TOKEN_USER_EMAIL,null);
    }

    public String getUserId() {
        Map<String,String> token =  JsonUtil.tokenToObject(getToken(),Map.class);
        return token.getOrDefault(TOKEN_USER_ID,null);
    }
    public String getUserName(){
        Map<String,String>token = JsonUtil.tokenToObject(getToken(), Map.class);
        return token.getOrDefault(TOKEN_USER_NAME, null);
    }


}


