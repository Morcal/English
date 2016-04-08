package com.tekinarslan.material.sample.ui.module.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.saiff35.livingtabs.LivingTabsLayout;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.ui.adapter.SectionsPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class MessageFragment extends Fragment {
    private static final String TAG = MessageFragment.class.getSimpleName();
    @Bind(R.id.tabs)
    LivingTabsLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        Logger.i(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i(TAG, "onStart");
        initView();
    }

    private void initView() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getActivity());
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setCurrentItem(1);
        tabs.setupWithViewPager(viewPager);
    }
}
