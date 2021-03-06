package com.zzl.top;

import android.content.Context;

/**
 * Created by zhangzl
 * desc: 保存sharepreferens数据
 * date: 2016/10/13.
 */

public class PreferencesManager {
    private static PreferencesManager instance_ = new PreferencesManager(LibApplication.getContext());
    private Context context_;

    private static final String PREFERENCE_NAME = "myapp";

    public PreferencesManager(Context context) {
        this.context_ = context;
    }

    public static PreferencesManager getInstance(){

        return instance_;
    }

    public boolean putInt(String key, int value){
        boolean flag = context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
        return flag;
    }

    public int getInt(String key, int defValue){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getInt(key, defValue);
    }

    public boolean putLong(String key, long value){
        boolean flag = context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putLong(key, value).commit();
        return flag;
    }

    public long getLong(String key, long defValue){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getLong(key, defValue);
    }

    public boolean putString(String key, String value){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public String getString(String key, String defValue){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(key, defValue);
    }

    public boolean putBoolean(String key, boolean value){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defValue){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getBoolean(key, defValue);
    }

    public boolean clearAllValue(){
        return context_.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
    }
}
