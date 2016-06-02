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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/6/1.
 */
public class ListenerQuseFragment extends Fragment {

    private Listener listener;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private int pos;
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


    private List<Listener.ListenEntity.ListenQuestionEntity> list;

    private QuesPagerAdapter adapter;

    public static ListenerQuseFragment newInstance() {
        ListenerQuseFragment fragmentFirst = new ListenerQuseFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listener = (Listener) getArguments().getSerializable("LISTENER");
        Logger.i("size->" + listener.getListen().getListenQuestion().size());
        list = listener.getListen().getListenQuestion();
        pos = listener.getListen().getListenQuestion().get(0).getPos();
        int size = listener.getListen().getListenQuestion().get(0).getChoice().size();
        Logger.i("pos->" + pos + " size->" + size);
        Logger.i("content1->" + list.get(0).getChoice().get(0).getContent());
        Logger.i("content2->" + list.get(0).getChoice().get(1).getContent());

        createChoiceentity(list1, list.get(0));
        createChoiceentity(list2, list.get(1));
        createChoiceentity(list3, list.get(2));
        createChoiceentity(list4, list.get(3));
        createChoiceentity(list5, list.get(4));
        createChoiceentity(list6, list.get(5));
        createChoiceentity(list7, list.get(6));
        createChoiceentity(list8, list.get(7));
        createChoiceentity(list9, list.get(8));
        createChoiceentity(list10, list.get(9));
    }

    private void createChoiceentity(List<String> list, Listener.ListenEntity.ListenQuestionEntity question) {
        list.add(question.getChoice().get(0).getContent());
        list.add(question.getChoice().get(1).getContent());
        list.add(question.getChoice().get(2).getContent());
        list.add(question.getChoice().get(3).getContent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_question, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new QuesPagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

    public static class QuesPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 10;
        private List<Listener.ListenEntity.ListenQuestionEntity> list = new ArrayList<>();

        public QuesPagerAdapter(FragmentManager fragmentManager, List<Listener.ListenEntity.ListenQuestionEntity> list) {
            super(fragmentManager);
            this.list = list;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return QuestionFragment.newInstance(0, "Question " + list.get(0).getPos(), list1);
                case 1:
                    return QuestionFragment.newInstance(1, "Question " + list.get(1).getPos(), list2);
                case 2:
                    return QuestionFragment.newInstance(2, "Question " + list.get(2).getPos(), list3);
                case 3:
                    return QuestionFragment.newInstance(3, "Question " + list.get(3).getPos(), list4);
                case 4:
                    return QuestionFragment.newInstance(4, "Question " + list.get(4).getPos(), list5);
                case 5:
                    return QuestionFragment.newInstance(5, "Question " + list.get(5).getPos(), list6);
                case 6:
                    return QuestionFragment.newInstance(6, "Question " + list.get(6).getPos(), list7);
                case 7:
                    return QuestionFragment.newInstance(7, "Question " + list.get(7).getPos(), list8);
                case 8:
                    return QuestionFragment.newInstance(8, "Question " + list.get(8).getPos(), list9);
                case 9:
                    return QuestionFragment.newInstance(9, "Question " + list.get(9).getPos(), list10);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
