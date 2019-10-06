//package com.screwmachine55open.verseit.controller;
//
//import com.screwmachine55open.verseit.constant.Constant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author ：xrzhan
// * @date ：Created in 2019/4/28 23:15
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//@RestController
//public class TestRedis {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @RequestMapping(Constant.BASE_URL + "/redisHandler")
//
////    @RequestMapping("/redisHandler")
//    public String redisHandler(){
//        stringRedisTemplate.opsForValue().set("k5", "Springboot redis");
//        return stringRedisTemplate.opsForValue().get("k5");
//    }
//}