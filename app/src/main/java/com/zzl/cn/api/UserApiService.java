package com.zzl.cn.api;

import com.zzl.cn.bean.ResultBean;
import com.zzl.cn.bean.UserBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhangzl
 * desc:
 * date: 16-9-1.
 */

public interface UserApiService {
    /**
     * 登录
     * @param map
     * @return
     */
    @POST(URLs.login)
    Observable<ResultBean<UserBean>> login(@Body Map<String, Object> map);

    /**
     * 注册
     * @param map
     * @return
     */
    @POST(URLs.register)
    Observable<ResultBean<UserBean>> register(@Body Map<String, Object> map);


    /**
     * 登陆
     * @param phone     手机号
     * @param password   密码
     * @return
     */
//    @FormUrlEncoded
//    @POST("api/login/")
//    Observable<ResultBean<UserBean>> login(@Field("phone") String phone, @Field("password") String password);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("api/login/")
//    Observable<ResultBean<UserBean>> login(@Body RequestBody requestBody);

//    @POST("{url}")
//    Observable<ResultBean<UserBean>> login(@Path("url") String url, @QueryMap Map<String, Object> map);


    /**
     * 注册
     * @param data
     * @return
     */
//    @FormUrlEncoded
//    @POST("api/register/")
//    Observable<ResultBean<UserBean>> register(@FieldMap Map<String, String> data);
//
//    @POST("api/register/")
//    Observable<ResultBean<UserBean>> register(@Body RequestBody requestBody);
}
