package com.screwmachine55open.verseit.util;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/10 16:28
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Setter
@Getter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TestInitial {

    String a;
    ArrayList<String> b;
    public static void main(String[] args) {
        testReference t = new testReference();
        t.b = new ArrayList<String>();
//        t.a = "aaaa";
//        t.b.add("aa");
//        t.b.add("b");
//        System.out.println("t:"+t);
//        Collection<String> collection =  t.b;
//        collection.add("a");
//        System.out.println("t:"+t);

    }
}
