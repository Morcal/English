package com.tekinarslan.material.sample.ui.module.study;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class StudyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = StudyFragment.class.getSimpleName();
    @Bind(R.id.roll_view_pager)
    RollPagerView rollPagerView;
    @Bind(R.id.gridView)
    GridView gridView;
    @Bind(R.id.grow_chart)
    CombinedChart mChart;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;
    private final int itemcount = 12;


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
        gridView.setOnItemClickListener(this);
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

        setChart();
    }

    private void setChart() {
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.getAxisRight().setEnabled(false); // 隐藏右边的坐标轴
        mChart.getXAxis().setGridColor(Color.TRANSPARENT);//去掉网格中竖线的显示

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        CombinedData data = new CombinedData(Contast.mMonths);

        data.setData(generateLineData());
        mChart.setData(data);
        mChart.invalidate();
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(getRandom(15, 10), index));

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setDrawCubic(true);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_green);
        set.setFillDrawable(drawable);
        set.setDrawFilled(true);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set);
        return d;
    }


    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
        //显示所选Item的ItemText
        String str = (String) item.get("name");
        Log.i(TAG, "模块：" + str);
    }

    // 轮播ViewPager适配器
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