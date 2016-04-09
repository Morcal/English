package com.tekinarslan.material.sample.utills;

/**
 * Created by lyqdhgo on 2016/4/9.
 */
public class Util {
    public static boolean checkSdCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
}
