package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.Podcasts;
import com.tekinarslan.material.sample.ui.adapter.ShuoKeAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/3/19.
 */
public class ShuokeFragment extends Fragment {
    private static final String TAG = ShuokeFragment.class.getSimpleName();
    @Bind(R.id.swiprefreshlayout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler_podcast)
    RecyclerView recyclerView;
    private int pageCount = 1;
    private ShuoKeAdapter shuoKeAdapter;
    private List<Podcasts.PodcastsEntity> podcastList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_shuoke, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        initData(Contast.HOSTURL + "&page=" + pageCount);
    }

    private void initData(String url) {
        ViewUtils.showDialog(getActivity(), "Loading");
        Dao.getEntity(url, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(getActivity(), getString(R.string.error));
                ViewUtils.hideDialog();
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Log.i(TAG, "result: " + result);
                Podcasts podcasts = new Gson().fromJson(result, Podcasts.class);
                podcastList = podcasts.getPodcasts();
                shuoKeAdapter = new ShuoKeAdapter(getActivity(), podcastList);
                int size = podcastList.size();
                Log.i(TAG, "shuokesize: " + size);
                initView();
            }
        });
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(shuoKeAdapter);
        initEvent();
    }

    private void initEvent() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageCount++;
                String url = Contast.HOSTURL + "&page=" + pageCount;
                Dao.getEntity(url, new Dao.EntityListener() {
                    @Override
                    public void onError() {
                        ViewUtils.showToastShort(getActivity(), "刷新失败");
                    }

                    @Override
                    public void onSuccess(String result) {
                        List<Podcasts.PodcastsEntity> list = new ArrayList<>();
                        Podcasts podcasts = new Gson().fromJson(result, Podcasts.class);
                        list = podcasts.getPodcasts();
                        podcastList.clear();
                        podcastList.addAll(list);
                        shuoKeAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        });

        shuoKeAdapter.setOnItemClickListener(new ShuoKeAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view, Bundle data) {
                Logger.i("ID：" + data);
                String id = data.getString("ID");
                String avatar = data.getString("AVATAR");
                String name = data.getString("NAME");
                String background = data.getString("BACKGROUND");
                String subscribecount = data.getString("SUBSCRIBESCOUNT");
                String title = data.getString("TITLE");
                String body = data.getString("BODY");
                Bundle bundle = new Bundle();
                bundle.putString("ID", id);
                bundle.putString("AVATAR", avatar);
                bundle.putString("NAME", name);
                bundle.putString("BACKGROUND", background);
                bundle.putString("TITLE", title);
                bundle.putString("BODY", body);
                bundle.putString("SUBSCRIBESCOUNT", subscribecount);
                Intent intent = new Intent(getActivity(), ShuokeDetialActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
