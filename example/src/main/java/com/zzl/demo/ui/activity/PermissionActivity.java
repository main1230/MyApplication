package com.zzl.demo.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.zzl.demo.R;
import com.zzl.demo.ui.BaseActivity;

import butterknife.OnClick;

/**
 * Created by zhangzl
 * 描述: 权限测试
 * 时间: 16-12-20.
 */

public class PermissionActivity extends BaseActivity {
    @OnClick(R.id.stroge_btn)
    void stroge() {
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mActivity, "已授权sdcard写的权限", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, "sdcard写的权限被拒", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1000);
        }
    }

    @OnClick(R.id.lianxiren_btn)
    void lianxiren() {
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mActivity, "已授权读取联系人权限", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, "读取联系人权限被拒", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_permission;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void handleMessages(Message msg) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000:
                if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mActivity, "已授权sdcard写的权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mActivity, "sdcard写的权限被拒", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
