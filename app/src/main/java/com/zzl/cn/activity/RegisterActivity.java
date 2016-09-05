package com.zzl.cn.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import com.zzl.cn.R;
import com.zzl.cn.activity.base.BaseActivity;
import com.zzl.cn.api.RetrofitManager;
import com.zzl.cn.api.RxHelper;
import com.zzl.cn.api.UserApiService;
import com.zzl.cn.bean.UserBean;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by zzl
 * desc:
 * date: 2016/9/5.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @OnClick(R.id.register_btn)
    void registerUser() {
        register();
    }

    private UserApiService userApiService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        userApiService = RetrofitManager.builder().create(UserApiService.class);
    }

    private void register() {
        String phone = phoneEt.getText().toString();
        String username = nameEt.getText().toString();
        String password = pwdEt.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToastMsg("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(username)) {
            showToastMsg("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToastMsg("请输入密码");
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("username", username);
        map.put("password", password);

        showLoadingDialog();
        Subscription subscription = userApiService.register(map)
                .compose(RxHelper.<UserBean>handleResult())
                .subscribe(new Action1<UserBean>() {
                               @Override
                               public void call(UserBean userBean) {
                                   hideLoadingDialog();
                                   showToastMsg("注册成功：" + userBean.getName());
                                   finish();
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
}
