package com.tekinarslan.material.sample.ui.module.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.saiff35.livingtabs.LivingTabsLayout;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.adapter.SectionsPagerAdapter;
import com.tekinarslan.material.sample.ui.module.home.LoginActivity;
import com.tekinarslan.material.sample.ui.module.home.UserModel;
import com.tekinarslan.material.sample.utills.ViewUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.listener.ObseverListener;
import cn.bmob.newim.notification.BmobNotificationManager;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class MessageFragment extends Fragment implements ObseverListener {
    private static final String TAG = MessageFragment.class.getSimpleName();
    @Bind(R.id.tabs)
    LivingTabsLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        Logger.i(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i(TAG, "onStart");
        initView();
        setConnect();
    }

    @Override
    public void onResume() {
        super.onResume();
        //添加观察者-用于是否显示通知消息
        BmobNotificationManager.getInstance(getActivity()).addObserver(this);
        //进入应用后，通知栏应取消
        BmobNotificationManager.getInstance(getActivity()).cancelNotification();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //清理导致内存泄露的资源
        BmobIM.getInstance().clear();
    }

    /**
     * 注册消息接收事件
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(MessageEvent event) {
//        checkRedPoint();
    }

    /**
     * 注册离线消息接收事件
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(OfflineMessageEvent event) {
//        checkRedPoint();
    }

    private void initView() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getActivity());
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setCurrentItem(1);
        tabs.setupWithViewPager(viewPager);
    }

    private void setConnect() {
        //连接服务器
        User user = UserModel.getInstance().getCurrentUser();
        Logger.i("Current User-->" + user);
        // 当User为空时，说明用户未登录
        if (user == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {
            Logger.i("user->" + user.getObjectId());
            BmobIM.connect(user.getObjectId(), new ConnectListener() {
                @Override
                public void done(String uid, BmobException e) {
                    if (e == null) {
                        Logger.i("connect success");
                    } else {
                        Logger.e(e.getErrorCode() + "/" + e.getMessage());
                    }
                }
            });
            //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
            BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
                @Override
                public void onChange(ConnectionStatus status) {
                    ViewUtils.showToastShort(getActivity(), "" + status.getMsg());
                }
            });
            //解决leancanary提示InputMethodManager内存泄露的问题
//        IMMLeaks.fixFocusedViewLeak(getApplication());
        }
    }
}
