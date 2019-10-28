package com.screwmachine55open.verseit.controller;


import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.entity.Compose;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.serviceImpl.ComposeServiceImpl;
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
@Api("创作模块")
@RestController
@RequestMapping(Constant.BASE_URL + "compose")
public class ComposeController extends BaseController {
    @Autowired
    ComposeServiceImpl composeService;
    /***********************************对个人创作部分的controller*****************************************************************/


    @Log("获取自己指定创作文件夹的诗歌")
    @RequestMapping(value = "/getCompose/{composeDir}", method = RequestMethod.GET)
    public Result<Page<Poem>> getComposeByDir(@PathVariable(value="composeDir")String composeDir,//name value都是等价的(springboot 4+)
//                                                        @PathParam(value="Page" )Integer page,
                                              @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = composeService.getComposeByDir(getUserName(),composeDir);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取自己所有创作文件夹的所有诗歌")
    @RequestMapping(value = "/getComposeByDefault", method = RequestMethod.GET)
    public Result<Page<Poem>> getComposeByDefault(@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = composeService.getComposeByDefault(getUserName());
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }


    @Log("获取自己所有创作文件夹")
    @RequestMapping(value = "/getComposeDir", method = RequestMethod.GET)
    public Result<Page<String>> getComposeDir(@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<String> dirRes = composeService.getComposeDir(getUserName());
            Page<String> pageList = new PageImpl<String>(dirRes, pageable, dirRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, " Maybe the poemDir doesn't exist");
        }
    }




    @Log("获取用户指定类型label的创作诗歌")
    @RequestMapping(value = "/getCompose/{label}", method = RequestMethod.GET)
    public Result<Page<Poem>> getComposeByLabel(String label , @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = composeService.getComposeByLabel(getUserName(),label);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }
    @Log("获取用户指定类型label的创作诗歌")
    @RequestMapping(value = "/getCompose/{userName}/{label}", method = RequestMethod.GET)
    public Result<Page<Poem>> getComposeByLabelLimit(String userName, String label , @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = composeService.getComposeByLabelLimit(userName,label);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }




    @Log("获取他人所有可见创作文件夹的所有诗歌")
    @RequestMapping(value = "/getComposeWithVis", method = RequestMethod.GET)
    public Result<Page<Poem>> getComposeWithVis(String userName,@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = composeService.getComposeWithVis(userName);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取指定的他人创作文件夹的诗歌(可见)")
    @RequestMapping(value = "/getComposeInDirWithVis/{userName}/{composeDir}", method = RequestMethod.GET)
    public Result<Page<Poem>> getComposeInDirWithVis(@PathVariable(value = "userName")String userName,
                                                     @PathVariable(value = "composeDir")String composeDir,
                                                     @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poem> queryRes = composeService.getComposeInDirWithVis(userName,composeDir);
            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }


    @Log("获取他人的创作文件夹(可见)")
    @RequestMapping(value = "/getComposeDirWithVis/{userName}", method = RequestMethod.GET)
    public Result<Page<String>> getComposeDirWithVis(String userName,@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<String> dirRes = composeService.getComposeDirWithVis(userName);
            Page<String> pageList = new PageImpl<String>(dirRes, pageable, dirRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, " Maybe the poemDir doesn't exist");
        }
    }


    //用户设置和具体创作页面设置都可以使用

    @Log("设置指定创作文件夹可见")
    @RequestMapping(value = "/setComposeVis/{composeDir}", method = RequestMethod.GET)
    public Result<String> setComposeVis(@PathVariable(value="composeDir")String composeDir){
        try {
            String res = composeService.setComposeVis(getUserName(),composeDir);
            return Result.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");

        }
    }

    @Log("设置指定创作文件夹不可见")
    @RequestMapping(value = "/setComposeInvis/{composeDir}", method = RequestMethod.GET)
    public Result<String> setComposeInvis(@PathVariable(value="composeDir")String composeDir){
        try {
            String res = composeService.setComposeInvis(getUserName(),composeDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");

        }
    }


    @Log("删除指定创作文件夹")
    @RequestMapping(value = "/delComposeDir/{composeDir}", method = RequestMethod.GET)
    public Result<String> delComposeDir(@PathVariable(value="composeDir")String composeDir){
        try {
            String res = composeService.delComposeDir(getUserName(),composeDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");
        }
    }

    @Log("添加创作文件夹")
    @RequestMapping(value = "/addComposeDir/{composeDir}", method = RequestMethod.GET)
    public Result<String> addComposeDir(@PathVariable(value="composeDir")String composeDir){
        try {
            String res = composeService.addComposeDir(getUserName(),composeDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");
        }
    }


    @Log("在指定创作文件夹下添加诗歌 可以是不存在的文件夹,比如默认default文件夹")
    @RequestMapping(value = "/addComposeInDir/{composeDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> addComposeInDir(
            @PathVariable(value="composeDir")String composeDir,
            @PathVariable(value="poemId")String poemId){

        try {
            String res = composeService.addComposeInDir(getUserName(),composeDir,poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");
        }
    }


    @Log("在所有文件夹下删除诗歌")
    @RequestMapping(value = "/delCompose/{poemId}", method = RequestMethod.GET)
    public Result<String> delCompose(
            @PathVariable(value="poemId")String poemId){
        try {
            String res = composeService.delCompose(getUserName(),poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");
        }
    }

    @Log("在指定创作文件夹下删除诗歌")
    @RequestMapping(value = "/delCompose/{composeDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> delComposeInDir(@PathVariable(value="composeDir")String composeDir,
            @PathVariable(value="poemId")String poemId){
        try {
            String res = composeService.delComposeInDir(getUserName(),composeDir,poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");
        }
    }


    /*
     * 创作一首诗歌
     * @TODO  里面应该调用poemService 新建一首诗
     * */
    @Log("创作一首诗歌")
    @RequestMapping(value = "/ComposeOne/", method = RequestMethod.POST)
    public Result<String> ComposeOnePoem(
            @RequestBody Poem poem){
        try {
            String res = composeService.ComposeOnePoem(getUserName(),poem);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the composeDir doesn't exist");
        }
    }





}










