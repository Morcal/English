package com.tekinarslan.material.sample.ui.module.community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.Article;
import com.tekinarslan.material.sample.bean.Topic;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/3/4.
 */
public class ListenFragment extends Fragment {
    private static final String TAG = ListenFragment.class.getSimpleName();
    private static final String URL = "http://apineo.llsapp.com/api/v1/topics/essential?page=1&appId=lls&deviceId=868201026091087&sDeviceId=868201026091087&appVer=4&token=809685e0c40d013333bf0273409c204a";
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private List<Topic.TopicsEntity> topicsEntityList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        initData();
        initDatas(URL);
//        initView();
    }

    protected void initData() {

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initDatas(String type) {
        ViewUtils.showDialog(getActivity(), "Loading");
        Dao.getEntity(type, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(getActivity(), getString(R.string.error));
                ViewUtils.hideDialog();
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Log.i(TAG, "result: " + result);
                Topic topic = new Gson().fromJson(result, Topic.class);
                topicsEntityList = topic.getTopics();
                int size = topicsEntityList.size();
                Log.i(TAG, "size: " + size);
                initView();
            }
        });
    }


    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(mAdapter = new HomeAdapter(topicsEntityList));
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        private List<Topic.TopicsEntity> list;

        public HomeAdapter(List<Topic.TopicsEntity> list) {
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_listen, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Topic.TopicsEntity entity = list.get(position);
            Topic.TopicsEntity.CircleEntity circle = entity.getCircle();
            holder.name.setText(circle.getName());
            holder.title.setText(entity.getTitle());
            holder.viewCount.setText(entity.getViewCount() + "");
            UIUtil.setAvatar(entity.getCover(), holder.roundedImageView, 477, 477);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            TextView title;
            TextView viewCount;
            RoundedImageView roundedImageView;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.tv_name);
                title = (TextView) view.findViewById(R.id.tv_title);
                viewCount = (TextView) view.findViewById(R.id.tv_viewCount);
                roundedImageView = (RoundedImageView) view.findViewById(R.id.imageView);
            }
        }
    }
}
