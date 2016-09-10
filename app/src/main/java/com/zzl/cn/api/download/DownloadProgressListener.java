package com.zzl.cn.api.download;

/**
 * Created by zzl
 * desc: 下载进度
 * date: 2016/9/10.
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
