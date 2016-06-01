package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;

import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/6/1.
 * 听力解析界面
 */
public class ListenerAnalyFragment extends Fragment {


    public static ListenerAnalyFragment newInstance() {
        ListenerAnalyFragment fragmentFirst = new ListenerAnalyFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_analyze, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
