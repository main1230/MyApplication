package com.zzl.cn.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by: zzl.
 * describe:
 * date: 2016/7/3.
 */
public class SIMCardUtil {
    /**
     * 通过系统接口获取手机号码，不一定能够获取
     * 需要权限 <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     * @param context
     * @return
     */
    public static String getNativePhoneNumber(Context context){
        String phone = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            phone = tm.getLine1Number();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return phone;
    }

    /**
     * 获取手机SIM卡IMSI号
     * 需要加入权限<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     * @param context
     * @return
     */
    public static String getImsi(Context context){
        String imsi = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imsi = tm.getSubscriberId();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return imsi;
    }

    /**
     * 获取SIM序列号
     * 需要加入权限<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     * @param context
     * @return
     */
    public static String getSimSerialNumber(Context context){
        String simSerialNum = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            simSerialNum = tm.getSimSerialNumber();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return simSerialNum;
    }

    /**
     * 获取设备id（IMEI）
     * 需要加入权限<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     * @param context
     * @return
     */
    public static String getDeviceId(Context context){
        String deviceId = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return deviceId;
    }
}
