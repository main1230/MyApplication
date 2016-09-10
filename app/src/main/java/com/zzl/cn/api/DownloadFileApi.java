package com.zzl.cn.api;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by zzl
 * desc: 文件下载
 * date: 2016/9/10.
 */
public interface DownloadFileApi {

    /**
     * 文件下载
     * @param url
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String url);

    /**
     * 文件上传
     * @param url
     * @param data    RequestBody实例中包裹的字符串值
     * @param params
     * @return
     */
    @Multipart
    @POST
    Observable<String> uploadFile(@Url String url,
                                  @Part("data") RequestBody data,
                                  @PartMap Map<String,RequestBody> params);
}
