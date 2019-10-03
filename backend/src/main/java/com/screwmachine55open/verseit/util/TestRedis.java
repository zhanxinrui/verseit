//package com.screwmachine55open.verseit.util;
//
//import com.screwmachine55open.verseit.entity.User;
//import static org.junit.Assert.*;
////import javafx.application.Application;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
////import org.springframework.util.Assert;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author ：xrzhan
// * @date ：Created in 2019/4/28 19:39
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class TestRedis {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;//
//    @Autowired
//    private RedisTemplate redisTemplate;//使用自己的模板
//    @Test
//    public void test() throws Exception {
//        stringRedisTemplate.opsForValue().set("aaa", "111");
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
//    }
//
//    @Test
//    public void testObj() throws Exception {
//        User user = new User("s1","jamski","jamski");
//        System.out.println("user:"+user);
//        ValueOperations<String, User> operations=redisTemplate.opsForValue();
//        operations.set("com.neox", user);
//        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
//        Thread.sleep(1000);
//        //redisTemplate.delete("com.neo.f");
//        boolean exists=redisTemplate.hasKey("com.neo.f");
//        if(exists){
//            System.out.println("exists is true");
//        }else{
//            System.out.println("exists is false");
//        }
//        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
//    }
//}
