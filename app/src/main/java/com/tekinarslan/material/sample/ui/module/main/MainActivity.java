package com.tekinarslan.material.sample.ui.module.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.ui.module.community.SampleActivity;
import com.tekinarslan.material.sample.ui.module.home.LoginActivity;
import com.tekinarslan.material.sample.ui.module.message.MessageFragment;
import com.tekinarslan.material.sample.ui.module.own.OwnFragment;
import com.tekinarslan.material.sample.ui.module.study.StudyFragment;
import com.tekinarslan.material.sample.utills.PicassoImageLoader;
import com.tekinarslan.material.sample.weight.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String DEFAULTPATH = "/data/data/com.tekinarslan.material.sample/cache/ImagePicker/cropTemp/IMG_20160527_203815.jpg";
    private static final int NUM_ITEMS = 3;
    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation)
    NavigationView navigationView;
    @Bind(R.id.tab_group)
    RadioGroup radioGroup;
    @Bind(R.id.rabtn_study)
    RadioButton rabtnStudy;
    @Bind(R.id.rabtn_community)
    RadioButton rabtnCommunity;
    @Bind(R.id.rabtn_message)
    RadioButton rabtnMessage;
    @Bind(R.id.rabtn_own)
    RadioButton rabtnOwn;
    ActionBarDrawerToggle drawerToggle;
    private CircleImageView avatar;
    private StudyFragment studyFragment;
    private MessageFragment messageFragment;
    private OwnFragment ownFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    private void initView() {
        setTitle(getString(R.string.app_name));
        setDefaultFragment();
        View headView = navigationView.getHeaderView(0);
        avatar = (CircleImageView) headView.findViewById(R.id.avatar);
//        SharedPreferences preference = getSharedPreferences("avatorpath", Activity.MODE_PRIVATE);
//        String path = preference.getString("path", DEFAULTPATH);
//        PicassoImageLoader imageLoader = new PicassoImageLoader();
//        imageLoader.displayImage(MainActivity.this, path, avatar, 50, 50);
    }

    private void initEvent() {
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String item = menuItem.getTitle().toString();
                int itemId = menuItem.getItemId();
                Log.i("Item", item);
                Log.i("ItemId", itemId + "");
                selectMenuItem(itemId);
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (checkedId) {
                    case R.id.rabtn_study:
                        if (studyFragment == null) {
                            studyFragment = new StudyFragment();
                        }
                        transaction.replace(R.id.content, studyFragment);
                        break;
                    case R.id.rabtn_community:
                        Intent intent = new Intent(MainActivity.this, SampleActivity.class);
                        startActivityForResult(intent, 1);

                        break;
                    case R.id.rabtn_message:
                        if (messageFragment == null) {
                            messageFragment = new MessageFragment();
                        }
                        transaction.replace(R.id.content, messageFragment);
                        break;
                    case R.id.rabtn_own:
                        if (ownFragment == null) {
                            ownFragment = new OwnFragment();
                        }
                        transaction.replace(R.id.content, ownFragment);
                        break;
                }
                //transaction.commit();
                transaction.commitAllowingStateLoss();

            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Log.i(TAG, "登录/注册");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, 250);
            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        studyFragment = new StudyFragment();
        transaction.replace(R.id.content, studyFragment);
        transaction.commit();
    }

    private void selectMenuItem(int itemId) {
        drawerLayout.closeDrawers();
        switch (itemId) {
            case R.id.nav_action_home:
                break;
            case R.id.nav_action_subject:
                break;
            case R.id.nav_action_collect:
                break;
            case R.id.nav_action_own:
                break;
            case R.id.nav_action_note:
                break;
            case R.id.nav_action_setting:
                break;
            case R.id.nav_action_logout:
                break;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                rabtnStudy.setChecked(true);
                break;
        }
    }

}
