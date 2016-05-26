package com.tekinarslan.material.sample.app;

import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.bean.User;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by lyqdhgo on 2016/1/25.
 */
public class Dao {

    public static void getEntity(String url, final EntityListener listener) {
        Log.i("url->", url);
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i("Error", e.toString());
                listener.onError();
            }

            @Override
            public void onResponse(String response) {
                Log.i("Success", response);
                listener.onSuccess(response);
            }
        });
    }

    // 提交String
    public static void postString(String url, String content) {
        OkHttpUtils.postString()
                .url(url)
                .content(content)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Logger.i("提交数据失败 PHP");
                    }

                    @Override
                    public void onResponse(String response) {
                        Logger.i("提交数据成功 PHP" + "Response->" + response);
                    }
                });
    }

    public static void postJson(String url, User user) {
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new User()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });
    }

    public interface EntityListener {
        void onError();

        void onSuccess(String result);
    }
}
