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
 */
public class ExamTranslateFragment extends Fragment {

    private String title;
    private int page;

    public static ExamTranslateFragment newInstance(int page, String title) {
        ExamTranslateFragment fragmentFirst = new ExamTranslateFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examtranslate, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
