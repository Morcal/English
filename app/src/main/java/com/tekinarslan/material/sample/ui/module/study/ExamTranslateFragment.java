package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Translate;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by lyqdhgo on 2016/6/1.
 */
public class ExamTranslateFragment extends Fragment {
    @Bind(R.id.tv_dir)
    TextView tvDir;
    @Bind(R.id.tv_chinese)
    TextView tvChinese;

    private String title;
    private int page;

    public static ExamTranslateFragment newInstance(int page, String title) {
        ExamTranslateFragment fragmentFirst = new ExamTranslateFragment();
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
        View view = inflater.inflate(R.layout.fragment_examtranslate, container, false);
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
        final BmobQuery<Translate> query = new BmobQuery<Translate>();
        query.getObject(getActivity(), "e4e69a03d4", new GetListener<Translate>() {
            @Override
            public void onSuccess(Translate translate) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询成功");
                Logger.i("Translate" + translate.getTransChinese());
                tvDir.setText(translate.getTransDirection());
                tvChinese.setText(translate.getTransChinese());
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询失败" + s);
            }
        });
    }
}
