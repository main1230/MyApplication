package com.zzl.cn.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zzl.cn.R;
import com.zzl.cn.api.ApiService;
import com.zzl.cn.api.RxHelper;
import com.zzl.cn.api.URLs;
import com.zzl.cn.api.UserApiService;
import com.zzl.cn.bean.ResultBean;
import com.zzl.cn.bean.UserBean;
import com.zzl.cn.api.RetrofitManager;
import com.zzl.cn.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

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
        ApiService<UserBean> userApiService = RetrofitManager.builder().create(ApiService.class);
        Subscription subscription = userApiService
                .post(URLs.login, map)
                .compose(RxHelper.handleResult(UserBean.class))
                .subscribe(new Action1<UserBean>() {
                    @Override
                    public void call(UserBean userBeanResult) {
                        hideLoadingDialog();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        hideLoadingDialog();
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
