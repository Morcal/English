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
public class SubjectFragment extends Fragment {
    //    @Bind(R.id.tv_dir)
    TextView tvDirection;
    //    @Bind(R.id.tv_chinese)
    TextView tvChinese;

    private String direction;
    private String chinese;

    public static SubjectFragment newInstance(String direction, String chinese) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putString("DIRECTION", direction);
        args.putString("CHINESE", chinese);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("SubjectFragment");
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        tvDirection = (TextView) view.findViewById(R.id.tv_dir);
        tvChinese = (TextView) view.findViewById(R.id.tv_chinese);
//        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            direction = getArguments().getString("DIRECTION");
            chinese = getArguments().getString("CHINESE");
            Logger.i("dir->" + direction + " chinese->" + chinese);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        tvDirection.setText(direction);
        tvChinese.setText(chinese);
    }
}
