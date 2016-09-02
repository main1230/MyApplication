package com.zzl.cn.utils;

import android.util.Log;

/**
 * Created by: zzl.
 * describe:
 * date: 2016/7/3.
 */
public class LogUtils {

    public static void showErrorLog(Class<?> cls, String log) {
        Log.e(cls.getName(), log);
    }

    public static void showLog(String log) {
        System.out.println(log);
    }
}
