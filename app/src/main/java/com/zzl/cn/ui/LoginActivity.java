package com.zzl.cn.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zzl.cn.R;
import com.zzl.cn.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by zhangzl
 * desc: 登录
 * date: 16-9-1.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.result_tv)
    TextView resultTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }
}
