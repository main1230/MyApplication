package com.zzl.cn.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.zzl.cn.R;

/**
 * Created by: zzl.
 * describe:
 * date: 2016/7/1.
 */
public class DialogLoading extends Dialog {

    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        loadingLabel = (TextView) findViewById(R.id.loading_text);
    }

    public void setMsg(String msg) {
        loadingLabel.setText(msg);
    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }

}

