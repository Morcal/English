package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tekinarslan.material.sample.R;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        ButterKnife.bind(this);
    }
}
