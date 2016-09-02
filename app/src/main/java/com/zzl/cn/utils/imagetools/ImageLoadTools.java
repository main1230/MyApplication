package com.zzl.cn.utils.imagetools;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.zzl.cn.R;

/**
 * Created by: zzl.
 * describe: 图片加载
 * date: 2016/7/2.
 */
public class ImageLoadTools {

    /**
     * 加载图片
     * @param context
     * @param url
     * @param imageView
     * @param isUseDefaultImg
     */
    public static void loadImg(Context context, String url, ImageView imageView, boolean isUseDefaultImg) {
        if (TextUtils.isEmpty(url)) return;
        if (isUseDefaultImg) {
            Glide.with(context)
                    .load(url)
                    .fitCenter()
                    .crossFade()
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .fitCenter()
                    .crossFade()
                    .into(imageView);
        }
    }

    public static void loadImg(Context context, String url, ImageView imageView, int loadingImg) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(loadingImg)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载圆角
     * @param context
     * @param url
     * @param imageView
     * @param loadingImg
     */
    public static void loadImgRound(Context context, String url, ImageView imageView, int loadingImg) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(loadingImg)
                .crossFade(500)
//                .transform(new GlidTopRoundTransform(context, 15))
//                .transform(new GlideRoundTransform(context, 15))
                .into(imageView);
    }

    /**
     * 加载圆形
     * @param context
     * @param url
     * @param imageView
     * @param loadingImg
     */
    public static void loadImgCircle(Context context, String url, ImageView imageView, int loadingImg) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(loadingImg)
                .crossFade(500)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载圆角
     * @param context
     * @param rId
     * @param imageView
     * @param loadingImg
     */
    public static void loadImgRound(Context context, int rId, ImageView imageView, int loadingImg) {
        Glide.with(context)
                .load(rId)
                .fitCenter()
                .placeholder(loadingImg)
                .crossFade(500)
                .transform(new GlideRoundTransform(context, 15))
                .into(imageView);
    }

    /**
     * 加载圆形
     * @param context
     * @param rId
     * @param imageView
     * @param loadingImg
     */
    public static void loadImgCircle(Context context, int rId, ImageView imageView, int loadingImg) {
        Glide.with(context)
                .load(rId)
                .fitCenter()
                .placeholder(loadingImg)
                .crossFade(500)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }
}
