package com.zzl.demo.ui;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzl
 * 描述: activity管理类
 * 时间: 2016/12/13.
 */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();

    /**
     * 添加activitiy
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除activitiy
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 关闭并移除所有的activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
