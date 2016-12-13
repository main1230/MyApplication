package com.zzl.demo.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.zzl.demo.R;
import com.zzl.demo.ui.BaseActivity;

/**
 * Created by zhangzl
 * 描述: 通知
 * 时间: 2016/12/13.
 */

public class NotificationActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_notification;
    }

    NotificationManager notificationManager;

    @Override
    protected void initUI() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    protected void handleMessages(Message msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                showNormaNotification();
                break;
        }
    }

    private void showNormaNotification() {
        Notification notification = new NotificationCompat.Builder(mActivity)
                .setContentTitle("标题头")
                .setContentText("内容内容7878788888")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        notificationManager.notify(1, notification);
    }
}
