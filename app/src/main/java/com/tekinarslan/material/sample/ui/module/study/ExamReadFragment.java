package com.tekinarslan.material.sample.ui.module.study;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Read;
import com.tekinarslan.material.sample.ui.adapter.ParalFragmentAdapter;
import com.tekinarslan.material.sample.utills.Util;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/6/1.
 */
public class ExamReadFragment extends Fragment {

    @Bind(R.id.main_content)
    TextView content;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    ParalFragmentAdapter adapter;

    private String title;
    private int page;

    public static ExamReadFragment newInstance(int page, String title) {
        ExamReadFragment fragmentFirst = new ExamReadFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examread, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        ViewUtils.showDialog(getActivity(), "加载中...");
        final BmobQuery<Read> query = new BmobQuery<Read>();
        query.findObjects(getActivity(), new FindListener<Read>() {
            @Override
            public void onSuccess(List<Read> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询成功");

                String passage = list.get(0).getPassage();
                String pass = Util.ToDBC(passage);
                content.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf"));
                content.setText(pass);

                adapter = new ParalFragmentAdapter(getChildFragmentManager());
                adapter.setList(list.get(0).getShortChoice());
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询失败");
            }
        });

    }

}
