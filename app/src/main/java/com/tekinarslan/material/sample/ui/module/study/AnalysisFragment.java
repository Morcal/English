package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/5/4.
 */
public class AnalysisFragment extends Fragment {
    TextView analysis;

    private String english;

    public static AnalysisFragment newInstance(String english) {
        AnalysisFragment fragment = new AnalysisFragment();
        Bundle args = new Bundle();
        args.putString("ENGLISH", english);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("AnalysisFragment");
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        analysis = (TextView) view.findViewById(R.id.tv_analysis);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            english = getArguments().getString("ENGLISH");
            Logger.i("english->" + english);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        analysis.setText("示例范文：\n" + english);
    }
}
