package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.PlayPauseButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/3/18.
 */
public class PlayAudioActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.playpause)
    PlayPauseButton pauseButton;
    @Bind(R.id.playpausewrapper)
    View playWraper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playaudio);
        ButterKnife.bind(this);
        iniData();
        initView();
        initEvent();
    }

    private void iniData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("AUDIO");
        ViewUtils.showToastShort(this, "ID:" + id);
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);

        pauseButton.setColor(Color.WHITE);
        pauseButton.startAnimation();

    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        playWraper.setOnClickListener(mPlayPauseExpandedListener);
    }

    private final View.OnClickListener mPlayPauseExpandedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pauseButton.isPlayed()) {
                pauseButton.setPlayed(true);
                pauseButton.startAnimation();
            } else {
                pauseButton.setPlayed(false);
                pauseButton.startAnimation();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 200);

        }
    };
}
