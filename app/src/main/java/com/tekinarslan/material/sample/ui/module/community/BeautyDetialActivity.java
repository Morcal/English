package com.tekinarslan.material.sample.ui.module.community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/5/10.
 */
public class BeautyDetialActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_detial);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        toolbar.setTitle("我的收藏");
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initData() {

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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
