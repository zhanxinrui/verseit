package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.serviceImpl.UserServiceImpl;
import com.screwmachine55open.verseit.util.Result;
import io.netty.buffer.PoolSubpageMetric;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.Map;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 18:42
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
    @Api("用户模块控制器")
    @RestController
    @RequestMapping(Constant.BASE_URL + "/user")
    public class UserController extends BaseController {

        @Autowired
        UserServiceImpl userService;

        @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = "application/json")
        public Result<User> register(@RequestBody User user){
            System.out.println("register:"+user);
            return userService.register(user);
        }

        /*对应了进行了保存新的用户和对用户信息的修改的操作*/
        @Log("保存用户")
        @RequestMapping(value = "/saveUser", method = RequestMethod.POST,consumes = "application/json")
        public Result<User> saveUser(@RequestBody User user){
//            System.out.println("save user:"+user);
            return Result.ok(userService.saveUser(user));
        }

        /*单独提供一个修改密码的功能,其他的功能通过前端设置，后端saveUser完成*/
        @Log("修改密码")
        @RequestMapping(value = "/changePasswd", method = RequestMethod.POST,consumes = "application/json")
        public Result<User> changePasswd(@RequestBody User user){
    //            System.out.println("save user:"+user);
            return Result.ok(userService.saveUser(user));
        }
        @Log("tocken登录")
        @RequestMapping(value = "/login", method = RequestMethod.POST,consumes = "application/json")
        public  Result<User> login(@RequestBody Map<String,String> param){
            System.out.println("login:"+param);
            return userService.login(param);
        }

        @Log("根据userName获取用户信息")
        @RequestMapping(value = "/getUserInfo/{userName}", method = RequestMethod.GET)
        public Result<User> getUserInfo (@PathVariable(value = "userName",name = "userName") String userName ){
//            System.out.println("getInfo from userName"+userName);
            return userService.getUserByUserName(userName);
//        return userService.getUserById(getUserId());
        }

        @Log("返回邮箱验证码")
        @RequestMapping(value = "/getMail/{email}",method = RequestMethod.GET)
        public Result<String> sendEmail(@PathVariable(value = "email",name = "email") String email){
            return userService.sendEmail(email);
        }


        @Log("删除用户")
        @RequestMapping(value = "/delete/user",method = RequestMethod.GET)
        public Result<String> deleteUser(){
            return userService.deleteUser(getUserName());
        }









}


