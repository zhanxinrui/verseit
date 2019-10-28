package com.screwmachine55open.verseit.serviceImpl;

import com.screwmachine55open.verseit.dao.PoemDao;
import com.screwmachine55open.verseit.dao.UserDao;
import com.screwmachine55open.verseit.entity.Collect;
import com.screwmachine55open.verseit.entity.Compose;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.service.ComposeService;
import com.screwmachine55open.verseit.util.Result;
import com.sun.org.apache.xml.internal.dtm.ref.dom2dtm.DOM2DTMdefaultNamespaceDeclarationNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/6 22:32
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class ComposeServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PoemDao poemDao;
    //    @Autowired
//    MongoOperations mongoOperations;
//    @Autowired
//    private MongoTemplate mongoTemplate;

    /**
     * 获取指定创作文件夹的诗歌
     * */
    public ArrayList<Poem> getComposeByDir(String userName, String ComposeDir) {
        Compose ComposeRes = new Compose();
        try {
            Collection<Compose> Composes = userDao.findByUserName(userName).getComposes();
            for (Compose x : userDao.findByUserName(userName).getComposes()) {
                if (x.getComposeDir().equals(ComposeDir)) {
                    ComposeRes = x;
                    break;
                }
            }
            ArrayList<Poem> queryRes = new ArrayList<>();
            for (String x : ComposeRes.getPoems()) {
                Poem singlePoem = poemDao.findByPoemId(x);
                queryRes.add(singlePoem);
            }

            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取所有创作文件夹的所有诗歌
     * */
    public ArrayList<Poem> getComposeByDefault(String userName) {
        Compose ComposeRes = new Compose();
        ArrayList<Poem> queryRes = new ArrayList<>();
        try {
            Collection<Compose> Composes = userDao.findByUserName(userName).getComposes();
            for (Compose x : userDao.findByUserName(userName).getComposes()) {
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
     * 获取自己指定label的创作
     * @TODO label使用hashset会快一些
     * */
    public ArrayList<Poem> getComposeByLabel(String userName,String label) {
        Compose ComposeRes = new Compose();
        ArrayList<Poem> queryRes = new ArrayList<>();
        try {
            Collection<Compose> Composes = userDao.findByUserName(userName).getComposes();
            for (Compose x : userDao.findByUserName(userName).getComposes()) {
                if (x.getVisible()) {
                    for (String y : x.getPoems()) {
                        Poem singlePoem = poemDao.findByPoemId(y);
                        for (String z : singlePoem.getLabel()) {
                            if (z == label) {
                                queryRes.add(singlePoem);
                                break;
                            }

                        }
                    }
                }
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取他人所有可见创作文件夹的所有诗歌
     *
     * */
    public ArrayList<Poem> getComposeWithVis(String userName) {
        Compose ComposeRes = new Compose();
        ArrayList<Poem> queryRes = new ArrayList<>();
        try {
            Collection<Compose> Composes = userDao.findByUserName(userName).getComposes();
            for (Compose x : userDao.findByUserName(userName).getComposes()) {
                if(x.getVisible()) {
                    for (String y : x.getPoems()) {
                        Poem singlePoem = poemDao.findByPoemId(y);
                        if (singlePoem.getVisible()) {
                            queryRes.add(singlePoem);
                        }
                    }
                }
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 获取他人指定文件夹的可见的创作诗歌
     * */
    public ArrayList<Poem> getComposeInDirWithVis(String userName,String dir) {
        Compose ComposeRes = new Compose();
        ArrayList<Poem> queryRes = new ArrayList<>();
        try {
            Collection<Compose> Composes = userDao.findByUserName(userName).getComposes();
            for (Compose x : userDao.findByUserName(userName).getComposes()) {
                if(x.getVisible() && x.getComposeDir()==dir) {
                    for (String y : x.getPoems()) {
                        Poem singlePoem = poemDao.findByPoemId(y);
                        if (singlePoem.getVisible()) {
                            queryRes.add(singlePoem);
                        }
                    }
                    break;
                }
            }
            return queryRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取他人 可见的创作文件夹
     * */
    public ArrayList<String> getComposeDirWithVis(String userName) {
        try {
            Collection<Compose> queryRes = userDao.findByUserName(userName).getComposes();
            ArrayList<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {
                if (i.getVisible())
                    dirRes.add(i.getComposeDir());
            }

            return dirRes;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取所有创作文件夹
     * */
    public ArrayList<String> getComposeDir(String userName) {
        try {
            Collection<Compose> queryRes = userDao.findByUserName(userName).getComposes();
            ArrayList<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {
                dirRes.add(i.getComposeDir());
            }
            return dirRes;

        } catch (Exception e) {
            throw e;
        }
    }




    /**
     * 设置指定创作文件夹可见
     * */
    public String setComposeVis(String userName, String ComposeDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            List<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {
                i.setVisible(true);
            }
            user.setComposes(queryRes);
            userDao.save(user);
            return "ok";
        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * 设置指定创作文件夹不可见
     * */
    public String setComposeInvis(String userName, String ComposeDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            List<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {
                i.setVisible(false);
            }
            user.setComposes(queryRes);
            userDao.save(user);
            return "ok";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 删除指定创作文件夹
     * @TODO 里面应该调用poemService 诗库中删除文件夹中的诗
     * */

    public String delComposeDir(String userName, String ComposeDir) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            List<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {
                if (i.getComposeDir().equals(ComposeDir))
                    queryRes.remove(i);
            }
            user.setComposes(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 添加创作文件夹
     *
     * */
    public String addComposeDir(String userName, String ComposeDir) throws Exception{
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            List<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {

                if (i.getComposeDir().equals(ComposeDir))
                    throw new Exception("e");
            }
            Compose tmp = new Compose();
            tmp.setComposeDir(ComposeDir);
            queryRes.add(tmp);
            user.setComposes(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 获取他人用户指定类型label的创作诗歌
     * */
    public ArrayList<Poem> getComposeByLabelLimit(String userName,String label){
        User user = userDao.findByUserName(userName);
        ArrayList<Poem> poems  = new ArrayList<>();
        try {
            Collection<Compose> queryRes = user.getComposes();
            boolean tag = false ;
            for (Compose i : queryRes) {
                Collection<String> resPoems = i.getPoems();
                for( String j: resPoems){
                     Poem poem = poemDao.findByPoemId(j);
                    if(poem.getVisible()==true &&  poem.getLabel().contains(label)){//他人查看必须可见
                        poems.add(poemDao.findByPoemId(j));
                    }
                }
            }

            return poems;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 在指定创作文件夹下添加诗歌  不存在就新建这个目录
     * */
    public String addComposeInDir(String userName, String ComposeDir, String poemId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            boolean tag = false ;
            for (Compose i : queryRes) {
                if (i.getComposeDir().equals(ComposeDir)) {
                    Collection<String> resPoems = i.getPoems();
                    resPoems.add(poemId);
                    i.setPoems(resPoems);
                    tag = true;
                    break;
                }
            }
            if(tag==false){//如果不存在,就创建一个新的文件夹
                Compose newCompose = new Compose();
                newCompose.getPoems().add(poemId);
                newCompose.setComposeDir(ComposeDir);
                queryRes.add(newCompose);
            }

            user.setComposes(queryRes);
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }

    /*
    * 创作一首诗歌
    * @TODO  调用poemService 新建一首诗
    * */
    public String ComposeOnePoem(String userName, Poem poem) {
        User user = userDao.findByUserName(userName);
        try{
            String poemId = "xxx";
//            String poemId = poemService.xxxxxxcreate(poem);
            boolean tag = false;
            for(Compose x: user.getComposes()){
                if("Default"==x.getComposeDir()) {
                    tag = true;
                    x.getPoems().add(poemId);
                    return "ok";
                }

            }
            if (tag==false){
                Compose tmp = new Compose();
                tmp.setComposeDir("Default");
                user.getComposes().add(tmp);
                userDao.save(user);
            }
            return "ok";

        }catch (Exception e){
            throw e;
        }

    }



        /**
         * 删除诗歌 在所有文件夹下
         * */
    public String delCompose(String userName, String poemId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            List<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {
                for(String j : i.getPoems()){
                    if(poemId==j)
                        i.getPoems().remove(j);                }
            }
            user.setComposes(queryRes);//可能不需要
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }



    /**
     * 在指定创作文件夹下删除诗歌
     * @fixme
     * */
    public String delComposeInDir(String userName, String ComposeDir, String poemId) {
        User user = userDao.findByUserName(userName);
        try {
            Collection<Compose> queryRes = user.getComposes();
            List<String> dirRes = new ArrayList<>();
            for (Compose i : queryRes) {

                if (i.getComposeDir().equals(ComposeDir)) {
                    i.getPoems().remove(i);//test 引用。。。
                }
            }

            user.setComposes(queryRes);//可能不需要
            userDao.save(user);
            return "ok";

        } catch (Exception e) {
            throw e;
        }

    }
}
