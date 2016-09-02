package com.zzl.cn.bean;

import java.io.Serializable;

/**
 * Created by zhangzl
 * desc: 数据返回结果
 * date: 16-9-1.
 */

public class ResultBean<T> implements Serializable {
    /**
     * 1成功   2失败
     */
    private int code = 2;
    private String msg = "操作失败";
    private T data;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
