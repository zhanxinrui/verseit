package com.screwmachine55open.verseit.serviceImpl;

import com.screwmachine55open.verseit.entity.Poem;
//import org.springframework.data.mongodb.core.query.Criteria;

import java.util.*;

import com.screwmachine55open.verseit.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


/**
 * @author ：xrzhan
 * @date ：Created in 2019/10/5 11:16
 * @description：${涉及诗歌广场中的相关诗歌serivce}
 * @modified By：
 * @version: 1.0
 */
@Service

public class PoemServiceImpl implements PoemService {

    @Autowired
    private MongoTemplate mongoTemplate;
//    @Autowired
//    private ConcernService concernService;
//    @Autowired
//    private  UserDao userDao;
//    private PoemDaoRelatedImpl poemDaoImpl;
    /*
    * @fixme 以后可以给每个用户都建立一个tag 和concerned，根据不同用户来查tag和concerned
    * */
//    enum TAG{DEFAULT,ANCIENT,MODERN,FOREIGN,RHYMED,ESSAY}
    String[] TAG = {"default","ancient","modern","foreign","rhymed","essay"};
    String[] CONCERNED = {"default","notable","friend","schoolmate","family","specialconcern"};
//    enum CONCERNED{DEFAULT,NOTABLE,FRIEND,SCHOOLMATE,FAMILY,SPECIALCONCERN}
    /**
     * 广场部分
     * */
    //二级菜单  type: pubPoem userPoem concennPoem
    @Override
     public List<Poem>getAllPublishedPoem(HashMap<String,ArrayList> param){
        PoemDaoRelatedImpl poemDaoRelatedImpl = new PoemDaoRelatedImpl();
        //通过连接多个
//         Query query  = getAllPublishedPoem();
//         Query query = new Query();
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.and("type").is("published"));
        ArrayList <String> styleList = param.get("style");
        ArrayList<String> sortList = param.get("sort");
        Query query = new Query();
        query.addCriteria(poemDaoRelatedImpl.getStyle(styleList))
                .with(poemDaoRelatedImpl.getSort(sortList)); //得到所有需要关注的用户id增加为criteria
        List<Poem> resultList= mongoTemplate.find(query, Poem.class);
        return resultList;

    }



//
    public List<Poem>  getAllPersonalPoem(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.and("type").is("personal"));
        List<Poem> resultList= mongoTemplate.find(query, Poem.class);
        return resultList;
    }
    /**
     * Find Concerned Poem according to param
     * */
    @Override
    public List<Poem>getAllConcernedPoem(HashMap<String,ArrayList>param, String userId){
        PoemDaoRelatedImpl poemDaoRelatedImpl = new PoemDaoRelatedImpl();
        //{sort:List<String>,style:List<String>,group:List<String>
        ArrayList <String> styleList = param.get("style");
        ArrayList <String> groupList = param.get("group");
        ArrayList<String> sortList = param.get("sort");
        Query query = new Query();
        query.addCriteria(poemDaoRelatedImpl.getAllConcernedUsersWithGroup(userId,groupList))
                .addCriteria(poemDaoRelatedImpl.getStyle(styleList))
                .with(poemDaoRelatedImpl.getSort(sortList)); //得到所有需要关注的用户id增加为criteria
        List<Poem> resultList= mongoTemplate.find(query, Poem.class);
        return resultList;
    }





//
//    //三级菜单  Sort
//    Query  getDefalutPoem();
//    Query  getHotPoem();
//    Query  getNewPoem();
//
//    //四级菜单  style
//    Query getTagPoem(Query query,List<Integer> tagList);
//
//    //五级菜单  Group
//    Query getConcernPem(Query query,List<Integer> concernList);
//
//    //执行
//    List<Poem> ExecuteQuery(Query query);



}
