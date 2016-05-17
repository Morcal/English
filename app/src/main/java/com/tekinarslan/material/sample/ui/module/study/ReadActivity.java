package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.ui.adapter.ParalFragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class ReadActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_content)
    TextView content;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    ParalFragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {

    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        FragmentManager fm = getSupportFragmentManager();
        adapter = new ParalFragmentAdapter(fm);
        viewPager.setAdapter(adapter);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != this) {
                    finish();
                }
            }
        });
    }
}
