package com.tekinarslan.material.sample.ui.module.study;

import android.content.Intent;
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
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.weight.FlashView;

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
    @Bind(R.id.flash_view)
    FlashView flashView;
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
        initEvent();
        return view;
    }


    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
        gridView.setOnItemClickListener(this);
    }

    private void initView() {

        // 初始化轮播
        List<String> imageUrls = new ArrayList<String>();
        imageUrls.add("drawable://" + R.drawable.header_cet4);
        imageUrls.add("drawable://" + R.drawable.header_cet6);
        flashView.setImageUris(imageUrls);
        flashView.setEffect(Contast.DEFAULT_EFFECT);//更改图片切换的动画效果

        dataList = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_gridview, new String[]{"pic", "name"}, new int[]{R.id.item_image, R.id.item_text});
        gridView.setAdapter(simpleAdapter);

        setChart();
    }

    private void initEvent() {
        flashView.setOnPageClickListener(new FlashViewListener() {
            @Override
            public void onClick(int position) {
                Logger.i("fishView position->" + position);
                switch (position) {
                    case 0:
                        Intent cet4Intent = new Intent(getActivity(), CourseCETActivity.class);
                        startActivity(cet4Intent);
                        break;
                    case 1:
                        Intent cet6Intent = new Intent(getActivity(), CourseCETActivity.class);
                        startActivity(cet6Intent);
                        break;
                    default:
                        break;
                }
            }
        });

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
        Log.i(TAG, "模块：" + str + position);
        switch (position) {
            case 0:
            case 3:
                Intent subject = new Intent(getActivity(), SubjectActivity.class);
                startActivity(subject);
                break;
            case 1:
            case 2:
                Intent exam = new Intent(getActivity(), ExamActivity.class);
                startActivity(exam);
                break;
            case 4:
                break;
            case 5:
                Intent course = new Intent(getActivity(), CourseSpokenActivity.class);
                startActivity(course);
                break;
            default:
                break;
        }

    }

}
