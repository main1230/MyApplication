package com.zzl.top;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangzl
 * desc:
 * date: 2016/10/13.
 */

public class LibApplication extends Application {
    static Context mContext;

    public LibApplication() {
        mContext = this;
    }

    public static void initialize(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
