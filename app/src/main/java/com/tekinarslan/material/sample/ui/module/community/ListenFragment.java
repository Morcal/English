package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.Topic;
import com.tekinarslan.material.sample.ui.adapter.ListenerAdapter;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/3/4.
 */
public class ListenFragment extends Fragment {
    private static final String TAG = ListenFragment.class.getSimpleName();
    private static final String URL = "http://apineo.llsapp.com/api/v1/topics/essential?page=1&appId=lls&deviceId=868201026091087&sDeviceId=868201026091087&appVer=4&token=809685e0c40d013333bf0273409c204a";
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<String> mDatas;
    private ListenerAdapter mAdapter;
    private List<Topic.TopicsEntity> topicsEntityList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData(URL);
//        initEvent();
    }

    private void initData(String type) {
        ViewUtils.showDialog(getActivity(), "Loading");
        Dao.getEntity(type, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(getActivity(), getString(R.string.error));
                ViewUtils.hideDialog();
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Log.i(TAG, "result: " + result);
                Topic topic = new Gson().fromJson(result, Topic.class);
                topicsEntityList = topic.getTopics();
                int size = topicsEntityList.size();
                Log.i(TAG, "size: " + size);
                initView();
            }
        });
    }


    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(mAdapter = new ListenerAdapter(getActivity(), topicsEntityList));
        initEvent();
    }


    private void initEvent() {
        mAdapter.setOnItemClickListener(new ListenerAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Log.i(" ", "audioUrl：" + data);
                Intent intent = new Intent(getActivity(), PlayAudioActivity.class);
                intent.putExtra("AUDIO", data);
                startActivity(intent);

            }
        });
    }

}
