package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;

/**
 * Created by lyqdhgo on 2016/5/4.
 */
public class SubjectFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("SubjectFragment");
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        return view;
    }
}
