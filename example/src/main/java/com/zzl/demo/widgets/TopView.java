package com.zzl.demo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.zzl.demo.R;

/**
 * Created by zhangzl
 * 描述: 标题头
 * 时间: 2016/12/13.
 */

public class TopView extends LinearLayout {
    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_layout, this);
    }
}
