package com.screwmachine55open.verseit.util;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>Title: $file.className</p>
 *
 * @author zhanxirnui
 * @date 2019年05月10日
 */
@Setter
@Getter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class testReference {

    String a;
    Collection<String> b;
    public static void main(String[] args) {
        testReference t = new testReference();
        t.b = new ArrayList<String>();
        t.a = "aaaa";
        t.b.add("aa");
        t.b.add("b");
        System.out.println("t:"+t);
        Collection<String> collection =  t.b;
        collection.add("a");
        System.out.println("t:"+t);

    }
}
