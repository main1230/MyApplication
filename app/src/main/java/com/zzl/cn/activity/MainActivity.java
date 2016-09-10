package com.zzl.cn.activity;

import android.os.Bundle;
import android.widget.Button;

import com.zzl.cn.R;
import com.zzl.cn.activity.base.BaseActivity;
import com.zzl.cn.api.DownloadAPI;
import com.zzl.cn.api.download.DownloadProgressListener;
import com.zzl.cn.application.Contents;
import com.zzl.cn.utils.LogUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;

public class MainActivity extends BaseActivity {

    @BindView(R.id.update_btn)
    Button updateBtn;

    @OnClick(R.id.update_btn)
    void update() {
        updateBtn.setEnabled(false);
        File outFile = new File(Contents.ROOT_PATH + "android-support-v4.jar");
        Subscription subscription = new DownloadAPI("http://192.168.1.188:8000/", downloadProgressListener)
                .downloadFile("http://192.168.1.188:8000/static/android-support-v4.jar", outFile, new Subscriber() {
                    @Override
                    public void onCompleted() {
                        updateBtn.setEnabled(true);
                        updateBtn.setText("下载完成---onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        updateBtn.setText("下载完成---onError---");
                    }

                    @Override
                    public void onNext(Object o) {
                        updateBtn.setText("下载完成---onNext---");
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
        @Override
        public void update(final long bytesRead, final long contentLength, boolean done) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateBtn.setText((bytesRead * 100 / contentLength) + "%");
                }
            });
            LogUtils.showErrorLog(getClass(), (bytesRead * 100 / contentLength) + "%");
        }
    };
}
