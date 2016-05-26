package com.tekinarslan.material.sample.utills;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    // MD5加密 32位
    public static String strToMd5(String string) {
        //创建MD5对象
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] cipherData = md5.digest(string.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte cipher : cipherData) {
                // 确保生成32位字符串
                String toHexStr = Integer.toHexString(cipher & 0xff);
                builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
