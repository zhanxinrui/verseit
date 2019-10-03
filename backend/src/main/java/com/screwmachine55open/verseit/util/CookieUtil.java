package com.screwmachine55open.verseit.util;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 19:52
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;
import org.springframework.http.HttpRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CookieUtil<T> {
    /**
     * 根据Key遍历查找浏览器cookie对应的值
     */
    public static String getCookieByKey(HttpServletRequest request, String key)  {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(Objects.equals(key,cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }



}
