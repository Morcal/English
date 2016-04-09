package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.saiff35.livingtabs.LivingTabsLayout;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.ui.module.message.ConversationFragment;
import com.tekinarslan.material.sample.ui.module.message.LinkmanFragment;
import com.tekinarslan.material.sample.ui.module.message.PlaceholderFragment;

import java.util.Locale;

/**
 * Created by lyqdhgo on 2016/4/8.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter implements LivingTabsLayout.DrawableResIconAdapter {
    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // 消息页
                return new ConversationFragment();
            case 1:
                // 联系人列表
                return new LinkmanFragment();
            case 2:
                // 打卡/签到
                return PlaceholderFragment.newInstance(position + 1);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.meaasge).toUpperCase(l);
            case 1:
                return context.getString(R.string.linkman).toUpperCase(l);
            case 2:
                return context.getString(R.string.singin).toUpperCase(l);
        }
        return null;
    }

    @Override
    public int getIcon(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_home;
            case 1:
                return R.drawable.ic_account;
            case 2:
                return R.drawable.ic_fire;
        }
        return -1;
    }
}
