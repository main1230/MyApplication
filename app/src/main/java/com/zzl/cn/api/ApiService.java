package com.zzl.cn.api;
import com.zzl.cn.bean.ResultBean;
import com.zzl.cn.bean.UserBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zzl
 * desc:
 * date: 2016/9/4.
 */
public interface ApiService {
    /**
     * post请求
     * @param url
     * @param map
     * @return
     */
    @POST("{url}")
    Observable<ResultBean> post(@Path("url") String url,
                                          @QueryMap Map<String, Object> map);

    /**
     * get请求
     * @param url
     * @param map
     * @return
     */
    @GET
    Observable<ResultBean> get(@Path("url") String url,
                                   @QueryMap Map<String, Object> map);
}
