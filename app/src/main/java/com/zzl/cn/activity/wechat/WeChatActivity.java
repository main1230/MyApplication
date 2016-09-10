package com.zzl.cn.activity.wechat;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.zzl.cn.R;
import com.zzl.cn.activity.base.BaseActivity;
import com.zzl.cn.api.DownloadAPI;
import com.zzl.cn.api.download.DownloadProgressListener;
import com.zzl.cn.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzl
 * desc:
 * date: 2016/9/10.
 */
public class WeChatActivity extends BaseActivity {
    private ListView mListView;

    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();

    private AudioRecorderButton mAudioRecorderButton;

    private View animView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mListView = (ListView) findViewById(R.id.id_listview);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);

        mAudioRecorderButton.setAudioFinishRecorderListener(audioFinishRecorderListener);

        mAdapter = new RecoderAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

        //listView的item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                // 播放动画（帧动画）
                if (animView != null) {
                    animView.setBackgroundResource(R.drawable.adj);
                    animView = null;
                }
                animView = view.findViewById(R.id.id_recoder_anim);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) animView.getBackground();
                animation.start();
                // 播放录音
                MediaManager.playSound(mDatas.get(position).filePath,new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    private AudioRecorderButton.AudioFinishRecorderListener audioFinishRecorderListener = new AudioRecorderButton.AudioFinishRecorderListener(){
        @Override
        public void onFinish(float seconds, String filePath) {
            Recorder recorder = new Recorder(seconds, filePath);
            mDatas.add(recorder);
            mAdapter.notifyDataSetChanged(); //通知更新的内容
            mListView.setSelection(mDatas.size() - 1); //将lisview设置为最后一个
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
        if (mAudioRecorderButton != null) {
            mAudioRecorderButton.clear();
        }
    }

    private void uploadFile() {

    }

    DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
        @Override
        public void update(final long bytesRead, final long contentLength, boolean done) {
            LogUtils.showErrorLog(getClass(), (bytesRead * 100 / contentLength) + "%");
        }
    };
}
