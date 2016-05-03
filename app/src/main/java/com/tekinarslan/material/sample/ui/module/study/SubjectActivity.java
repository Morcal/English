package com.tekinarslan.material.sample.ui.module.study;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.camnter.easycountdowntextureview.EasyCountDownTextureView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.ui.module.community.Player;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/1.
 */
public class SubjectActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SubjectActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tv_write)
    TextView tvWrite;
    @Bind(R.id.tv_listen)
    TextView tvListen;
    @Bind(R.id.tv_read)
    TextView tvRead;
    @Bind(R.id.tv_trans)
    TextView tvTrans;
    @Bind(R.id.crp_write)
    ColorfulRingProgressView crpWrite;
    @Bind(R.id.crp_listen)
    ColorfulRingProgressView crpListen;
    @Bind(R.id.crp_read)
    ColorfulRingProgressView crpRead;
    @Bind(R.id.crp_trans)
    ColorfulRingProgressView crpTrans;
    @Bind(R.id.linear_write)
    LinearLayout write;
    @Bind(R.id.linear_listen)
    LinearLayout listen;
    @Bind(R.id.linear_read)
    LinearLayout read;
    @Bind(R.id.linear_trans)
    LinearLayout translate;

    private float writePrecent = 45f;
    private float listenPrecent = 78f;
    private float readPrecent = 24f;
    private float translatePrecent = 68f;
    private float progress = 0f;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    updateProgress(crpWrite, tvWrite, writePrecent);
                    updateProgress(crpListen, tvListen, listenPrecent);
                    updateProgress(crpRead, tvRead, readPrecent);
                    updateProgress(crpTrans, tvTrans, translatePrecent);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        write.setOnClickListener(this);
        listen.setOnClickListener(this);
        read.setOnClickListener(this);
        translate.setOnClickListener(this);

        Timer timer = new Timer();
        timer.schedule(new UpDateTask(), 1, 80);
    }

    private void initData() {

    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (this != null) {
                    finish();
                }
            }
        });
    }

    private void updateProgress(ColorfulRingProgressView crpView, TextView textView, float precent) {
        if (progress > 100 || progress > precent) {
            return;
        } else {
            crpView.setPercent(progress);
            textView.setText((int) progress + "%");
        }
        progress++;
        Log.i(TAG, "progress->" + progress);
    }

    private class UpDateTask extends TimerTask {

        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_write:
                Log.i(TAG, "Write");
                Intent write = new Intent(SubjectActivity.this, WriteListActivity.class);
                startActivity(write);
                break;
            case R.id.linear_listen:
                Log.i(TAG, "Listener");
                break;
            case R.id.linear_read:
                Log.i(TAG, "Read");
                break;
            case R.id.linear_trans:
                Log.i(TAG, "Translation");
                Intent translate = new Intent(SubjectActivity.this, TranslateActivity.class);
                startActivity(translate);
                break;
        }
    }
}
