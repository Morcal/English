package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.tekinarslan.material.sample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class StudyFragment extends Fragment {
    private static final String TAG = StudyFragment.class.getSimpleName();
    @Bind(R.id.roll_view_pager)
    RollPagerView rollPagerView;
    @Bind(R.id.gridView)
    GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
        // 点击无效
        rollPagerView.getViewPager().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "viewPager点击了");
            }
        });
    }

    private void initView() {
        rollPagerView.setPlayDelay(1000);
        rollPagerView.setAnimationDurtion(500);
        rollPagerView.setAdapter(new TestLoopAdapter(rollPagerView));
        //mRollViewPager.setAdapter(new TestNomalAdapter());
        rollPagerView.setHintView(new IconHintView(getActivity(), R.drawable.point_focus, R.drawable.point_normal));

        dataList = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_gridview, new String[]{"pic", "name"}, new int[]{R.id.item_image, R.id.item_text});
        gridView.setAdapter(simpleAdapter);
    }


    private List<Map<String, Object>> getData() {
        int[] itemImages = {
                R.drawable.icon_company_test_new, R.drawable.icon_course_study_new,
                R.drawable.icon_intelli_test_new, R.drawable.icon_question_center_new,
                R.drawable.icon_wrong_test_new, R.drawable.icon_tag_new
        };
        String[] itemTexts = {"专项练习", "历年真题", "在线测试", "精华专题", "错题练习", "课程学习"};

        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", itemImages[i]);
            map.put("name", itemTexts[i]);
            dataList.add(map);
        }
        return dataList;
    }


    private class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {
                R.drawable.header_cet4,
                R.drawable.header_cet6
        };

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.length;
        }

    }
}
