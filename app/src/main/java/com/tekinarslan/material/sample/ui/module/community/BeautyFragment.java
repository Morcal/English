package com.tekinarslan.material.sample.ui.module.community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/3/26.
 */
public class BeautyFragment extends Fragment {
//    @Bind(R.id.srl_beauty)
//    SwipeRefreshLayout refreshLayout;
//    @Bind(R.id.rlv_beauty)
//    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_beauty, container, false);
        View view = inflater.inflate(R.layout.item_beauty, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
