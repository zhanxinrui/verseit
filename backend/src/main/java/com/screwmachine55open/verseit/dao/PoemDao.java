package com.screwmachine55open.verseit.dao;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 22:06
 * @Version 1.0
 */
@Repository
public interface PoemDao  extends  MongoRepository<Poem,String>{

    Poem findByPoemId(String id);//这块用id就报错

}
