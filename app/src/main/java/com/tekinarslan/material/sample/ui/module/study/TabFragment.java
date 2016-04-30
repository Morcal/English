package com.tekinarslan.material.sample.ui.module.study;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.SpokenEntity;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/4/30.
 */
public class TabFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private SpokenAdapter adapter;

    CallBack callback;

    @Bind(R.id.listview)
    ListView listView;

    public static TabFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TabFragment pageFragment = new TabFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initView();
        initEvent();
    }

    private void initData() {
//        titles = new ArrayList<String>();
//        list.add("英语口语提升1");
//        list.add("英语口语提升2");
//        list.add("英语口语提升3");
//        list.add("英语口语提升4");
//        list.add("英语口语提升5");
//        list.add("英语口语提升6");
        BmobQuery<SpokenEntity> query = new BmobQuery<SpokenEntity>();
        query.findObjects(getActivity(), new FindListener<SpokenEntity>() {
            @Override
            public void onSuccess(List<SpokenEntity> list) {
                Logger.i("Spoken list size->" + list.size());
                ViewUtils.showToastShort(getActivity(), "查询成功");
                adapter = new SpokenAdapter(getActivity(), list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                Logger.i("Error:" + s);
                ViewUtils.showToastShort(getActivity(), "查询失败");
            }
        });
    }

    private void initView() {

    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.i("Select view->" + parent.getSelectedView());
                SpokenEntity entity = (SpokenEntity) parent.getItemAtPosition(position);
                Logger.i("select item：" + entity.getId());
                String title = entity.getTitle();
                String audioUrl = entity.getAudioUrl();
                Logger.i("title:" + title + " audioUrl->" + audioUrl);
            }
        });
    }

    interface CallBack {
        void getData(String title, String audioUrl);
    }

    class SpokenAdapter extends BaseAdapter {

        private List<SpokenEntity> list;
        private Context context;

        public SpokenAdapter(Context context, List<SpokenEntity> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_spoken, null);
                holder.spokenName = (TextView) convertView.findViewById(R.id.tv_spokename);
                holder.spokenPlay = (ImageView) convertView.findViewById(R.id.iv_playspokenm);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SpokenEntity entity = (SpokenEntity) getItem(position);
            holder.spokenName.setText(entity.getTitle());
            return convertView;
        }

        class ViewHolder {
            TextView spokenName;
            ImageView spokenPlay;
        }
    }

}


