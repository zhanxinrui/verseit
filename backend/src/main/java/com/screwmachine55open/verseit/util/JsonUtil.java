package com.screwmachine55open.verseit.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;
import sun.misc.BASE64Decoder;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 21:25
 * @description: 使用jacksoon包中的 ObjectMapper进行json和java之间的转换
 * @modified By：
 * @version: $version$
 */
public class JsonUtil<T> {

    public static String protobufToJson(String pb) throws Exception {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pbByte=base64Decoder.decodeBuffer(pb);
        Message.Builder paramMessageBuilder = Struct.newBuilder();
        Message paramMessage = paramMessageBuilder.mergeFrom(pbByte).build();
        return JsonFormat.printer().print(paramMessage);
    }



    public static <T> T stringToObject(String str, Class<T> clazz)
            throws Exception {
        // 特殊字符串过滤
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.readValue(str, clazz);
    }

    public static <T> T tokenToObject(String str, Class<T> clazz)
    {
        String json = null;
        T object = null;
        try {
            json = JsonUtil.protobufToJson(str);
            object = JsonUtil.stringToObject(json,clazz);//json -> javaObj
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }






}