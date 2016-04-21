package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.ShKeDetial;

import java.util.ArrayList;

/**
 * Created by lyqdhgo on 2016/4/20.
 */
public class ShuoKeDetialAdapter extends RecyclerView.Adapter<ShuoKeDetialAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    public ArrayList<ShKeDetial.EpisodesEntity> datas = null;
    private OnRecycleViewItemClickListener onItemClickListener = null;


    public ShuoKeDetialAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<ShKeDetial.EpisodesEntity> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shk_detial, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ShKeDetial.EpisodesEntity entity = datas.get(position);
        viewHolder.mTextView.setText(entity.getTitle());

        viewHolder.itemView.setTag(entity);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            ShKeDetial.EpisodesEntity entity = (ShKeDetial.EpisodesEntity) v.getTag();
            Bundle bundle = new Bundle();
            bundle.putString("TITLE", entity.getTitle());
            bundle.putString("AVATAR", entity.getImage());
            bundle.putString("AUDIOURL", entity.getAudioUrl());
            bundle.putString("DESC", entity.getDescription());
            onItemClickListener.onItemClick(v, bundle);
        }
    }

    // 对外暴露方法
    public void setOnItemClickListener(OnRecycleViewItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_item_title);
        }
    }

    public static interface OnRecycleViewItemClickListener {
        //        void onItemClick(View view, String data);
        void onItemClick(View view, Bundle data);
    }
}
