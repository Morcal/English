package com.tekinarslan.material.sample.ui.module.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.ui.module.community.SampleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/6/1.
 */
public class ListenerActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.top_group)
    RadioGroup radioGroup;
    @Bind(R.id.rabtn_article)
    RadioButton rabutArticle;
    @Bind(R.id.rabtn_question)
    RadioButton rabutQuestion;
    @Bind(R.id.rabtn_analyze)
    RadioButton rabutAnalyze;
    @Bind(R.id.content)
    FrameLayout frameLayout;

    private ListenerArtFragment artFragment;
    private ListenerQuseFragment quseFragment;
    private ListenerAnalyFragment analyFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {

    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        // 设置默认界面
        setDefaultFragment();
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (checkedId) {
                    case R.id.rabtn_article:
                        if (artFragment == null) {
                            artFragment = new ListenerArtFragment();
                        }
                        transaction.replace(R.id.content, artFragment);
                        break;
                    case R.id.rabtn_question:
                        if (quseFragment == null) {
                            quseFragment = new ListenerQuseFragment();
                        }
                        transaction.replace(R.id.content, quseFragment);
                        break;
                    case R.id.rabtn_analyze:
                        if (analyFragment == null) {
                            analyFragment = new ListenerAnalyFragment();
                        }
                        transaction.replace(R.id.content, analyFragment);
                        break;
                    default:
                        break;
                }
                transaction.commitAllowingStateLoss();
            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        quseFragment = new ListenerQuseFragment();
        transaction.replace(R.id.content, quseFragment);
        transaction.commit();
    }

}
