package com.zzl.cn.api;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangzl
 * desc: 网络请求
 * date: 16-9-1.
 */

public class RetrofitManager {
    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;

    public static Retrofit builder() {
        initOkHttpClient();
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(URLs.baseURL)
                            .client(mOkHttpClient)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }

        return mRetrofit;
    }

    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (mOkHttpClient == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
}
