package com.screwmachine55open.verseit.serviceImpl;

import com.google.common.base.Objects;
import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.service.UserService;
import com.screwmachine55open.verseit.util.*;
import lombok.val;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.regex.Pattern;
import lombok.*;

import static com.screwmachine55open.verseit.util.Result.Code.OK;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 20:51
 * @description：${description}
 * @modified By：
 * @version: $version$
 */




@Service
public class UserServiceImpl implements UserService {
    @Autowired
    TokenService tokenService;
    @Autowired
    private  UserDao userDao;

    @Override
    public User findOneById(String id) {
        System.out.println("found id"+id);
        return userDao.findByUserId(id);

    }
    @Override
//    @Cacheable(value = "user", key = "#root.targetClass + T(String).valueOf(#userName)")
    public Result<User> getUserByUserName(String userName) {
        return Result.ok(userDao.findByUserName(userName));
    }

    /**
     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     * unless 表示条件表达式成立的话不放入缓存
     * #id是此函参数里的id
     * #result for a reference to the result of the method invocation. For supported wrappers such as Optional, #result refers to the actual object, not the wrapper
     * #root.method, #root.target, and #root.caches for references to the method, target object, and affected cache(s) respectively.
     * Shortcuts for the method name (#root.methodName) and target class (#root.targetClass) are also available.
     * Method arguments can be accessed by index. For instance the second argument can be accessed via #root.args[1], #p1 or #a1. Arguments can also be accessed by name if that information is available.
     */
//    @Cacheable(value = "user", key = "#root.targetClass + T(String).valueOf(#id)")
    @Override
    public Result<User> getUserByUserId(String id) {
        return Result.ok(userDao.findByUserId(id));
    }

//    @Override
//    public User getUserByGithubNodeId(String nodeId) {
//        return userDao.findByGithubNodeId(nodeId);
//    }

    /**
     * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
     * root是当前类型
     * result是返回值
     */
//    @CachePut(value = "user", key = "T(String).valueOf(#root.targetClass).concat('-').concat(#user.userName)", unless = "#user eq null or #user.userName eq null")
    @Override
    public User saveUser(User user) {
        if(!StringUtils.isEmpty(user.getGithubNodeId())){//避免同一github注册多次
            User result =  userDao.findByGithubNodeId(user.getGithubNodeId());
            if(result != null) {
//                System.out.println("there is one!");
                User userTmp = user;
                CombineBeans cb = new CombineBeans();
                user = (User) cb.combineSydwCore(user,result);//相同则前者覆盖后者

            }
        }
        if(user.getUserId()!=null){
            User result =  userDao.findByUserId(user.getUserId());
            if(result != null) {
                CombineBeans cb = new CombineBeans();
                user = (User) cb.combineSydwCore(user,result);
            }
        }
        if(user.getUserName()!=null){
            User result =  userDao.findByUserName(user.getUserName());
            if(result != null) {
                CombineBeans cb = new CombineBeans();
                user = (User) cb.combineSydwCore(user,result);
            }
        }
        if(user.getEmail()!=null){
            User result = userDao.findByEmail(user.getEmail());
            if(result != null) {
                CombineBeans cb = new CombineBeans();
                user = (User) cb.combineSydwCore(user,result);
            }
        }
        /*补齐工作*/
        //如果id为空 补齐
        if(StringUtils.isEmpty(user.getUserId())){
            user.setUserId(UUID.randomUUID().toString());//设置id为唯一识别码
        }
        //如果用户名为空 补齐
        if (user.getUserName() == null) {
            user.setUserName("游客_" + UUID.randomUUID().toString());//因为github用户名可能重复，所以对于首次github登陆的用户分配一个随机的用户名，随后他可以在账号中修改
        }
        System.out.println(user.getUserId());
        return userDao.save(user);
    }




