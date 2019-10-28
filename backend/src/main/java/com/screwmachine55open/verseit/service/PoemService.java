package com.screwmachine55open.verseit.service;

import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.util.Result;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 18:29
 * @description：Poem的接口  请在serviceImpl完成实现  其中Page是需要多项的数据结构，使用Result应该是可能出现失败的查询
 * @modified By：
 * @version: $version$
 */

public interface PoemService {
    /**
    * 广场部分
    * */
    //二级菜单  type: pubPoem userPoem concennPoem
    List<Poem>getAllPublishedPoem(HashMap<String,ArrayList>cond);
    List<Poem>getAllPersonalPoem();
    List<Poem>getAllConcernedPoem(HashMap<String,ArrayList>param, String userId);


//    //三级菜单  Sort
//    Query  getDefalutPoem();
//    Query  getHotPoem();
//    Query  getNewPoem();
//
//    //四级菜单  style
//    Query getTagPoem(Query query,List<Integer> tagList);
//
//    //五级菜单  Group
//    Query getConcernPem(Query query,List<Integer> concernList);
//





    /**
     *单个诗歌的操作
     * */
//
//    Poem savePoem(Poem poem);
//
//    Result<Poem>delPoem(Poem poem);
//
//
//    Poem getPoemById(String id);
//
//    /**
//     * @return 多页的poem?不太懂page的结构
//     * */
//    Page<Poem> getDefaultPoem();
//
//    Result<Page<Poem>> getPoemByPoet(String Poet);
//
//    Result<Page<Poem>> getPoemByName(String name);

//    ArrayList<Poem>

}
