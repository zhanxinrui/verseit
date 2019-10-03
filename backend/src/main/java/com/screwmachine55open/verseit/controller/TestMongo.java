//package com.screwmachine55open.verseit.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Enumeration;
//
///**
// * @author ：xrzhan
// * @date ：Created in 2019/4/29 15:20
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//@Controller
//public class TestMongo {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Autowired
//    private CustomerRepository repository;
//
//
//    @RequestMapping("/probe/wp")
//    @ResponseBody
//    public Object wpPhp(HttpServletRequest request, HttpServletResponse response){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        System.out.println(sdf.format(new Date())+"-->prob post begin.......");
//        try {
//            Enumeration<String> enums = request.getParameterNames();
//            while (enums.hasMoreElements()) {
//                String name = enums.nextElement();
//                System.out.println("data:"+name+"="+request.getParameter(name));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Customer cus = repository.findByDevcMac("FCE998A1E7EF");
//        System.out.println(cus.toString());
//
//        Customer cus1=mongoTemplate.findOne(new Query(Criteria.where("devcMac").is("FCE998A1E7EF")), Customer.class);
//        System.out.println(cus1);
//        System.out.println(sdf.format(new Date())+"-->wphp post end.......");
//
//        return "OK";
//    }
//}