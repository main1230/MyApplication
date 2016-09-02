package com.zzl.cn.api;

import com.zzl.cn.bean.ResultBean;
import com.zzl.cn.bean.UserBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhangzl
 * desc:
 * date: 16-9-1.
 */

public interface UserApiService {

    /**
     * 登陆
     * @param phone     手机号
     * @param password   密码
     * @return
     */
    @FormUrlEncoded
    @POST("/api/login/")
    Observable<ResultBean<UserBean>> login(@Field("phone") String phone, @Field("password") String password);

    /**
     * 注册
     * @param data
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<ResultBean<UserBean>> register(@FieldMap Map<String, String> data);
}
