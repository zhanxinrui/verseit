package com.screwmachine55open.verseit.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.service.UserService;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/10/3 21:44
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service

public class TokenService {

    private static final long EXPIRE_TIME = 50 * 60 * 1000;//50分钟过期
//    public static String getToken(User user){
//        String token = "";
//        token= JWT.create().withAudience(user.getUserId())// 将 user id 保存到 token 里面
//                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
//        return token;
//    }
    /**
     * 生成签名,50min后过期
     *
     * @param user 用户对象
     * @return 加密的token
     */
    public static String getToken(User user) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            // 附带username信息
            System.out.println("userId"+user.getUserId());
            String res = JWT.create()
                    .withClaim("userId", user.getUserId())
                    .withExpiresAt(date)
                    .sign(algorithm);
            String uid = JWT.decode(res).getClaim("userId").asString();

            System.out.println("uid:"+uid);


//            System.out.println("userdao:"+u);
            return res;
//            return JWT.create()
//                    .withClaim("userId", user.getUserId())
//                    .withExpiresAt(date)
//                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId").asString();
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param user 用户
     * @return 是否正确
     */
    public static void  verify(String token, User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", user.getUserId())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw exception;
        }
    }
}
