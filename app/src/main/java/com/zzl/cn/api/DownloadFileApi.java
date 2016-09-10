package com.zzl.cn.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by zzl
 * desc: 文件下载
 * date: 2016/9/10.
 */
public interface DownloadFileApi {

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String url);
}
