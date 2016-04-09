package com.tekinarslan.material.sample.ui.module.message;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.ObseverListener;

/**
 * Created by lyqdhgo on 2016/4/9.
 */
public class ChatActivity extends AppCompatActivity implements ObseverListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    BmobIMConversation c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        if (getIntent() != null && getIntent().hasExtra(getPackageName())) {
            c = BmobIMConversation.obtain(BmobIMClient.getInstance(), (BmobIMConversation) getIntent().getBundleExtra(getPackageName()).getSerializable("c"));
        }
        initView();
        initEvent();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 接收到聊天消息
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(MessageEvent event) {

//        addMessage2Chat(event);
    }
}
