//package com.screwmachine55open.verseit.dao;
//
//import com.screwmachine55open.verseit.entity.Concern;
//import com.screwmachine55open.verseit.entity.Poem;
//import com.screwmachine55open.verseit.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//
///**
// * @author ：xrzhan
// * @date ：Created in 2019/10/5 16:41
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//@Service
//  public class PoemDaoImpl  {
//    @Autowired
//    UserDao userDao;
//    /*
//     * @fixme 以后可以给每个用户都建立一个tag 和concerned，根据不同用户来查tag和concerned
//     * */
//    enum TAG{DEFAULT,ANCIENT,MODERN,FOREIGN,RHYMED,ESSAY}
//    enum CONCERNED{DEFAULT,NOTABLE,FRIEND,SCHOOLMATE,FAMILY,SPECIALCONCERN}
//    /************************************************************广场部分********************************************************************************/
//
//    //二级菜单  type: pubPoem userPoem concennPoem
//     public Query getAllPublishedPoem(){
//        Query query = new Query();
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.and("type").is("published"));
//        return query;
//    }
//
//     public Query  getAllPersonalPoem(){
//        Query query = new Query();
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.and("type").is("personal"));
//        return query;
//    }
//
//     public Criteria getAllConcernedUsers(String userId){
//        Query query = new Query();
//        Collection<Concern> Concerns = userDao.findByUserId(userId).getConcerns();
//        ArrayList<Criteria> criteriaList = new ArrayList<>();
//        //定义一个无长度的数组，类型为 Criteria  因为orOperator中要求是一个Array
//        Criteria[] criteriaArray = {};
//
//        for (Concern x : Concerns) {
//            for (String y : x.getUsers()){
//                User singleUser = userDao.findByUserId(y);
//                Criteria criteria = new Criteria();
//                criteriaList.add(criteria.and(userId).is(singleUser.getUserId()));
//            }
//        }
//        if(criteriaList.size()>0){
//            //把无长度的数组实例出来，长度就位集合的个数
//            criteriaArray = new Criteria[criteriaList.size()];
//            for(int i = 0 ; i < criteriaList.size() ; i++){
//                //把集合中的条件对象全部存入数组中
//                criteriaArray[i] = criteriaList.get(i);
//            }
//        }
//        //最后把数组入参到 andOperator() 方法中
////        query.addCriteria(new Criteria().orOperator(criteriaArray));
//        return new Criteria().orOperator(criteriaArray);
////        return query;
//    }
//
//    /**
//     * Find coresponding Poem accroding to groupList
//     * @param group  {DEFAULT,NOTABLE,FRIEND,SCHOOLMATE,FAMILY,SPECIALCONCERN}
//     *
//     * */
//    public Criteria getAllConcernedUsersWithGroup(String userId,ArrayList<String>group){
//        HashSet<String> groupSet = new HashSet<String>() ;
//        for(String s:group ) groupSet.add(s);
//        Query query = new Query();
//        Collection<Concern> Concerns = userDao.findByUserId(userId).getConcerns();
//        ArrayList<Criteria> criteriaList = new ArrayList<>();
//        Criteria[] criteriaArray = {};
//        for (Concern x : Concerns) {
//            if(groupSet.size()>0 &&  groupSet.contains(x.getConcernDir()))
//                for (String y : x.getUsers()){
//                    User singleUser = userDao.findByUserId(y);
//                    Criteria criteria = new Criteria();
//                    criteriaList.add(criteria.and(userId).is(singleUser.getUserId()));
//                }
//        }
//        if(criteriaList.size()>0){
//            criteriaArray = new Criteria[criteriaList.size()];
//            for(int i = 0 ; i < criteriaList.size() ; i++){
//                criteriaArray[i] = criteriaList.get(i);
//            }
//        }
//        return new Criteria().orOperator(criteriaArray);
//    }
//
//
//
//    /**
//     * Find coresponding Poem accroding to styleList
//     * @param styleList   DEFAULT,ANCIENT,MODERN,FOREIGN,RHYMED,ESSAY
//     *
//     * */
//    public Criteria getStyle(ArrayList<String> styleList){
//        ArrayList<Criteria> criteriaList = new ArrayList<>();
//        //定义一个无长度的数组，类型为 Criteria  因为orOperator中要求是一个Array
//        Criteria[] criteriaArray = {};
//        for (String x : styleList) {
//                  Criteria criteria = new Criteria();
//                criteriaList.add(criteria.and("label").is(x));
//        }
//        if(criteriaList.size()>0){
//            //把无长度的数组实例出来，长度就位集合的个数
//            criteriaArray = new Criteria[criteriaList.size()];
//            for(int i = 0 ; i < criteriaList.size() ; i++){
//                //把集合中的条件对象全部存入数组中
//                criteriaArray[i] = criteriaList.get(i);
//            }
//        }
//        return new Criteria().andOperator(criteriaArray);
//    }
//
//    //三级菜单
//    public Sort getSort(ArrayList<String>sortList) {
//        List<Sort.Order> orders  = new ArrayList<>();
//        if(sortList.isEmpty()) return new Sort(Sort.Order.desc("like"));
//
//        for(String s: sortList)
//            orders.add(Sort.Order.desc(s));
//        return new Sort(orders);
//    }
//
//
//
//}
