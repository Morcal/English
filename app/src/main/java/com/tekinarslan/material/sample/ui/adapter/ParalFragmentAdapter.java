package com.tekinarslan.material.sample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekinarslan.material.sample.bean.Read;
import com.tekinarslan.material.sample.ui.module.study.ReadParalFragment;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class ParalFragmentAdapter extends FragmentPagerAdapter {

    private List<Read.ShortChoiceEntity> list;

    public ParalFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

//    public ParalFragmentAdapter(FragmentManager fm, List<Read.ShortChoiceEntity> list) {
//        super(fm);
//        this.list = list;
//    }

    public void setList(List<Read.ShortChoiceEntity> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReadParalFragment.newInstance(position, list.get(position).getPos(), list.get(position).getQuestion(), "A."+list.get(position).getChoice().get(0).getContent()+",B."+list.get(position).getChoice().get(1).getContent()+",C."+list.get(position).getChoice().get(2).getContent()+",D."+list.get(position).getChoice().get(3).getContent());
            case 1:
                return ReadParalFragment.newInstance(position, list.get(position).getPos(), list.get(position).getQuestion(), "A."+list.get(position).getChoice().get(0).getContent()+",B."+list.get(position).getChoice().get(1).getContent()+",C."+list.get(position).getChoice().get(2).getContent()+",D."+list.get(position).getChoice().get(3).getContent());
            case 2:
                return ReadParalFragment.newInstance(position, list.get(position).getPos(), list.get(position).getQuestion(), "A."+list.get(position).getChoice().get(0).getContent()+",B."+list.get(position).getChoice().get(1).getContent()+",C."+list.get(position).getChoice().get(2).getContent()+",D."+list.get(position).getChoice().get(3).getContent());
            case 3:
                return ReadParalFragment.newInstance(position, list.get(position).getPos(), list.get(position).getQuestion(), "A."+list.get(position).getChoice().get(0).getContent()+",B."+list.get(position).getChoice().get(1).getContent()+",C."+list.get(position).getChoice().get(2).getContent()+",D."+list.get(position).getChoice().get(3).getContent());
            case 4:
                return ReadParalFragment.newInstance(position, list.get(position).getPos(), list.get(position).getQuestion(), "A."+list.get(position).getChoice().get(0).getContent()+",B."+list.get(position).getChoice().get(1).getContent()+",C."+list.get(position).getChoice().get(2).getContent()+",D."+list.get(position).getChoice().get(3).getContent());
            default:
                return new ReadParalFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}
