package com.tekinarslan.material.sample.ui.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/3.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.iv_bg)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.set_login);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        imageView.startAnimation(animation);
    }
}
