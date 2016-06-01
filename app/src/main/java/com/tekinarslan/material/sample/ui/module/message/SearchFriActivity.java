package com.tekinarslan.material.sample.ui.module.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.module.home.BaseModel;
import com.tekinarslan.material.sample.ui.module.home.UserModel;
import com.tekinarslan.material.sample.ui.module.message.message.event.ChatEvent;
import com.tekinarslan.material.sample.ui.module.message.message.im.im.adapter.SearchUserAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class SearchFriActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_search)
    EditText search;
    @Bind(R.id.rc_view)
    RecyclerView recyclerView;
    @Bind(R.id.sw_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager layoutManager;
    private SearchUserAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        adapter = new SearchUserAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setEnabled(true);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    Logger.i("go->搜索");
                    //隐藏软键盘
                    hideSoftInputView();
                    swipeRefreshLayout.setRefreshing(true);
                    query();
                }
                return false;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
    }

    public void query() {
        String name = search.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ViewUtils.showToastShort(SearchFriActivity.this, "请填写用户名");
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        UserModel.getInstance().queryUsers(name, BaseModel.DEFAULT_LIMIT, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                Logger.i("搜索成功");
                swipeRefreshLayout.setRefreshing(false);
                adapter.setDatas(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {
                Logger.i("搜索失败");
                swipeRefreshLayout.setRefreshing(false);
                ViewUtils.showToastShort(SearchFriActivity.this, s + "(" + i + ")");
            }
        });
    }

    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        BmobIMUserInfo info = event.info;
        //如果需要更新用户资料，只需要传新的info进去就可以了
        Logger.i("" + info.getName() + "," + info.getAvatar() + "," + info.getUserId());
        BmobIM.getInstance().startPrivateConversation(info, new ConversationListener() {
            @Override
            public void done(BmobIMConversation c, BmobException e) {
                if (e == null) {
                    Logger.i("SearchFriActivity->onEventMainThread"+c);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("c", c);
                    Intent intent = new Intent(SearchFriActivity.this, ChatActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    ViewUtils.showToastShort(SearchFriActivity.this, e.getMessage() + "(" + e.getErrorCode() + ")");
                }
            }
        });
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
