package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
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
import com.tekinarslan.material.sample.bean.Podcasts;
import com.tekinarslan.material.sample.bean.ShKeDetial;
import com.tekinarslan.material.sample.ui.adapter.ShuoKeAdapter;
import com.tekinarslan.material.sample.ui.adapter.ShuoKeDetialAdapter;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/4/20.
 */
public class ShuokeDetialActivity extends AppCompatActivity {
    private static final String TAG = ShuokeDetialActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_share)
    TextView tvShare;
    @Bind(R.id.text_empty)
    TextView tvEmpty;
    @Bind(R.id.recyclerview)
    XRecyclerView xRecyclerView;

    private ShuoKeDetialAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    private int pageCount = 1;
    private String background;
    private String id;
    private String title;
    private String body;
    private String name;
    private String subscribesCount;
    private List<ShKeDetial.EpisodesEntity> episodes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuokedetail);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        Logger.i("bundle:" + bundle);
        id = bundle.getString("ID");
        title = bundle.getString("TITLE");
        background = bundle.getString("BACKGROUND");
        body = bundle.getString("BODY");
        name = bundle.getString("NAME");
        String ragline = bundle.getString("TAGLINE");
        String avatar = bundle.getString("AVATAR");
        String episodesCount = bundle.getString("EPISODESCOUNT");
        subscribesCount = bundle.getString("SUBSCRIBESCOUNT");
        Logger.i("id:" + id + " title:" + title + " subscribesCount:" + subscribesCount);
        String url = Contast.SHUOKE + id + Contast.SHKPARAMS + "&page=" + pageCount;
        Logger.i("说客详情页请求地址->" + url);
        initListData(url);

//        listData = new ArrayList<String>();
//        for (int i = 0; i < 15; i++) {
//            listData.add("item" + (i + listData.size()));
//        }
    }

    private void initListData(String url) {
        ViewUtils.showDialog(this, "Loading");
        Dao.getEntity(url, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(ShuokeDetialActivity.this, getString(R.string.error));
                ViewUtils.hideDialog();
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Log.i(TAG, "result: " + result);
                ShKeDetial shKeDetial = new Gson().fromJson(result, ShKeDetial.class);
                episodes = shKeDetial.getEpisodes();
                mAdapter = new ShuoKeDetialAdapter((ArrayList<ShKeDetial.EpisodesEntity>) episodes);
                int size = episodes.size();
                Logger.i("episodes zize: " + size);

                xRecyclerView.setAdapter(mAdapter);
                xRecyclerView.setRefreshing(true);
            }
        });
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);

        ImageView cover = (ImageView) header.findViewById(R.id.iv_cover);
        TextView dyunNum = (TextView) header.findViewById(R.id.tv_dyue_num);
        TextView tvTitle = (TextView) header.findViewById(R.id.tv_title);
        TextView tvBody = (TextView) header.findViewById(R.id.tv_body);

        // 设置头部信息
        dyunNum.setText("订阅 " + subscribesCount);
        tvTitle.setText(title);
        tvBody.setText(body);
        UIUtil.setAvatar(background, cover, 360, 155);

        xRecyclerView.addHeaderView(header);

//        mAdapter = new ShuoKeDetialAdapter(listData);
//        xRecyclerView.setAdapter(mAdapter);
//        xRecyclerView.setRefreshing(true);
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

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("分享中...");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "分享内容测试。。。");
                startActivity(Intent.createChooser(intent, "分享到"));
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
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            pageCount++;
                            xRecyclerView.loadMoreComplete();
//                            for (int i = 0; i < 15; i++) {
//                                listData.add("item" + (i + listData.size()));
//                            }
                            // 上拉加载更多
                            String url = Contast.SHUOKE + id + Contast.SHKPARAMS + "&page=" + pageCount;
                            Logger.i("加载更多时的url->" + url);

                            Dao.getEntity(url, new Dao.EntityListener() {
                                @Override
                                public void onError() {
                                    ViewUtils.showToastShort(ShuokeDetialActivity.this, getString(R.string.error));
                                }

                                @Override
                                public void onSuccess(String result) {
                                    Log.i(TAG, "result: " + result);
                                    ShKeDetial shKeDetial = new Gson().fromJson(result, ShKeDetial.class);
                                    List<ShKeDetial.EpisodesEntity> addepisodes = shKeDetial.getEpisodes();
                                    episodes.addAll(addepisodes);
                                    mAdapter = new ShuoKeDetialAdapter((ArrayList<ShKeDetial.EpisodesEntity>) episodes);
                                    int size = episodes.size();
                                    Logger.i("episodes zize: " + size);

                                    xRecyclerView.setAdapter(mAdapter);
//                                    xRecyclerView.setRefreshing(true);
                                }
                            });

                            mAdapter.notifyDataSetChanged();
                            xRecyclerView.refreshComplete();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            mAdapter.notifyDataSetChanged();
                            xRecyclerView.loadMoreComplete();
                        }
                    }, 1000);
                }
                times++;
            }
        });
    }
}
