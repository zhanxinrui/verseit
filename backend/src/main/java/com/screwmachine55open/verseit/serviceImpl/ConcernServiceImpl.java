package com.screwmachine55open.verseit.serviceImpl;

import com.screwmachine55open.verseit.dao.PoetDao;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.Concern;
import com.screwmachine55open.verseit.entity.Poet;
import com.screwmachine55open.verseit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/5 23:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */


@Service
public class ConcernServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PoetDao poetDao;


    /**
     * 获取指定文件夹个人关注
     * */
    public ArrayList<Poet> getConcernByDir(String userName, String ConcernDir) {
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
            for (String x : ConcernRes.getPersons()) {
                Poet singlePoet = poetDao.findByPoetId(x);
                queryRes.add(singlePoet);
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 获取所有关注文件夹的所有诗人
     * */
    public ArrayList<Poet> getConcernByDefault(String userName) {
        Concern ConcernRes = new Concern();
        ArrayList<Poet> queryRes = new ArrayList<>();
        try {
            Collection<Concern> Concerns = userDao.findByUserName(userName).getConcerns();
            for (Concern x : userDao.findByUserName(userName).getConcerns()) {
                for (String y : x.getPersons()) {
                    Poet singlePoet = poetDao.findByPoetId(y);
                    queryRes.add(singlePoet);
                }
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * 获取所有关注文件夹
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
    public String addConcernInDir(String userName, String ConcernDir, String PoetId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            boolean tag = false ;
            for (Concern i : queryRes) {
                if (i.getConcernDir().equals(ConcernDir)) {
                    Collection<String> resPoets = i.getPersons();
                    resPoets.add(PoetId);
                    i.setPersons(resPoets);
                    tag = true;
                    break;
                }
            }
            if(tag==false){//如果不存在,就创建一个新的文件夹
                Concern newConcern = new Concern();
                newConcern.getPersons().add(PoetId);
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
     * */
    public String delConcern(String userName, String PoetId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            List<String> dirRes = new ArrayList<>();
            for (Concern i : queryRes) {
                for(String j : i.getPersons()){
                    if(PoetId==j)
                        i.getPersons().remove(j);                }
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
     * */
    public String delConcernInDir(String userName, String ConcernDir, String PoetId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Concern> queryRes = user.getConcerns();
            List<String> dirRes = new ArrayList<>();
            for (Concern i : queryRes) {
                if (i.getConcernDir().equals(ConcernDir)) {
                    for(String j : i.getPersons()){
                        if(PoetId==j)
                            i.getPersons().remove(j);
                    }
                }
            }

            user.setConcerns(queryRes);//可能不需要
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }
}
