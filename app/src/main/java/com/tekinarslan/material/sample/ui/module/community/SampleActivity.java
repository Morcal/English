package com.tekinarslan.material.sample.ui.module.community;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.ui.adapter.ViewPagerAdapter;
import com.tekinarslan.material.sample.weight.SlidingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SampleActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPager pager;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout slidingTabLayout;
    private String titles[] = new String[]{"每日精选", "词单", "英乐"
            , "说客", "美文", "写作"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));
        slidingTabLayout.setViewPager(pager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        pager.setOffscreenPageLimit(5);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });
    }
}
