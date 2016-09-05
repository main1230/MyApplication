package com.zzl.cn.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;
import com.zzl.cn.R;
import com.zzl.cn.application.AppManager;
import com.zzl.cn.exception.APIException;
import com.zzl.cn.utils.NetUtil;
import com.zzl.cn.widget.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhangzl
 * desc:
 * date: 16-9-1.
 */

public abstract class BaseActivity extends FragmentActivity {
    private DialogLoading loadingDialog;

    protected BaseActivity mActivity;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;
    protected Toast mToast;
    private Unbinder mUnbinder;

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No Titlebar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppManager.getAppManager().addActivity(this);
        mActivity = this;
        mCompositeSubscription = new CompositeSubscription();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        afterCreate(savedInstanceState);
    }

    public void showToastMsg(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new DialogLoading(this);
            loadingDialog.setMsg(msg);
        }
        loadingDialog.show();
    }

    public void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new DialogLoading(this);
        }
        loadingDialog.show();
    }

    public void showLoadingDialog(boolean isCancel) {
        if (loadingDialog == null) {
            loadingDialog = new DialogLoading(this);
        }
        loadingDialog.setCanceledOnTouchOutside(isCancel);
        loadingDialog.show();
    }

    public void showLoadingDialog(String msg, boolean isCancel) {
        if (loadingDialog == null) {
            loadingDialog = new DialogLoading(this);
            loadingDialog.setMsg(msg);
        }
        loadingDialog.setCanceledOnTouchOutside(isCancel);
        loadingDialog.show();
    }

    public void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.removeActivity(this);
        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
        mCompositeSubscription.unsubscribe();
        mUnbinder.unbind();
        super.onDestroy();
    }

    /**
     * 网络请求失败处理
     * @param e
     */
    public void dealException(Throwable e) {
        hideLoadingDialog();
        if (!NetUtil.isNetworkConnected()) {
            showToastMsg(getString(R.string.net_not_open) + e.getMessage());
        } else {
            if (e instanceof APIException) {
                showToastMsg(e.getMessage());
                connectNetErrorCode((APIException) e);
            } else {
                showToastMsg(getString(R.string.net_connect_faile) + "(" + e.toString() + ")");
            }
        }
        /*if (e instanceof SocketTimeoutException) {
            showToastMsg(getString(R.string.net_connect_faile) + e.getMessage());
        } else if (e instanceof ConnectException) {
            showToastMsg(getString(R.string.net_not_open) + e.getMessage());
        } else {
            showToastMsg(e.getMessage());
        }*/
    }

    protected void connectNetErrorCode(APIException e) {

    }

    /**
     * 跳转到其他界面
     * @param cls 跳转页面
     * @param bundle Bundle参数
     * @param isReturn 是否返回
     * @param requestCode 请求Code
     * @param isFinish 是否销毁当前页面
     */
    protected void jumpToPage(Class<?> cls, Bundle bundle, boolean isReturn,
                              int requestCode, boolean isFinish) {
        if (cls == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (isReturn) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        if (isFinish) {
            finish();
        }
    }

    /**
     * 跳转到其他界面
     *
     * @param cls 跳转页面
     * @param bundle Bundle参数
     * @param isFinish 是否销毁当前页面
     */
    protected void jumpToPage(Class<?> cls, Bundle bundle, boolean isFinish) {
        jumpToPage(cls, bundle, false, 0, isFinish);
    }

    /**
     * 跳转到其他界面，不销毁当前页面，也不传参数
     *
     * @param cls
     *            跳转页面
     */
    protected void jumpToPage(Class<?> cls) {
        jumpToPage(cls, null, false, 0, false);
    }
}
