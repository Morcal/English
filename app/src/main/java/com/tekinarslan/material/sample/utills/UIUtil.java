package com.tekinarslan.material.sample.utills;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tekinarslan.material.sample.R;


public class UIUtil {
    static int DEFAULT_AVATAR = R.drawable.default_photo_cube;
    static int LOADING_IMAGE_ID = R.drawable.default_photo_wight;
    static int FAIL_IMAGE_ID = R.drawable.default_photo_wight;

    private UIUtil() {
    }

    public static void setAvatar(String url, ImageView img) {
        setImage(url, img, DEFAULT_AVATAR, DEFAULT_AVATAR);
    }

    public static void setAvatar(String url, ImageView img, int width, int height) {
        setImage(url, img, width, height, DEFAULT_AVATAR, DEFAULT_AVATAR);
    }

    public static void setImage(String url, ImageView img) {
        setImage(url, img, LOADING_IMAGE_ID, FAIL_IMAGE_ID);
    }

    public static void setImage(String url, ImageView img, int loading, int fail) {
//        setImageImageLoader(url, img, loading, fail);
        setImagePicasso(url, img, 0, 0, loading, fail);
    }

    public static void setImage(String url, ImageView img, int width, int height, int loading, int fail) {
        setImagePicasso(url, img, width, height, loading, fail);
    }


    private static void setImagePicasso(String url, ImageView img, int loading, int fail) {
        setImagePicasso(url, img, 0, 0, loading, fail);
    }

    private static void setImagePicasso(String url, ImageView img, int width, int height, int loading, int fail) {
        if (url == null) {
            img.setImageResource(fail);
            return;
        }
        if (width != 0 && height != 0) {
            Picasso.with(img.getContext()).load(url).placeholder(loading).error(fail).resize(width, height).into(img);
        } else {
            Picasso.with(img.getContext()).load(url).placeholder(loading).error(fail).into(img);
        }
    }

    @SuppressLint("NewApi")
    private static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static int dp2Px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
                .getDisplayMetrics());
    }

    public static int px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static String addSpace(String classifyName) {
        if (classifyName.length() > 2) {
            return classifyName;
        }
        return classifyName.substring(0, 1) + " " + classifyName.substring(1);
    }

    private static final float SCALE = 16f / 9;

    private static Bitmap resize(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 扩大View的触摸范围
     *
     * @param parent
     * @param btn    要扩大触摸范围的View
     * @param width
     * @param height
     */
    public static void enlargeViewTouchScope(View parent, View btn, float width, float height) {
        Rect rect = new Rect();
        rect.top = btn.getTop();
        rect.bottom = btn.getBottom();
        rect.left = btn.getLeft();
        rect.right = btn.getRight();

        rect.top -= height;
        rect.bottom += height;
        rect.left -= width;
        rect.right += width;

        btn.setTouchDelegate(new TouchDelegate(rect, btn));
    }

}
