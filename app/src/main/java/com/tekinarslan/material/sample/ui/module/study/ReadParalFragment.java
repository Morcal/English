package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class ReadParalFragment extends Fragment {
    int mNum; //页号

    public static ReadParalFragment newInstance(int num) {
        ReadParalFragment fragment = new ReadParalFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paral, null);
        TextView tv = (TextView) view.findViewById(R.id.text);
        tv.setText("fragment+" + mNum);
        return view;
    }
}
