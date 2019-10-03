package com.screwmachine55open.verseit.util;

import java.util.Map;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/4 12:17
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class StringUtil {

    public static String generateUrlString(Map map) {

        StringBuilder url = new StringBuilder("?");

        map.forEach((k,v) -> {
            url.append(k+"="+v+"&");

        });
        String urls = url.toString();
        return  urls.substring(0,urls.length()- 1);
    }



}
