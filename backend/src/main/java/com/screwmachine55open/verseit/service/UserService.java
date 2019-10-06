package com.screwmachine55open.verseit.service;

import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 18:29
 * @description：User的接口  请在serviceImpl完成实现
 * @modified By：
 * @version: $version$
 */
public interface UserService {

    Result<User> getUserByUserId(String id);

    User findOneById(String id);
    User getUserByGithubNodeId(String nodeId);


    Result<User> getUserByUserName(String userName);

    User saveUser(User user);

    Result<User>register(User user);

    Result<User>login(Map<String,String>param);//在内部调用了loginUser

    Result<User>loginUser(User user);

    Result<String>sendEmail(String email);
    Result<String> changePasswd( String userName);
    Result<String>deleteUser(String userName);

    public Result<List<User>> getAllUser ();

    public Result<Page<User>> getAllUserPage();


}
