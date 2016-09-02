package com.zzl.cn.exception;

/**
 * Created by Administrator
 * desc: 自定义异常
 * date: 2016/8/26 0026.
 */
public class APIException extends Exception {
    public int code;
    public String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
