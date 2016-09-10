package com.zzl.cn.activity.wechat;

/**
 * Created by zzl
 * desc:
 * date: 2016/9/10.
 */
public class Recorder {
    float time;
    String filePath;

    public Recorder(float time, String filePath) {
        super();
        this.time = time;
        this.filePath = filePath;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
