package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Topic;
import com.tekinarslan.material.sample.utills.UIUtil;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/3/17.
 */
public class ListenerAdapter extends RecyclerView.Adapter<ListenerAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Topic.TopicsEntity> list;
    private OnRecycleViewItemClickListener onItemClickListener = null;

    public ListenerAdapter(Context context, List<Topic.TopicsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_listen, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        // 为View设置监听事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Topic.TopicsEntity entity = list.get(position);
        Topic.TopicsEntity.CircleEntity circle = entity.getCircle();
        holder.name.setText(circle.getName());
        holder.title.setText(entity.getTitle());
        holder.viewCount.setText(entity.getViewCount() + "");
        holder.itemView.setTag(entity);
        UIUtil.setAvatar(entity.getCover(), holder.roundedImageView, 477, 477);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Log.i(" ", "adpter view " + v.toString());
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, ((Topic.TopicsEntity) v.getTag()).getId());
        }
    }

    // 对外暴露方法
    public void setOnItemClickListener(OnRecycleViewItemClickListener listener) {
        this.onItemClickListener = listener;
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

    public static interface OnRecycleViewItemClickListener {
        void onItemClick(View view, String data);
    }
}

