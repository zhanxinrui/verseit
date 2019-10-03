package com.screwmachine55open.verseit.dao;

import com.screwmachine55open.verseit.entity.Poet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * @Author: wuquanda
 * @Date: 2019/5/2 20:45
 * @Version 1.0
 */

@Repository
public interface PoetDao extends  MongoRepository<Poet,String>{
    Poet findByPoetId(String poetId);

}
