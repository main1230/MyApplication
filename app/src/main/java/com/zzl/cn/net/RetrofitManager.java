package com.zzl.cn.net;

import com.zzl.cn.bean.ResultBean;
import com.zzl.cn.exception.APIException;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangzl
 * desc: 网络请求
 * date: 16-9-1.
 */

public class RetrofitManager {
    private static final String BASE_URL = "http://192.168.0.188:8000/";
    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;

    public static Retrofit builder() {
        initOkHttpClient();
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
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

    /**
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponse(final ResultBean<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    if (response.getCode() == 1) {
                        subscriber.onNext(response.getData());
                    } else {
                        subscriber.onError(new APIException(response.getCode(), response.getMsg()));
                    }

                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 对网络返回结果进行处理
     * @param <T>
     * @return
     */
    public <T> Observable.Transformer<ResultBean<T>, T> handleResult() {
        return new Observable.Transformer<ResultBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<ResultBean<T>> resultBeanObservable) {
                return resultBeanObservable.flatMap(new Func1<ResultBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResultBean<T> tResultBean) {
                        if (tResultBean.getCode() == 1) {
                            return createData(tResultBean.getData());
                        }
                        return Observable.error(new APIException(tResultBean.getCode(), tResultBean.getMsg()));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     * @param data
     * @param <T>
     * @return
     */
    private <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