    @Override
    public Result<User> register(User user) {
        System.out.println("tag1");
        User userResult = userDao.findByEmailOrUserName(user.getEmail(),user.getUserName());
        if(userResult == null){
            System.out.println("its a new account :"+user);
            return  Result.ok(this.saveUser(user));

        }
        return Result.error(userResult,"操作失败,该邮箱已存在！");


    }
    @Override
    public Result<List<User>> getAllUser () {
        List<User> userList = userDao.findAll();
        if(userList != null){
            //   System.out.println("its a new account :"+user);
            return  Result.ok(userList);

        }
        return Result.error(userList,"不存在用户！");
    }

    @Override
    public Result<Page<User>> getAllUserPage() {
        System.out.println("tag1");
        Pageable pageable =new PageRequest(0, 20);
        Page<User>  userList = userDao.findAll(pageable);

        if(userList != null){
            //   System.out.println("its a new account :"+user);
            return  Result.ok(userList);

        }
        return Result.error(null,"不存在用户！");
    }


    //    @Cacheable(value = "user", key = "#root.targetClass + #param", unless = "#result eq null") 不知道怎么表示param里的值。。
    @Override
    public Result<User> login(Map<String,String> param) {
        System.out.println("ok now");

        User user ;
        Result<User> result = Result.error(null,"操作失败");
        String token = param.getOrDefault("token",null);
        if(StringUtils.isNoneEmpty(token)){
            try {
                user = JsonUtil.tokenToObject(token,User.class);//这的token指的是json格式的输入数据
                result = this.loginUser(user);
                System.out.println("real token"+result.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    @Override
    //@CachePut(value = "user", key = "T(String).valueOf(#root.targetClass).concat('-').concat(#user.userName)", unless = "#user eq null or #user.userId eq null")
    public Result<User> loginUser(User user) {
        //若 github_node_id 非空  就不再生成
        if(!StringUtils.isEmpty(user.getGithubNodeId())){
            User result = getUserByGithubNodeId(user.getGithubNodeId());
            if(result != null) {
                System.out.println("result valid");
                return  Result.ok(result,tokenService.getToken(result));
            }else{
                System.out.println("result invalid");
                return Result.error(null,"操作失败,github登录失败");
            }
        }
        System.out.println("user:"+user);
        User userResult = userDao.findByEmailOrUserName(user.getEmail(),user.getUserName());
        if(userResult == null){
            System.out.println("账号不存在");
            return Result.error(null,"操作失败,该账号不存在！");
        }
        //密码不对
        if(!Objects.equal(userResult.getPassword(),user.getPassword())){
            System.out.println("密码错误");
            return Result.error(null,"操作失败,密码错误!");


        }
        System.out.println("here?");
        return  Result.ok(user,tokenService.getToken(userResult));

    }
    /*前端判断密码正误*/
    @Override
    public Result<String> changePasswd( String userName){

        User result = userDao.findByUserName(userName);

        if(result !=null){
            return Result.ok(null);
        }else return Result.error(null, "无此用户");//实际上并不存在这一error

    }
    /*删除账户*/
    @Override
    public Result<String> deleteUser( String userName){

        User user = userDao.findByUserName(userName);

        if(user !=null){
            userDao.deleteByUserName(userName);
            return Result.ok(null);
        }else return Result.error(null, "无此用户");//实际上并不存在这一error

    }

    @Override
    public User getUserByGithubNodeId(String nodeId) {
        return userDao.findByGithubNodeId(nodeId);
    }

    @Override
    public Result<String>sendEmail(String email){
        String pattern = "/^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$/";
        if(!Pattern.matches(pattern, email)){
            return Result.error(null,"邮箱格式不正确!");

        }
        //创建Random类对象
        Random random = new Random();

        //产生随机数
        Integer randomNum = random.nextInt(99999 - 11111 + 1) + 11111;
        try{
            MailUtil.sendMail(email, randomNum.toString());
        }catch(Exception e){
            return Result.error(null,"邮箱发送失败!");
        }
        return Result.ok(randomNum.toString());

    }



}
