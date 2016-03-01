package com.tekinarslan.material.sample.utills;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by lyqdhgo on 2016/1/25.
 */
public class ViewUtils {
    // ProgressDialog
    public static ProgressDialog dialog;

    public static void showDialog(Context context, String msg) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(msg);
        dialog.show();
    }

    public static void hideDialog() {
        dialog.dismiss();
    }

    // Toast
    public static void showToastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
