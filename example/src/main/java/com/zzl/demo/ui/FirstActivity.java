package com.zzl.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zzl.demo.R;
import com.zzl.demo.ui.activity.NotificationActivity;

/**
 * Created by zhangzl
 * 描述: 首个页面
 * 时间: 2016/12/13.
 */

public class FirstActivity extends BaseActivity {
    private ListView lv;
    private String[] data = {
            "RecylerView",
            "fileUpload",
            "notification"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.act_first;
    }

    @Override
    protected void handleMessages(Message msg) {

    }

    @Override
    protected void initUI() {
        lv = (ListView) findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,
                android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 2:
                        intent = new Intent(mActivity, NotificationActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
