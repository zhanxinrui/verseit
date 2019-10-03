package com.screwmachine55open.verseit.serviceImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qiniu.util.Etag;
import com.screwmachine55open.verseit.dao.PoemDao;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.Collect;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.util.MailUtil;
import com.screwmachine55open.verseit.util.Result;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.data.mongodb.core.query.Criteria;
import javax.persistence.criteria.Subquery;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/5 23:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class CollectServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    PoemDao poemDao;
    //    @Autowired
//    MongoOperations mongoOperations;
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 获取指定收藏文件夹的诗歌
     * */
    public ArrayList<Poem> getCollectBycollectDir(String userName, String collectDir) {
        Collect collectRes = new Collect();
        try {
            Collection<Collect> collects = userDao.findByUserName(userName).getCollects();
            for (Collect x : userDao.findByUserName(userName).getCollects()) {
                if (x.getCollectDir().equals(collectDir)) {
                    collectRes = x;
                    break;
                }
            }
            ArrayList<Poem> queryRes = new ArrayList<>();
            for (String x : collectRes.getPoems()) {
                Poem singlePoem = poemDao.findByPoemId(x);
                queryRes.add(singlePoem);
            }

            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取所有收藏文件夹的所有诗歌
     * */
    public ArrayList<Poem> getCollectByDefault(String userName) {
        Collect collectRes = new Collect();
        ArrayList<Poem> queryRes = new ArrayList<>();
        try {
            Collection<Collect> collects = userDao.findByUserName(userName).getCollects();
            for (Collect x : userDao.findByUserName(userName).getCollects()) {
                for (String y : x.getPoems()) {
                    Poem singlePoem = poemDao.findByPoemId(y);
                    queryRes.add(singlePoem);
                }
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取所有可见收藏文件夹的所有诗歌
     * */
    public ArrayList<Poem> getCollectByVis(String userName) {
        Collect collectRes = new Collect();
        ArrayList<Poem> queryRes = new ArrayList<>();
        try {
            Collection<Collect> collects = userDao.findByUserName(userName).getCollects();
            for (Collect x : userDao.findByUserName(userName).getCollects()) {
                if(x.getVisible()) {
                    for (String y : x.getPoems()) {
                        Poem singlePoem = poemDao.findByPoemId(y);
                        queryRes.add(singlePoem);
                    }
                }
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取指定的他人收藏文件夹的诗
     *
     * */
    public ArrayList<Poem> getCollectInDirWithVis(String userName,String collectDir) {
        Collect collectRes = new Collect();
        try {
            Collection<Collect> collects = userDao.findByUserName(userName).getCollects();
            for (Collect x : userDao.findByUserName(userName).getCollects()) {
                if (x.getCollectDir().equals(collectDir) && x.getVisible()==true) {
                    collectRes = x;
                    break;
                }
            }
            ArrayList<Poem> queryRes = new ArrayList<>();
            for (String x : collectRes.getPoems()) {
                Poem singlePoem = poemDao.findByPoemId(x);
                queryRes.add(singlePoem);
            }

            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * 获取所有收藏文件夹
     * */
    public ArrayList<String> getCollectDir(String userName) {
        try {
            Collection<Collect> queryRes = userDao.findByUserName(userName).getCollects();
            ArrayList<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {
                dirRes.add(i.getCollectDir());
            }
            return dirRes;

        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 获取可见的收藏文件夹
     * */
    public ArrayList<String> getVisCollectDir(String userName) {
        try {
            Collection<Collect> queryRes = userDao.findByUserName(userName).getCollects();
            ArrayList<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {
                if (i.getVisible())
                    dirRes.add(i.getCollectDir());
            }

            return dirRes;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 设置指定收藏文件夹可见
     * */
    public String setCollectVis(String userName, String collectDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            List<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {
                i.setVisible(true);
            }
            user.setCollects(queryRes);
            userDao.save(user);
            return "ok";
        } catch (Exception e) {
            throw e;
        }


    }
    /**
     * 设置指定收藏文件夹不可见
     * */
    public String setCollectInvis(String userName, String collectDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            List<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {
                i.setVisible(false);
            }
            user.setCollects(queryRes);
            userDao.save(user);
            return "ok";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 删除指定收藏文件夹
     * */

    public String delCollectDir(String userName, String collectDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            List<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {
                if (i.getCollectDir().equals(collectDir))
                    queryRes.remove(i);

            }
            user.setCollects(queryRes);

            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 添加收藏文件夹
     * */
    public String addCollectDir(String userName, String collectDir) throws Exception{
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            List<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {

                if (i.getCollectDir().equals(collectDir))
                    throw new Exception("e");
            }
            Collect tmp = new Collect();
            tmp.setCollectDir(collectDir);
            queryRes.add(tmp);
            user.setCollects(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 在指定收藏文件夹下添加诗歌
     * */
    public String addCollectInDir(String userName, String collectDir, String poemId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            boolean tag = false ;
            for (Collect i : queryRes) {
                if (i.getCollectDir().equals(collectDir)) {
                    Collection<String> restPoems = i.getPoems();
                    restPoems.add(poemId);
                    i.setPoems(restPoems);
                    tag = true;
                    break;
                }
            }
            if(tag==false){//如果不存在,就创建一个新的文件夹
                Collect newCollect = new Collect();
                newCollect.getPoems().add(poemId);
                newCollect.setCollectDir(collectDir);
                queryRes.add(newCollect);
            }

            user.setCollects(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * 删除诗歌 在所有文件夹下
     * */
    public String delCollect(String userName, String poemId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            List<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {
                for(String j : i.getPoems()){
                    if(poemId==j)
                        i.getPoems().remove(j);                }
            }
            user.setCollects(queryRes);//可能不需要
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }



    /**
     * 在指定收藏文件夹下删除诗歌
     * @fixme
     * */
    public String delCollectInDir(String userName, String collectDir, String poemId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Collect> queryRes = user.getCollects();
            List<String> dirRes = new ArrayList<>();
            for (Collect i : queryRes) {

                if (i.getCollectDir().equals(collectDir)) {
                    i.getPoems().remove(i);//test 引用。。。
                }
            }

            user.setCollects(queryRes);//可能不需要
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }
}
