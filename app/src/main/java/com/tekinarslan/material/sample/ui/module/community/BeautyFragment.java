package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Beauty;
import com.tekinarslan.material.sample.bean.Words;
import com.tekinarslan.material.sample.ui.adapter.BeautyAdapter;
import com.tekinarslan.material.sample.ui.adapter.ListenerAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/3/26.
 */
public class BeautyFragment extends Fragment {
    private static final String TAG = BeautyFragment.class.getSimpleName();
    @Bind(R.id.srl_beauty)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.rlv_beauty)
    RecyclerView recyclerView;
    private BeautyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty, container, false);
//        View view = inflater.inflate(R.layout.item_beauty, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        BmobQuery<Beauty> beauties = new BmobQuery<Beauty>();
        beauties.findObjects(getActivity(), new FindListener<Beauty>() {
            @Override
            public void onSuccess(List<Beauty> list) {
                ViewUtils.showToastShort(getActivity(), "beauty成功");
                Log.i(TAG, "beauty size->" + list.size());
                initView(list);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.showToastShort(getActivity(), "beauty失败");
            }
        });
    }

    private void initView(List<Beauty> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter = new BeautyAdapter(getActivity(), list));
        initEvent();
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new BeautyAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view, Beauty data) {
                Log.i(TAG, "点击的ID为->" + data.getTheme() + " image:" + data.getImgUrl());
                Bundle bundle=new Bundle();
                bundle.putString("THEME",data.getTheme());
                bundle.putString("IMAGEURL",data.getImgUrl());
                bundle.putString("ARTICLE",data.getArticle());
                Intent intent=new Intent(getActivity(),BeautyDetialActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
