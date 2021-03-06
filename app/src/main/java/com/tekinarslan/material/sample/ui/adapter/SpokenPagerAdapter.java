package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekinarslan.material.sample.ui.module.study.CommentFragment;
import com.tekinarslan.material.sample.ui.module.study.IntroduceFragment;
import com.tekinarslan.material.sample.ui.module.study.TabFragment;

/**
 * Created by lyqdhgo on 2016/4/30.
 */
public class SpokenPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"章节", "评论", "详情"};
    private Context context;

    public SpokenPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // 章节
                return TabFragment.newInstance(position + 1);
            case 1: // 评论
                return CommentFragment.newInstance();
            case 2: // 详情
                return IntroduceFragment.newInstance();
            default:
                return TabFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
