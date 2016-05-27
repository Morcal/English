package com.tekinarslan.material.sample.ui.module.own;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.ResultAskList;
import com.tekinarslan.material.sample.bean.ShKeDetial;
import com.tekinarslan.material.sample.ui.adapter.AskListAdapter;
import com.tekinarslan.material.sample.ui.adapter.ShuoKeDetialAdapter;
import com.tekinarslan.material.sample.ui.module.community.ShKAudioActivity;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/20.
 */
public class AskListActivity extends AppCompatActivity {
    private static final String TAG = AskListActivity.class.getSimpleName();
    private static String DEFAULT_USER_ID = "1";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.text_empty)
    TextView tvEmpty;
    @Bind(R.id.recyclerview)
    XRecyclerView xRecyclerView;

    private AskListAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    private int pageCount = 1;
    private String subscribesCount;
    private List<ResultAskList.DataEntity> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asklist);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
    }

    private void initData() {
        mAdapter = new AskListAdapter(this);

        // 获取提问列表
        // http://gaojinzhu.duapp.com/interface/user/AskListByUser?source=1&user_id=1
        SharedPreferences preference = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        String userId = preference.getString("objectId", DEFAULT_USER_ID);
        String askList = Contast.SERVERHOST + "/AskListByUser?source=3&user_id=" + userId;
        ViewUtils.showDialog(this, "Loading");
        Dao.getEntity(askList, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.hideDialog();
                Logger.i("获取失败");
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Logger.i("获取成功" + " Result->" + result);
                ResultAskList askList = new Gson().fromJson(result, ResultAskList.class);
                if ("9999".equals(askList.getE())) {
                    Logger.i("获取提问列表成功");
                    ViewUtils.showToastShort(AskListActivity.this, "获取成功");
                    List<ResultAskList.DataEntity> entity = askList.getData();
                    Logger.i("提问列表数->" + entity.size());
                    mAdapter.setDatas(entity);
                    xRecyclerView.setAdapter(mAdapter);
                    xRecyclerView.setRefreshing(true);
                } else {
                    Logger.i("其他原因导致获取提问列表失败");
                    ViewUtils.showToastShort(AskListActivity.this, "获取失败 " + askList.getM());
                }

            }
        });
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

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        listData.clear();
//                        for (int i = 0; i < 15; i++) {
//                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
//                        }
                        // 执行下拉刷新操作
                        mAdapter.notifyDataSetChanged();
                        xRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        listData.clear();
//                        for (int i = 0; i < 15; i++) {
//                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
//                        }
                        // 执行加载跟多操作
                        mAdapter.notifyDataSetChanged();
                        xRecyclerView.loadMoreComplete();
                    }

                }, 1000);
            }
        });
    }
}
