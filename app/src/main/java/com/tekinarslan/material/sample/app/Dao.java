package com.tekinarslan.material.sample.app;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by lyqdhgo on 2016/1/25.
 */
public class Dao {

    public static void getEntity(String url, final EntityListener listener) {
        Log.i("url", url);
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

    public interface EntityListener {
        void onError();

        void onSuccess(String result);
    }
}
