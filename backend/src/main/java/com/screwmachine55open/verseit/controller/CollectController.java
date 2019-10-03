package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.entity.Collect;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.serviceImpl.CollectServiceImpl;
import com.screwmachine55open.verseit.util.Result;
import io.swagger.annotations.Api;
import org.omg.CORBA.portable.IndirectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.meta.TypeQualifierNickname;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/6 18:50
 * @description：${description}
 * @modified By：
 * @version: $version$
 * @ FIXME: 2019/5/10  这部分暂时都是全查询  查询完再筛选
 */
@Api("收藏模块")
@RestController
@RequestMapping(Constant.BASE_URL + "/collect")
public class CollectController extends BaseController {

    @Autowired
    CollectServiceImpl collectService;







    /***********************************对个人收藏部分的controller*****************************************************************/


    @Log("获取自己指定收藏文件夹的诗歌")
    @RequestMapping(value = "/getCollect/{collectDir}", method = RequestMethod.GET)
    public Result<Page<Poem>> getCollectBycollectDir(@PathVariable(value="collectDir")String collectDir,//name value都是等价的(springboot 4+)
//                                                        @PathParam(value="Page" )Integer page,
                                                     @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = collectService.getCollectBycollectDir(getUserName(),collectDir);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取自己所有收藏文件夹的所有诗歌")
    @RequestMapping(value = "/getCollectByDefault", method = RequestMethod.GET)
    public Result<Page<Poem>> getCollectByDefault(@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = collectService.getCollectByDefault(getUserName());
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取他人所有可见收藏文件夹的所有诗歌")
    @RequestMapping(value = "/getCollectByVis", method = RequestMethod.GET)
    public Result<Page<Poem>> getCollectByVis(String userName,@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = collectService.getCollectByVis(userName);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取指定的他人收藏文件夹的诗歌(可见)")
    @RequestMapping(value = "/getCollectInDirWithVis/{userName}/{collectDir}", method = RequestMethod.GET)
    public Result<Page<Poem>> getCollectInDirWithVis(@PathVariable(value = "userName")String userName,
                                              @PathVariable(value = "collectDir")String collectDir,
                                              @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = collectService.getCollectInDirWithVis(userName,collectDir);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取自己所有收藏文件夹")
    @RequestMapping(value = "/getCollectDir", method = RequestMethod.GET)
    public Result<Page<String>> getCollectDir(@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<String> dirRes = collectService.getCollectDir(getUserName());
            Page<String> pageList = new PageImpl<String>(dirRes, pageable, dirRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, " Maybe the poemDir doesn't exist");
        }
    }

    @Log("获取他人的收藏文件夹(可见)")
    @RequestMapping(value = "/getVisCollectDir/{userName}", method = RequestMethod.GET)
    public Result<Page<String>> getVisCollectDir(String userName,@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<String> dirRes = collectService.getVisCollectDir(userName);
            Page<String> pageList = new PageImpl<String>(dirRes, pageable, dirRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, " Maybe the poemDir doesn't exist");
        }
    }


    //用户设置和具体收藏页面设置都可以使用

    @Log("设置指定收藏文件夹可见")
    @RequestMapping(value = "/setCollectVis/{collectDir}", method = RequestMethod.GET)
    public Result<String> setCollectVis(@PathVariable(value="collectDir")String collectDir){
        try {
            String res = collectService.setCollectVis(getUserName(),collectDir);
            return Result.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");

        }
    }

    @Log("设置指定收藏文件夹不可见")
    @RequestMapping(value = "/setCollectInvis/{collectDir}", method = RequestMethod.GET)
    public Result<String> setCollectInvis(@PathVariable(value="collectDir")String collectDir){
        try {
            String res = collectService.setCollectInvis(getUserName(),collectDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");

        }
    }


    @Log("删除指定收藏文件夹")
    @RequestMapping(value = "/delCollectDir/{collectDir}", method = RequestMethod.GET)
    public Result<String> delCollectDir(@PathVariable(value="collectDir")String collectDir){
        try {
            String res = collectService.delCollectDir(getUserName(),collectDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");
        }
    }

    @Log("添加收藏文件夹")
    @RequestMapping(value = "/addCollectDir/{collectDir}", method = RequestMethod.GET)
    public Result<String> addCollectDir(@PathVariable(value="collectDir")String collectDir){
        try {
            String res = collectService.addCollectDir(getUserName(),collectDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");
        }
    }


    @Log("在指定收藏文件夹下添加诗歌 可以是不存在的文件夹,比如默认default文件夹")
    @RequestMapping(value = "/addCollectInDir/{collectDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> addCollectInDir(
            @PathVariable(value="collectDir")String collectDir,
            @PathVariable(value="poemId")String poemId){

        try {
            String res = collectService.addCollectInDir(getUserName(),collectDir,poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");
        }
    }



    @Log("在指定收藏文件夹下删除诗歌")
    @RequestMapping(value = "/delCollectInDir/{collectDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> delCollectInDir(
            @PathVariable(value="collectDir")String collectDir,
            @PathVariable(value="poemId")String poemId){
        try {
            String res = collectService.addCollectInDir(getUserName(),collectDir,poemId);
            return Result.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");
        }
    }

    @Log("在所有文件夹下删除诗歌")
    @RequestMapping(value = "/delCollect/{collectDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> delCollect(
            @PathVariable(value="collectDir")String collectDir,
            @PathVariable(value="poemId")String poemId){
        try {
            String res = collectService.delCollect(getUserName(),poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the collectDir doesn't exist");
        }
    }





}
