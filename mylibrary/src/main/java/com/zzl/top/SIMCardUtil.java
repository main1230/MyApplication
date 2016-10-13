package com.zzl.top;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzl
 * desc: sim工具类
 * date: 2016/10/13.
 */

public class SIMCardUtil {

    /**
     * 获取IMSI号
     *
     * @return
     */
    public static String getImsi() {
        return getImsi(AppManager.getAppManager().currentActivity());
    }


    public static String getImsi(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String _imsi = tm.getSubscriberId();
            if (_imsi != null && !_imsi.equals("")) {
                return _imsi;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not_known";
    }

    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    public static boolean isSDExist() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * 获取SD卡的根目录，末尾带\
     *
     * @return
     */
    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 计算SD卡的剩余空间
     *
     * @return 返回-1，说明没有安装sd卡
     */
    public static long getFreeDiskSpace() {
        long freeSpace = 0;
        if (isSDExist()) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize / 1024;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return freeSpace;
    }

    /**
     * 获取程序版本号及版本名称
     *
     * @param packageName
     * @return
     */
    public static Object[] getAppVersionCode(String packageName) {
        Object[] info = new Object[2];
        try {
            PackageInfo packageInfo = AppManager.getAppManager().currentActivity()
                    .getPackageManager()
                    .getPackageInfo(packageName, 0);
            info[0] = packageInfo.versionCode;
            info[1] = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return info;
    }

    /**
     * 获取设备信息 如手机型号、系统版本号、手机品牌、设备ID
     *
     * @return
     */
    public static String[] getPhoneInfo() {
        String[] data = new String[3];
        data[0] = Build.MODEL;
        data[1] = Build.VERSION.RELEASE;
        data[2] = Build.MANUFACTURER;
        return data;
    }

    /**
     * 查询手机内非系统应用
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getNoSystemApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        //获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            //判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {

                apps.add(pak);
            }
        }
        return apps;
    }

    /**
     * 设置listview高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
