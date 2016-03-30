package com.tekinarslan.material.sample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekinarslan.material.sample.ui.module.community.BeautyFragment;
import com.tekinarslan.material.sample.ui.module.community.ListenFragment;
import com.tekinarslan.material.sample.ui.module.community.SampleFragment;
import com.tekinarslan.material.sample.ui.module.community.ShuokeFragment;
import com.tekinarslan.material.sample.ui.module.community.SiftFragment;
import com.tekinarslan.material.sample.ui.module.community.WordsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 6;
    private String titles[];

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles = titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0: //每日精选
                return new SiftFragment();
            case 1: //词汇
                return new WordsFragment();
            case 2: //听力
                return new ListenFragment();
            case 3: //说客
                return new ShuokeFragment();
            case 4: //美文
                return new BeautyFragment();
            case 5: //写作
                return SampleFragment.newInstance(position);
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}