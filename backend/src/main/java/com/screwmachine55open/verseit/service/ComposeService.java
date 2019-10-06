package com.screwmachine55open.verseit.service;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.entity.Compose;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/7 16:11
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ComposeService {


     /**
      * 获取个人的所有创作
      * */

     ArrayList<Compose> getComposeByDefault(String userName);


     /**
      * 获取指定的文件夹个人创作
      * */
     ArrayList<Compose> getComposeByDir(String userName,String composeDir);


     /**
      * 获取所有创作文件夹
      * */
     ArrayList<Compose> getComposeDir(String userName);

     /**
      * 获取用户指定类型label的创作诗歌
      * */
     ArrayList<Poem> getComposeByLabel(String userName,String label);

     /**
      * 获取他人用户指定类型label的创作诗歌
      * */
    ArrayList<Poem> getComposeByLabelLimit(String userName,String label);


     /**
      * 获取他人所有可见的诗歌的文件夹
      * */

     ArrayList<String> getComposeDirWithVis(String userName) ;


     /**
      * 获取他人所有可见的文件夹的所有诗歌
      * */

     ArrayList<Poem> getComposeWithVis(String userName) ;


     /**
      * 获取指定文件夹的所有可见的诗歌   因为实际上他人查看首先显示可见文件夹，所以不必判断该文件夹是否可见
      * */
     ArrayList<Poem> getComposeByDirWithVis(String userName, String composeDir);



    /**
     * 设置指定创作文件夹可见
     * */
    String setComposeVis(String userName, String ComposeDir) ;
    /**
     * 设置指定创作文件夹不可见   创作涉及设置诗歌不可见，此功能在poem中涉及
     * */
    String setComposeInvis(String userName, String ComposeDir) ;

    /**
     * 删除指定创作文件夹
     * @TODO 里面应该调用poemService 诗库中删除文件夹中的诗
     **/
    String delComposeDir(String userName, String ComposeDir) ;
    /**
     * 添加创作文件夹
     * */
    String addComposeDir(String userName, String ComposeDir) ;
    /**
     * 在指定创作文件夹下添加诗歌
     * */
    String addComposeInDir(String userName, String ComposeDir, String poemId) ;

    /*
     * 创作一首诗歌
     * @TODO  里面应该调用poemService 新建一首诗
     * */
    public String ComposeOnePoem(String userName, Poem poem);
    /**
     * 删除一首创作的诗歌通过id
     *@TODO 里面应该调用poemService 诗库中删除一首诗
     * */
    String delCompose(String userName,  String poemId);

    /**
     * 在指定创作文件夹下删除诗歌
     * @TODO 里面应该调用poemService 诗库中删除一首诗
     * */
    String delComposeInDir(String userName, String ComposeDir, String poemId) ;




}
