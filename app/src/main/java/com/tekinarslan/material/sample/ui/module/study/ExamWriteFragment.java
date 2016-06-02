package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Write;
import com.tekinarslan.material.sample.utills.UIUtil;
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
public class ExamWriteFragment extends Fragment {
    @Bind(R.id.tv_writedir)
    TextView writeDire;
    @Bind(R.id.tv_writeque)
    TextView writeQues;
    @Bind(R.id.et_write)
    EditText etWrite;
    @Bind(R.id.iv_image)
    ImageView image;

    private String title;
    private int page;

    public static ExamWriteFragment newInstance(int page, String title) {
        ExamWriteFragment fragmentFirst = new ExamWriteFragment();
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
        View view = inflater.inflate(R.layout.fragment_examwrite, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initView();
    }

    private void initData() {
        ViewUtils.showDialog(getActivity(), "加载中...");
        final BmobQuery<Write> query = new BmobQuery<Write>();
        query.getObject(getActivity(), "0bb5b8f6ff", new GetListener<Write>() {
            @Override
            public void onSuccess(Write write) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询成功");
                // 获取到单个写作对象
                Write.WriteEntity entity = write.getWrite();
                String direction = entity.getWriteDirection();
                String question = entity.getWriteQuestion();
                String imageUrl = entity.getWriteImageUrl();

                if (!direction.isEmpty()) {
                    writeDire.setText(direction);
                    writeDire.setVisibility(View.VISIBLE);
                }
                if (!question.isEmpty()) {
                    writeQues.setText(question);
                    writeQues.setVisibility(View.VISIBLE);
                    etWrite.setVisibility(View.VISIBLE);
                }
                if (!imageUrl.isEmpty()) {
                    image.setVisibility(View.VISIBLE);
                    UIUtil.setAvatar(imageUrl, image, 1000, 360);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询失败");
            }
        });
    }

    private void initView() {

    }
}
