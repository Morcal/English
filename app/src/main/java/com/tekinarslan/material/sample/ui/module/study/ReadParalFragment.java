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
import com.tekinarslan.material.sample.bean.Read;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class ReadParalFragment extends Fragment {
    private int num; //页号
    private int pos;
    private String question;
    private String[] entitys = new String[4];

    private AppCompatRadioButton radioA;
    private AppCompatRadioButton radioB;
    private AppCompatRadioButton radioC;
    private AppCompatRadioButton radioD;
    private TextView tv;

    public ReadParalFragment newInstance(int num, int pos, String question, String entity) {
        ReadParalFragment fragment = new ReadParalFragment();
        Bundle args = new Bundle();
        entitys = entity.split(",");
        Logger.i(pos + ":entitys->" + entity.toString());
        args.putInt("num", num);
        args.putInt("pos", pos);
        args.putString("question", question);
        args.putCharSequenceArray("entity", entitys);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        num = getArguments() != null ? getArguments().getInt("num") : 1;
        pos = getArguments() != null ? getArguments().getInt("pos") : 1;
        question = getArguments() != null ? getArguments().getString("question") : "Not Question";
        entitys = getArguments() != null ? getArguments().getStringArray("entity") : new String[]{"A Simple", "B Simple", "C Simple", "D Simple"};

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.i("onCreateView");
        View view = inflater.inflate(R.layout.fragment_paral, null);
        tv = (TextView) view.findViewById(R.id.text);
        radioA = (AppCompatRadioButton) view.findViewById(R.id.radioA);
        radioB = (AppCompatRadioButton) view.findViewById(R.id.radioB);
        radioC = (AppCompatRadioButton) view.findViewById(R.id.radioC);
        radioD = (AppCompatRadioButton) view.findViewById(R.id.radioD);
        tv.setText(pos + "." + question);
        radioA.setText(entitys[0]);
        radioB.setText(entitys[1]);
        radioC.setText(entitys[2]);
        radioD.setText(entitys[3]);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i("onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
    }
}
