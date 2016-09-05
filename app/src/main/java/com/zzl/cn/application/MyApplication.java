package com.zzl.cn.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zzl.cn.utils.FileUtils;
import com.zzl.cn.utils.StorageUtil;

/**
 * Created by: zzl.
 * describe:
 * date: 2016/6/30.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        mInstance = this;

        initDir();
    }

    // 初始化目录
    public void initDir() {
        if (StorageUtil.isSDExist()) {
            Contents.ROOT_PATH = StorageUtil.getSDRoot() + "/rxjava/";
        } else {
            Contents.ROOT_PATH = getApplicationContext().getFilesDir()
                    .getAbsolutePath() + "/";
        }
        Contents.TEMP_PIC_PATH = Contents.ROOT_PATH + "temp_pic/";
        Contents.TEMP_FILE_PATH = Contents.ROOT_PATH + "temp_file/";

        FileUtils.createDir(Contents.ROOT_PATH);
        FileUtils.createDir(Contents.TEMP_PIC_PATH);
        FileUtils.createDir(Contents.TEMP_FILE_PATH);

    }
}
