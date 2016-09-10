package com.zzl.cn.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zzl.cn.R;
import com.zzl.cn.activity.wechat.WeChatActivity;
import com.zzl.cn.api.RxHelper;
import com.zzl.cn.api.UserApiService;
import com.zzl.cn.bean.UserBean;
import com.zzl.cn.api.RetrofitManager;
import com.zzl.cn.activity.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

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

    private UserApiService userApiService;

    @OnClick(R.id.register_btn)
    void register() {
        jumpToPage(WeChatActivity.class);
        //jumpToPage(RegisterActivity.class);
    }

    @OnClick(R.id.login_btn)
    void login() {
        String phone = phoneEt.getText().toString();
        String password = pwdEt.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            showToastMsg("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToastMsg("请输入密码");
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);

        showLoadingDialog();
        userApiService = RetrofitManager.builder().create(UserApiService.class);
        Subscription subscription = userApiService.login(map)
                .compose(RxHelper.<UserBean>handleResult())
                .subscribe(new Action1<UserBean>() {
                               @Override
                               public void call(UserBean userBean) {
                                   hideLoadingDialog();
                                   showToastMsg("---"+userBean.getName());
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                dealException(throwable);
                            }
                        });

        mCompositeSubscription.add(subscription);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }
}
