package com.tekinarslan.material.sample.ui.module.own;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.ui.module.home.LoginActivity;
import com.tekinarslan.material.sample.utills.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class SettingActivity extends AppCompatActivity implements isLoginSuccessListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_islogin)
    TextView tvIsLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        toolbar.setTitle("设置");
        getSupportActionBar();
        toolbar.setNavigationIcon(R.drawable.back);
        if (Contast.isLogin) {
//            tvIsLogin.setText("退出登录");
        } else {
//            tvIsLogin.setText("登录");
        }
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvIsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Contast.isLogin) {
                    ViewUtils.showToastShort(SettingActivity.this, "已登录");
                } else {
                    Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin) {
            tvIsLogin.setText("退出登录");
        } else {
            tvIsLogin.setText("登录");
        }
    }
}
