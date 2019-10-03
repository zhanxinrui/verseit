package com.screwmachine55open.verseit.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ：xrzhan
 * @date ：Created in 2019/4/8 22:08
 * @description： 得到一个data message code组成的对象
 * @modified By：
 * @version: $version$
 */

@Getter
@Setter
public class Result <T> implements  Serializable {


    public enum Code {
        OK(200),
        ERROR(-1),
        ILLEGAL_PARAMETER(100);

        private int code;

        Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    private T data;
    private String message;
    private int code;

    public Result(T data, String message, Code code) {

        this.setData(data);
        this.setMessage(message);
        this.setCode(code.getCode());

    }

    public Result(String message, Code code) {
        this.setMessage(message);
        this.setCode(code.getCode());

    }


    public static <T> Result<T> ok(T data) {

        return new Result(data, "ok", Code.OK);

    }

    public static <T> Result<T> error(T data, String errorMsg) {

        return new Result(data, errorMsg, Code.ERROR);

    }

    // 其他的可预见场景，如无权限等等
    public static <T> Result<T> okNull() {
        return new Result<>("ok", Code.OK);
    }


}