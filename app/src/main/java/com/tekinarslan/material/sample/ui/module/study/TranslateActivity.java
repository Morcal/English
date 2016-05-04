package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Translate;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.MessageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class TranslateActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_select)
    MessageButton messageButton;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.but_save)
    Button save;

    SlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        messageButton.select(0);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
    }

    private void initEvent() {
        messageButton.setSelectListener(new MessageButton.ISelectListener() {
            @Override
            public void select(int type) {
                if (type == 0) {
                    viewPager.setCurrentItem(0);
                    Logger.i("选择试题");
                } else {
                    viewPager.setCurrentItem(1);
                    Logger.i("选择解析");
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                messageButton.select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToBmob();
            }
        });
    }

    /* PagerAdapter class */
    public class SlidePagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_ITEMS = 2;

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();
            if (position == 0) {
                messageButton.select(0);
                fragment = new SubjectFragment();
            } else if (position == 1) {
                messageButton.select(1);
                fragment = new AnalysisFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

    private void saveToBmob() {
        Translate tranlate = new Translate();
        tranlate.setId("cet4151210");
        tranlate.setTitle("CET4translate151210");
        tranlate.setTransDirection("Directions: For this part, you are allowed 30 minutes to translate a passage from Chinese into English.You should write your answer on Answer Sheet 2.");
        tranlate.setTransChinese("联合国下属机构世界旅游组织(World Tourism Organization)公布的数据显示，中国游客对全球旅游业的贡献最大。中国人去年花在出境游上的支出膨胀至1020亿美元，同2011年相比增长了40%。联合国世界旅游组织在其网站上发布的一份声明中说，这一增幅令中国迅速超越德国和美国。后两者在之前是出境游支出最高的两个国家。2012年德美两国出境旅游支出均同比增长6%，约840亿美元。");
        tranlate.setTransEnglish("The figures from the United Nations World Tourism Organization show that Chinese travelers are making the most contributions to the global tourism industry. Chinese travelers spent a record $102 billion on outbound tourism last year, a 40% rise from 2011. That surge sent China screaming past Germany and the U.S. — the former No. 1 and No. 2 spenders, respectively 一 which both saw tourist outlays increase 6% year- on-year to around $84 billion in 2012, the UNWTO said in a statement on its website。");
        tranlate.setUserTranslate(" ");
        tranlate.setScore(106.5);
        tranlate.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ViewUtils.showToastShort(TranslateActivity.this, "插入成功");
                Logger.i("插入成功");
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.showToastShort(TranslateActivity.this, "插入失败");
                Logger.i("插入成功");
            }
        });
    }
}
