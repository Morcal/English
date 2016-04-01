package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.camnter.easycountdowntextureview.EasyCountDownTextureView;
import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/1.
 */
public class ExamActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.view_down)
    EasyCountDownTextureView downTextureView;

    private boolean isPlay = true;

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

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    ivPlay.setImageResource(R.drawable.actionbar_discover_normal);
                    isPlay = false;
                } else {
                    ivPlay.setImageResource(R.drawable.actionbar_discover_selected);
                    isPlay = true;
                }
            }
        });
    }

}
