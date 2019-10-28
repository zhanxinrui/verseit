package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.annotation.UserLoginToken;
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.entity.Poet;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.service.PoemService;
import org.springframework.stereotype.Service;
import com.screwmachine55open.verseit.serviceImpl.ConcernServiceImpl;
import com.screwmachine55open.verseit.serviceImpl.PoemServiceImpl;
import com.screwmachine55open.verseit.serviceImpl.TokenService;
import com.screwmachine55open.verseit.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/10/5 15:03
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Api("诗歌模块")
@RestController
@RequestMapping(Constant.BASE_URL + "/poem")
public class PoemController extends BaseController{

    @Autowired
    PoemService poemService;
    @Autowired
    TokenService tokenService;

    /***********************************对广场部分的controller*****************************************************************/

    /**
    * @Description  中间层发过来的带条件查询以json格式。二级菜单对应三个路由，剩下的都可以通过json表示为{sort:List<Integer>,style:List<Integer>,group:List<String>}  对应的Integer为预先定义好的enum下标
    * */

    @Log("查询名人(published)诗歌")
    @RequestMapping(value = "/getPublished", method = RequestMethod.POST,consumes = "application/json")
    public  Result<Page<Poem>> getPulished(@RequestBody HashMap<String,ArrayList> param, @PageableDefault(page = 0,size = 15,sort = {"likeCount"},direction = Sort.Direction.ASC)Pageable pageable){

        try {
            List<Poem> queryRes = poemService.getAllPublishedPoem(param);

            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

    @Log("查询自己关注用户的诗歌(仅用户，诗人的诗歌可以在收藏诗人中找到并点击进入)")
    @UserLoginToken
    @RequestMapping(value = "/getConcerned", method = RequestMethod.POST,consumes = "application/json")
    public  Result<Page<Poem>> getConcernUser(@RequestBody HashMap<String,ArrayList> param, @PageableDefault(page = 0,size = 15,sort = {"likeCount"},direction = Sort.Direction.ASC)Pageable pageable){
        try {
            List<Poem> queryRes = poemService.getAllConcernedPoem(param,tokenService.getUserId(request.getHeader("Authorization")));

            Page<Poem> pageList = new PageImpl<Poem>(queryRes, pageable, queryRes.size());
            return Result.ok(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(null, "error maybe the poem doesn't exist anymore");
        }
    }

}
