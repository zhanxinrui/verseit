package com.screwmachine55open.verseit.service;

import com.screwmachine55open.verseit.annotation.Log;
import com.screwmachine55open.verseit.entity.Concern;
import com.screwmachine55open.verseit.entity.User;
import com.screwmachine55open.verseit.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/5 23:00
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ConcernService {

    /**
     * 获取自己所有的关注
     * */

    ArrayList<Concern> getConcernByDefault(String userName);


    /**
     * 获取指定的文件夹个人关注
     * */
    ArrayList<Concern> getConcernByDir(String userName,String ConcernDir);


    /**
     * 获取所有关注文件夹
     * */
    ArrayList<Concern> getConcernDir(String userName);


    /**
     * 删除指定关注文件夹
     *
     **/
    String delConcernDir(String userName, String ConcernDir) ;

    /**
     * 添加关注文件夹
     * */
    String addConcernDir(String userName, String ConcernDir) ;

    /**
     * 放一位诗人在指定文件夹下 (关注诗人)
     * */
    String addConcernInDir(String userName, String ConcernDir, String poetId) ;


    /**
     * 删除一位关注的诗人通过id
     * */
    String delConcern(String userName,  String poetI);

    /**
     * 在指定关注文件夹下删除诗人
     * */
    String delConcernIndir(String userName, String ConcernDir, String poet) ;

}
