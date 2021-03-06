package com.tekinarslan.material.sample.ui.module.study;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.camnter.easycountdowntextureview.EasyCountDownTextureView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.ui.module.community.Player;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/1.
 * Exam onLine
 */
public class ExamActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.seekbar)
    SeekBar seekbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.view_down)
    EasyCountDownTextureView downTextureView;

    ObjectAnimator animator;

    private Player player;

    private boolean isPlay = true;

    private ExamPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        downTextureView.setTimeHour(2);
        downTextureView.setTimeMinute(10);
        downTextureView.setTimeSecond(0);
        toolbar.setNavigationIcon(R.drawable.back);

        viewPager.setOffscreenPageLimit(4); //keep 4 page instances in System
        adapter = new ExamPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        player = new Player(seekbar);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (this != null) {
                    if (player != null) {
                        player.stop();
                    }
                    finish();
                }
            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    ivPlay.setImageResource(R.drawable.actionbar_discover_normal);
                    animator = ObjectAnimator.ofFloat(ivPlay, "rotation", 0.0f, 360.0f);
                    animator.setDuration(5000);
                    animator.setInterpolator(new LinearInterpolator());
                    animator.setRepeatCount(Integer.MAX_VALUE);
                    animator.setRepeatMode(ValueAnimator.RESTART);
                    animator.start();

                    player.playUrl(Contast.CET4LISTEN);
                    isPlay = false;
                } else {
                    ivPlay.setImageResource(R.drawable.actionbar_discover_selected);
                    animator.end();
                    player.pause();
                    isPlay = true;
                }
            }
        });
    }

    public static class ExamPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public ExamPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ExamWriteFragment.newInstance(0, "写作");
                case 1:
                    return ExamListenFragment.newInstance(1, "听力");
                case 2:
                    return ExamReadFragment.newInstance(2, "阅读");
                case 3:
                    return ExamTranslateFragment.newInstance(3, "翻译");

                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

}
