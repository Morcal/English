package com.tekinarslan.material.sample.ui.module.message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;

/**
 * Created by lyqdhgo on 2016/4/8.
 */
public class PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RelativeLayout payInfo;
    private TextView butQiDao;
    private Animation anim;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    startAnimation();
                    break;
                default:
                    break;
            }
        }
    };

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.i("PlaceholderFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_imitate_qiandao, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        payInfo = (RelativeLayout) view.findViewById(R.id.rl_payInfo);
        butQiDao = (TextView) view.findViewById(R.id.but_qiandao);
        Logger.i("sectionNumber->" + sectionNumber);

    }

    @Override
    public void onStart() {
        super.onStart();
        initEvent();
    }

    private void initEvent() {
        butQiDao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newThread();
            }
        });
    }

    /**
     * 测试用的,开启子线程
     */
    private void newThread() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);

            }
        }).start();
    }

    /**
     * 启动打印小票动画
     */
    private void startAnimation() {
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_in);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        payInfo.startAnimation(anim);
    }


}
