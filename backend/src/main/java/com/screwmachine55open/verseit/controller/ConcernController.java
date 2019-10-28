package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.entity.Concern;
import com.screwmachine55open.verseit.entity.Poet;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.serviceImpl.ConcernServiceImpl;
import com.screwmachine55open.verseit.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/6 22:14
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Api("关注模块")
@RestController
@RequestMapping(Constant.BASE_URL + "/concern")
public class ConcernController extends BaseController{

    @Autowired
    ConcernServiceImpl concernService;

    /***********************************对个人关注部分的controller*****************************************************************/


    @Log("获取自己指定关注文件夹的诗人")
    @RequestMapping(value = "/getConcern/{concernDir}", method = RequestMethod.GET)
    public Result<Page<Poet>> getConcernByDir(@PathVariable(value="concernDir")String concernDir,//name value都是等价的(springboot 4+)
//                                                        @PathParam(value="Page" )Integer page,
                                                 @PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC) Pageable pageable) {
        try {
            ArrayList<Poet> queryRes = concernService.getConcernPoetsByDir(getUserName(),concernDir);
            Page<Poet> pageList = new PageImpl<Poet>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("获取自己所有关注文件夹的所有诗人")
    @RequestMapping(value = "/getConcernByDefault", method = RequestMethod.GET)
    public Result<Page<Poet>> getConcernByDefault(@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<Poet> queryRes = concernService.getConcernPoetsByDefault(getUserName());
            Page<Poet> pageList = new PageImpl<Poet>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }



    @Log("获取自己所有关注文件夹")
    @RequestMapping(value = "/getConcernDir", method = RequestMethod.GET)
    public Result<Page<String>> getConcernDir(@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
        try {
            ArrayList<String> dirRes = concernService.getConcernDir(getUserName());
            Page<String> pageList = new PageImpl<String>(dirRes, pageable, dirRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, " Maybe the poemDir doesn't exist");
        }
    }

    // @Log("获取他人的关注文件夹(可见)")
    // @RequestMapping(value = "/getVisConcernDir/{userName}", method = RequestMethod.GET)
    // public Result<Page<String>> getVisConcernDir(String userName,@PageableDefault(page = 0, size = 15,sort = {"liekCount"},direction= Sort.Direction.ASC)Pageable pageable) {
    //     try {
    //         ArrayList<String> dirRes = concernService.getVisConcernDir(userName);
    //         Page<String> pageList = new PageImpl<String>(dirRes, pageable, dirRes.size());
    //         return Result.ok(pageList);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return Result.error(null, " Maybe the poemDir doesn't exist");
    //     }
    // }


    //用户设置和具体关注页面设置都可以使用



    @Log("删除指定关注文件夹")
    @RequestMapping(value = "/delConcernDir/{concernDir}", method = RequestMethod.GET)
    public Result<String> delConcernDir(@PathVariable(value="concernDir")String concernDir){
        try {
            String res = concernService.delConcernDir(getUserName(),concernDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the concernDir doesn't exist");
        }
    }

    @Log("添加关注文件夹")
    @RequestMapping(value = "/addConcernDir/{concernDir}", method = RequestMethod.GET)
    public Result<String> addConcernDir(@PathVariable(value="concernDir")String concernDir){
        try {
            String res = concernService.addConcernDir(getUserName(),concernDir);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the concernDir doesn't exist");
        }
    }


    @Log("放一位诗人在指定文件夹下 (关注诗人)")
    @RequestMapping(value = "/addConcernInDir/{concernDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> addConcernInDir(
            @PathVariable(value="concernDir")String concernDir,
            @PathVariable(value="poemId")String poemId){

        try {
            String res = concernService.addConcernPoetsInDir(getUserName(),concernDir,poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the concernDir doesn't exist");
        }
    }



    @Log("在指定关注文件夹下删除诗人")
    @RequestMapping(value = "/delConcernInDir/{concernDir}/{poemId}", method = RequestMethod.GET)
    public Result<String> delConcernInDir(
            @PathVariable(value="concernDir")String concernDir,
            @PathVariable(value="poemId")String poemId){
        try {
            String res = concernService.addConcernPoetsInDir(getUserName(),concernDir,poemId);
            return Result.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "May the concernDir doesn't exist");
        }
    }


    @Log("删除一位关注的诗人通过id")
    @RequestMapping(value = "/delConcern/{poemId}", method = RequestMethod.GET)
    public Result<String> delConcern(
            @PathVariable(value="poemId")String poemId){
        try {
            String res = concernService.delConcern(getUserName(),poemId);
            return Result.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "Error happened");
        }
    }

}
