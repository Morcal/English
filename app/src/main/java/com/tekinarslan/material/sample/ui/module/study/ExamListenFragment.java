package com.tekinarslan.material.sample.ui.module.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Listener;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/6/1.
 * 在线测试听力界面
 */
public class ExamListenFragment extends Fragment {
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private String title;
    private int page;

    private ListenerPagerAdapter adapter;

    static ArrayList<String> list1 = new ArrayList<>();
    static ArrayList<String> list2 = new ArrayList<>();
    static ArrayList<String> list3 = new ArrayList<>();
    static ArrayList<String> list4 = new ArrayList<>();
    static ArrayList<String> list5 = new ArrayList<>();
    static ArrayList<String> list6 = new ArrayList<>();
    static ArrayList<String> list7 = new ArrayList<>();
    static ArrayList<String> list8 = new ArrayList<>();
    static ArrayList<String> list9 = new ArrayList<>();
    static ArrayList<String> list10 = new ArrayList<>();

    public static ExamListenFragment newInstance(int page, String title) {
        ExamListenFragment fragmentFirst = new ExamListenFragment();
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
        View view = inflater.inflate(R.layout.fragment_examlistener, container, false);
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
        final BmobQuery<Listener> query = new BmobQuery<Listener>();
        query.findObjects(getActivity(), new FindListener<Listener>() {
            @Override
            public void onSuccess(List<Listener> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询成功");
                Listener listener = list.get(0);
                Listener.ListenEntity listenEntity = listener.getListen();
                // 返回每个题目
                List<Listener.ListenEntity.ListenQuestionEntity> questonlist = listenEntity.getListenQuestion();
                Logger.i("questionList->" + questonlist.size());
                Logger.i("pos1->" + questonlist.get(0).getPos() + " " + questonlist.get(0).getChoice().get(0).getContent());
                Logger.i("pos2->" + questonlist.get(1).getPos() + " " + questonlist.get(1).getChoice().get(0).getContent());

                createChoiceentity(list1, questonlist.get(0));
                createChoiceentity(list2, questonlist.get(1));
                createChoiceentity(list3, questonlist.get(2));
                createChoiceentity(list4, questonlist.get(3));
                createChoiceentity(list5, questonlist.get(4));
                createChoiceentity(list6, questonlist.get(5));
                createChoiceentity(list7, questonlist.get(6));
                createChoiceentity(list8, questonlist.get(7));
                createChoiceentity(list9, questonlist.get(8));
                createChoiceentity(list10, questonlist.get(9));

                adapter = new ListenerPagerAdapter(getChildFragmentManager(), questonlist);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(getActivity(), "查询失败");
            }
        });
    }


    private void createChoiceentity(List<String> list, Listener.ListenEntity.ListenQuestionEntity question) {
        list.add(question.getChoice().get(0).getContent());
        list.add(question.getChoice().get(1).getContent());
        list.add(question.getChoice().get(2).getContent());
        list.add(question.getChoice().get(3).getContent());
    }

    public static class ListenerPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 10;
        private List<Listener.ListenEntity.ListenQuestionEntity> list = new ArrayList<>();

        public ListenerPagerAdapter(FragmentManager fm, List<Listener.ListenEntity.ListenQuestionEntity> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ExamListenQuestionFragment.newInstance(0, "Question " + list.get(0).getPos(), list1);
                case 1:
                    return ExamListenQuestionFragment.newInstance(1, "Question " + list.get(1).getPos(), list2);
                case 2:
                    return ExamListenQuestionFragment.newInstance(2, "Question " + list.get(2).getPos(), list3);
                case 3:
                    return ExamListenQuestionFragment.newInstance(3, "Question " + list.get(3).getPos(), list4);
                case 4:
                    return ExamListenQuestionFragment.newInstance(4, "Question " + list.get(4).getPos(), list5);
                case 5:
                    return ExamListenQuestionFragment.newInstance(5, "Question " + list.get(5).getPos(), list6);
                case 6:
                    return ExamListenQuestionFragment.newInstance(6, "Question " + list.get(6).getPos(), list7);
                case 7:
                    return ExamListenQuestionFragment.newInstance(7, "Question " + list.get(7).getPos(), list8);
                case 8:
                    return ExamListenQuestionFragment.newInstance(8, "Question " + list.get(8).getPos(), list9);
                case 9:
                    return ExamListenQuestionFragment.newInstance(9, "Question " + list.get(9).getPos(), list10);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
