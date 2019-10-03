package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.util.MapUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

import static com.screwmachine55open.verseit.util.StringUtil.generateUrlString;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/4 12:14
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Api("用户模块控制器")
@RestController
@RequestMapping(Constant.BASE_URL + "/github")
public class OauthController {
    @RequestMapping(value = "/getUser/{code}", method = RequestMethod.GET)
    public Map getUser(@PathVariable String code)   {
        RestTemplate rest               = new RestTemplate();
        //向github验证获得accesstoken
        String tokenUrl = Constant.GITHUB_ACCESS_TOKEN_URL + generateUrlString((new MapUtils<String,String>().put("client_secret",Constant.GITHUB_client_secret).put("client_id",Constant.GITHUB_client_ID).put("code",code).map()) );
        Map<String,String> access_token = rest.getForObject(tokenUrl,Map.class);
        String tokenKet = access_token.get("access_token");
        System.out.println("access_token:"+access_token);
        String userUrl = Constant.GITHUB_USER_API_URL + generateUrlString(new MapUtils<String,String>().put("access_token",tokenKet).map());//https://api.github.com/user?access_token=c87faaf5879b7fe3bb7f3a2177c5f772795c3059
        System.out.println("userurl:"+userUrl);
        Map<String,String> githubUser = rest.getForObject(userUrl,Map.class);
        System.out.println("githubuser:"+githubUser);
        return githubUser;
    }
}
