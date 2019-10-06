package com.screwmachine55open.verseit.service;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.entity.Collect;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.util.Result;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/5 22:59
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface CollectService {

    /******************************个人的收藏 Service 接口*****************************************************/
    /**
     * 获取指定收藏文件夹的诗歌
     * */
    ArrayList<Poem> getCollectBycollectDir(String userName, String collectDir) ;
    /**
     * 获取所有收藏文件夹的所有诗歌
     * */
    ArrayList<Poem> getCollectByDefault(String userName) ;
    /**
     * 获取所有可见收藏文件夹的所有诗歌
     * */
    ArrayList<Poem> getCollectByVis(String userName) ;

    /**
     * 获取所有收藏文件夹
     * */
    ArrayList<String> getCollectDir(String userName) ;
    /**
     * 获取可见的收藏文件夹
     * */
    ArrayList<String> getVisCollectDir(String userName) ;

    /**
     * 获取指定的他人收藏文件夹的诗
     *
     * */
    ArrayList<Poem> getCollectInDirWithVis(String userName,String collectDir) ;


    /**
     * 设置指定收藏文件夹可见
     * */
    String setCollectVis(String userName, String collectDir) ;
    /**
     * 设置指定收藏文件夹不可见
     * */
    String setCollectInvis(String userName, String collectDir) ;

    /**
     * 删除指定收藏文件夹
     * */
    String delCollectDir(String userName, String collectDir) ;
    /**
     * 添加收藏文件夹
     * */
    String addCollectDir(String userName, String collectDir)throws Exception ;
    /**
     * 在指定收藏文件夹下添加诗歌
     * */
    String addCollectInDir(String userName, String collectDir, String poemId) ;
    /**
     * 删除一首收藏的诗歌通过id
     * */
    String delCollect(String userName,  String poemId) ;
    /**
     * 在指定收藏文件夹下删除诗歌
     * */
    String delCollectIndir(String userName, String collectDir, String poemId) ;





}