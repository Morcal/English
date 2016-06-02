package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/6/1.
 * 在线测试听力viewpager的fragment
 */
public class ExamListenQuestionFragment extends Fragment {
    @Bind(R.id.tv_question)
    TextView tvQues;
    @Bind(R.id.radioA)
    AppCompatRadioButton radioA;
    @Bind(R.id.radioB)
    AppCompatRadioButton radioB;
    @Bind(R.id.radioC)
    AppCompatRadioButton radioC;
    @Bind(R.id.radioD)
    AppCompatRadioButton radioD;

    private int page;
    private String title;
    private ArrayList<String> entity;

    public static ExamListenQuestionFragment newInstance(int page, String title, ArrayList<String> list) {
        ExamListenQuestionFragment fragmentFirst = new ExamListenQuestionFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        args.putStringArrayList("choices", list);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        entity = getArguments().getStringArrayList("choices");
        Logger.i("entity->" + entity.get(0) + "   " + entity.get(1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        tvQues.setText(title);
        radioA.setText("A." + entity.get(0));
        radioB.setText("B." + entity.get(1));
        radioC.setText("C." + entity.get(2));
        radioD.setText("D." + entity.get(3));
    }
}
