package com.tekinarslan.material.sample.ui.module.study;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.SpokenEntity;
import com.tekinarslan.material.sample.bean.Write;
import com.tekinarslan.material.sample.ui.adapter.SpokenPagerAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.ProgressBarCircular;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class CourseSpokenActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback, TabFragment.OnHeadlineSelectedListener {

    private static final String TAG = CourseSpokenActivity.class.getSimpleName();
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String DEFAULT_VIDEO_URL = "http://7xrfxa.com1.z0.glb.clouddn.com/sayenglish01.mp4";

    private String title;
    private String audioUrl;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.video_layout)
    View videoLayout;
    @Bind(R.id.videoView)
    UniversalVideoView videoView;
    @Bind(R.id.media_controller)
    UniversalMediaController mediaController;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private SpokenPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursespoken);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
    }

    private void initView() {
        adapter = new SpokenPagerAdapter(getSupportFragmentManager(), this);
        View loadView = LayoutInflater.from(this).inflate(R.layout.layout_loading, null);
        toolbar.setNavigationIcon(R.drawable.back);
        mediaController.setOnLoadingView(loadView);
        videoView.setMediaController(mediaController);
        setVideoAreaSize();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        videoView.setVideoViewCallback(this);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion ");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause ");
        if (videoView != null && videoView.isPlaying()) {
            mSeekPosition = videoView.getCurrentPosition();
            Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            videoView.pause();
        }
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        videoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = videoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = videoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                videoLayout.setLayoutParams(videoLayoutParams);
//                videoView.setVideoPath(audioUrl);
                videoView.requestFocus();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Position=" + videoView.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
        Log.d(TAG, "onRestoreInstanceState Position=" + mSeekPosition);
    }


    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = videoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            videoLayout.setLayoutParams(layoutParams);

        } else {
            ViewGroup.LayoutParams layoutParams = videoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            videoLayout.setLayoutParams(layoutParams);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPause UniversalVideoView callback");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            videoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

    private void saveData() {
        SpokenEntity spoken = new SpokenEntity();
        spoken.setId("spoke00");
        spoken.setTitle("英语口语入门 00");
        spoken.setAudioUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");

        spoken.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ViewUtils.showToastShort(CourseSpokenActivity.this, "插入成功");
                Logger.i("插入成功");
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.showToastShort(CourseSpokenActivity.this, "插入失败");
                Logger.i("插入失败" + "Error:" + s);
            }
        });
    }

    @Override
    public void onSpokenSelected(String title, String audioUrl) {
        this.title = title;
        this.audioUrl = audioUrl;
        videoView.setVideoPath(audioUrl);
        Logger.i("title->" + title + " audioUrl->" + audioUrl);

        if (mSeekPosition > 0) {
            videoView.seekTo(mSeekPosition);
        }
        videoView.start();
        mediaController.setTitle("英语口语入门");
    }
}
