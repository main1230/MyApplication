package com.zzl.demo.ui;

import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;
import com.zzl.demo.application.MyApplication;

/**
 * Created by zhangzl
 * 描述:
 * 时间: 2016/12/13.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
