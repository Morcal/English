package com.tekinarslan.material.sample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekinarslan.material.sample.ui.module.study.ReadParalFragment;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class ParalFragmentAdapter extends FragmentPagerAdapter {

    public ParalFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReadParalFragment.newInstance(position);
            case 1:
                return ReadParalFragment.newInstance(position);
            case 2:
                return ReadParalFragment.newInstance(position);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
