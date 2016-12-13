package com.zzl.demo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhangzl
 * 描述: activity页面基类
 * 时间: 2016/12/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected AppCompatActivity mActivity;
    /**
     * 布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initUI();

    /**
     * 返回数据处理
     * @param msg
     */
    protected abstract void handleMessages(Message msg);

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessages(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        ActivityCollector.addActivity(this);
        setContentView(getLayoutId());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initUI();
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
