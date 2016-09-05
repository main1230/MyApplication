package com.zzl.cn.api;

import com.zzl.cn.bean.ResultBean;
import com.zzl.cn.exception.APIException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zzl
 * desc:
 * date: 2016/9/4.
 */
public class RxHelper {
    /**
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> Observable<T> flatResponse(final ResultBean<T> response) {
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
    public static <T> Observable.Transformer<ResultBean<T>, T> handleResult() {
        return new Observable.Transformer<ResultBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<ResultBean<T>> resultBeanObservable) {
                return resultBeanObservable.flatMap(new Func1<ResultBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResultBean<T> tResultBean) {
                        if (tResultBean.getCode() == 200) {
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
    private static <T> Observable<T> createData(final T data) {
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
