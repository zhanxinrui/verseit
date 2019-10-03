package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.serviceImpl.UserServiceImpl;
import com.screwmachine55open.verseit.util.PageableTools;
import com.screwmachine55open.verseit.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.screwmachine55open.verseit.util.EntryUtil;

/**
 * <p>Title: $file.className</p>
 *
 * @author zhanxirnui
 * @date 2019年05月07日
 */

@RestController
@RequestMapping(Constant.BASE_URL )
public class TestDSController {
    @Autowired
    UserServiceImpl userService;

    @Log("arraylist")
    @RequestMapping(value = "/tstds/arraylist", method = RequestMethod.GET)
    public Result<List<User>> getAllUser(){
//        Pageable pageable = PageableTools.basicPage(0,EntryUtil.instance(String).getSort());
        ArrayList<String> arrayList  = new ArrayList<>();
        for(int i = 0; i <30 ; i ++)
            arrayList.add("jffjjf");


        return userService.getAllUser();

    }

    @Log("page")
    @RequestMapping(value = "/tstds/page", method = RequestMethod.GET)
    public Result<Page<User>> getAllUserUsingPage(){
        return userService.getAllUserPage();

    }




//
//    public Result<Page<Blog>> getBlogByTag(Blog blog) {
//        Pageable pageable = PageableTools.basicPage(0,EntryUtil.instance(blog).getSort());
//        if(Objects.equal(BLOG_TAG_ALL, blog.getTag())){
//            return Result.ok(blogDao.findAll(pageable));
//        }
//
//        return  Result.ok(blogDao.findBlogByTag(blog.getTag(),pageable));
//    }
}
