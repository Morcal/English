package com.tekinarslan.material.sample.ui.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/3.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.iv_bg)
    ImageView imageView;
    @Bind(R.id.layout_content)
    FrameLayout content;
    @Bind(R.id.tv_toregister)
    TextView toRegister;

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    private boolean isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.set_login);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        imageView.startAnimation(animation);
        setDefaultFragment();
    }

    public void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        loginFragment = new LoginFragment();
        transaction.setCustomAnimations(R.anim.slide_in_from_right,
                R.anim.slide_out_to_right);
        transaction.replace(R.id.layout_content, loginFragment);
        transaction.commit();
    }

    private void initEvent() {
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin) {
                    toRegister.setText("没有账户？请注册。");
                    setDefaultFragment();
                    isLogin = false;
                } else {
                    toRegister.setText("有了账户?请登录。");
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    if (registerFragment == null) {
                        registerFragment = new RegisterFragment();
                    }
                    transaction.setCustomAnimations(R.anim.slide_in_from_left,
                            R.anim.slide_out_to_left);
                    transaction.replace(R.id.layout_content, registerFragment);
                    transaction.commit();
                    isLogin = true;
                }
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }



}
