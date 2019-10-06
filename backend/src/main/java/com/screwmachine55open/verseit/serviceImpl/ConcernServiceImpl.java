package com.screwmachine55open.verseit.serviceImpl;

import com.screwmachine55open.verseit.dao.PoetDao;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.Concern;
import com.screwmachine55open.verseit.entity.Poet;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/5 23:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */


@Service
public class ConcernServiceImpl implements ConcernService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PoetDao poetDao;


    /**
     * 获取指定文件夹个人关注
     * */
    @Override
    public ArrayList<Poet> getConcernPoetsByDir(String userName, String ConcernDir) {
        Concern ConcernRes = new Concern();
        try {
            Collection<Concern> Concerns = userDao.findByUserName(userName).getConcerns();
            for (Concern x : userDao.findByUserName(userName).getConcerns()) {
                if (x.getConcernDir().equals(ConcernDir)) {
                    ConcernRes = x;
                    break;
                }
            }
            ArrayList<Poet> queryRes = new ArrayList<>();
            for (String x : ConcernRes.getPoets()) {
                Poet singlePoet = poetDao.findByPoetId(x);
                queryRes.add(singlePoet);
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public ArrayList<User> getConcernUsersByDir(String userName, String ConcernDir) {
        Concern ConcernRes = new Concern();
        try {
            Collection<Concern> Concerns = userDao.findByUserName(userName).getConcerns();
            for (Concern x : userDao.findByUserName(userName).getConcerns()) {
                if (x.getConcernDir().equals(ConcernDir)) {
                    ConcernRes = x;
                    break;
                }
            }
            ArrayList<User> queryRes1 = new ArrayList<>();
            for(String x:ConcernRes.getUsers()){
                User singleUser = userDao.findByUserId(x);
                queryRes1.add(singleUser);
            }
            return queryRes1;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 获取所有关注文件夹的所有诗人
     * */
    @Override
    public ArrayList<Poet>  getConcernPoetsByDefault(String userName) {
        ArrayList<Poet> queryPoets = new ArrayList<>();
        try {

            Collection<Concern> Concerns = userDao.findByUserName(userName).getConcerns();
            for (Concern x : Concerns) {


                for (String y : x.getPoets()) {
                    Poet singlePoet = poetDao.findByPoetId(y);
                    queryPoets.add(singlePoet);
                }
            }
            Map<String, ArrayList> finalRes = new HashMap<String,ArrayList>() ;
            return queryPoets;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 获取所有关注文件夹的所有用户
     * */
    @Override
    public ArrayList<User>  getConcernUsersByDefault(String userName) {
        ArrayList<User> queryUsers = new ArrayList<>();
        try {

            Collection<Concern> Concerns = userDao.findByUserName(userName).getConcerns();
            for (Concern x : Concerns) {

                for (String y : x.getUsers()) {
                    User singleUser = userDao.findByUserId(y);
                    queryUsers.add(singleUser);
                }
            }
            return queryUsers;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 获取所有关注文件夹名
     * */
    public ArrayList<String> getConcernDir(String userName) {
        try {
            Collection<Concern> queryRes = userDao.findByUserName(userName).getConcerns();
            ArrayList<String> dirRes = new ArrayList<>();
            for (Concern i : queryRes) {
                dirRes.add(i.getConcernDir());
            }
            return dirRes;

        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * 删除指定关注文件夹
     * */

    public String delConcernDir(String userName, String ConcernDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            List<String> dirRes = new ArrayList<>();
            for (Concern i : queryRes) {
                if (i.getConcernDir().equals(ConcernDir))
                    queryRes.remove(i);

            }
            user.setConcerns(queryRes);

            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 添加关注文件夹
     * */
    public String addConcernDir(String userName, String ConcernDir) throws Exception{
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            List<String> dirRes = new ArrayList<>();
            for (Concern i : queryRes) {

                if (i.getConcernDir().equals(ConcernDir))
                    throw new Exception("e");
            }
            Concern tmp = new Concern();
            tmp.setConcernDir(ConcernDir);
            queryRes.add(tmp);
            user.setConcerns(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 在指定关注文件夹下添加诗人
     * */
    public String addConcernPoetsInDir(String userName, String ConcernDir, String PoetId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            boolean tag = false ;
            for (Concern i : queryRes) {
                if (i.getConcernDir().equals(ConcernDir)) {
                    Collection<String> resPoets = i.getPoets();
                    resPoets.add(PoetId);
                    i.setPoets(resPoets);
                    tag = true;
                    break;
                }
            }
            if(tag==false){//如果不存在,就创建一个新的文件夹
                Concern newConcern = new Concern();
                newConcern.getPoets().add(PoetId);
                newConcern.setConcernDir(ConcernDir);
                queryRes.add(newConcern);
            }
            user.setConcerns(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }
    /**
     * 在指定关注文件夹下添加用户
     * */
    public String addConcernUsersInDir(String userName, String ConcernDir, String UserId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            boolean tag = false ;
            for (Concern i : queryRes) {
                if (i.getConcernDir().equals(ConcernDir)) {
                    Collection<String> resPoets = i.getUsers();
                    resPoets.add(UserId);
                    i.setUsers(resPoets);
                    tag = true;
                    break;
                }
            }
            if(tag==false){//如果不存在,就创建一个新的文件夹
                Concern newConcern = new Concern();
                newConcern.getUsers().add(UserId);
                newConcern.setConcernDir(ConcernDir);
                queryRes.add(newConcern);
            }
            user.setConcerns(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }



    /**
     * 删除诗人 在所有文件夹下
     * @fixme  应该在数据库中查
     * */
    public String delConcern(String userName, String PoetId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            List<String> dirRes = new ArrayList<>();
            for (Concern i : queryRes) {
                for(String j : i.getPoets()){
                    if(PoetId==j)
                        i.getPoets().remove(j);                }
            }
            user.setConcerns(queryRes);//可能不需要
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * 在指定关注文件夹下删除诗人
     * @fixme 应该在数据库中删除
     * */
//    public String delConcernInDir(String userName, String ConcernDir, String PoetId) {
//        User user = userDao.findByUserName(userName);
//        try {
//            Collection<Concern> queryRes = user.getConcerns();
//            List<String> dirRes = new ArrayList<>();
//            for (Concern i : queryRes) {
//                if (i.getConcernDir().equals(ConcernDir)) {
//                    for(String j : i.getPersons()){
//                        if(PoetId==j)
//                            i.getPersons().remove(j);
//                    }
//                }
//            }
//
//            user.setConcerns(queryRes);//可能不需要
//            userDao.save(user);
//            return "ok";
//
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }
}
