package com.screwmachine55open.verseit.service;

import com.screwmachine55open.verseit.entity.Poem;
import com.screwmachine55open.verseit.util.Result;
import org.springframework.data.domain.Page;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 18:29
 * @description：Poem的接口  请在serviceImpl完成实现  其中Page是需要多项的数据结构，使用Result应该是可能出现失败的查询
 * @modified By：
 * @version: $version$
 */
public interface PoemService {

    /**
     * 前台做相应修改，后台得到的只是最后需要保存的结果。
     * */
    Poem savePoem(Poem poem);

    Result<Poem>delPoem(Poem poem);


    Poem getPoemById(String id);

    /**
     * @return 多页的poem?不太懂page的结构
     * */
    Page<Poem> getRandPoem();

    Result<Page<Poem>> getPoemByPoet(String Poet);

    Result<Page<Poem>> getPoemByName(String name);



}
