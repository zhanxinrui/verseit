package com.screwmachine55open.verseit.dao;

import com.screwmachine55open.verseit.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/29 16:07
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Repository
public interface  UserDao extends MongoRepository<User,String> {


    User findByUserName (String userName);

    User findByEmailOrUserName(String email, String userName);

    User findByUserId(String id);//这块用id就报错

    User findByGithubNodeId(String nodeId);

    User findByGithubNodeIdOrUserName(String nodeId, String userName);
    User findAllByGithubNodeId(String nodeId);
    User deleteByUserName(String userName);
    User findByEmail(String email);
}
