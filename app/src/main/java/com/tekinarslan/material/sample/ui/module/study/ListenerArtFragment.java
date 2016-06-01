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
 * 听力原文界面
 */
public class ListenerArtFragment extends Fragment {

    private String title;
    private int page;

    public static ListenerArtFragment newInstance(int page, String title) {
        ListenerArtFragment fragmentFirst = new ListenerArtFragment();
        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_article, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
