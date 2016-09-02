package com.zzl.cn.utils.imagetools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by zzl on 2016/8/18.
 */
public class GlidTopRoundTransform extends BitmapTransformation {
    private static float radius = 0f;

    public GlidTopRoundTransform(Context context) {
        this(context, 4);
    }

    public GlidTopRoundTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//        paint.setAntiAlias(true);
//        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rectF, radius, radius, paint);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        drawLiftUp(canvas, paint);
        drawRightUp(canvas, source.getWidth(), paint);

        Paint paint2 = new Paint();
        paint2.setXfermode(null);

        canvas.drawBitmap(result, 0, 0, paint2);


        return result;
    }

    private static void drawLiftUp(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(0, radius);
        path.lineTo(0, 0);
        path.lineTo(radius, 0);
        path.arcTo(new RectF(0, 0, radius * 2, radius * 2), -90, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private static void drawRightUp(Canvas canvas, int width, Paint paint) {
        Path path = new Path();
        path.moveTo(width, radius);
        path.lineTo(width, 0);
        path.lineTo(width - radius, 0);
        path.arcTo(new RectF(width - radius * 2, 0, width,
                0 + radius * 2), -90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}
