package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Beauty;
import com.tekinarslan.material.sample.bean.Topic;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.weight.HWRatioImageView;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/3/30.
 */
public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Beauty> list;
    private OnRecycleViewItemClickListener onItemClickListener = null;

    public BeautyAdapter(Context context, List<Beauty> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_beauty, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Beauty beauty = list.get(position);
        holder.tvTitle.setText(beauty.getTitle());
        holder.tvData.setText(beauty.getData());
        holder.tvTheme.setText(beauty.getTheme());
        holder.tvAuthor.setText(beauty.getAuthor());
        holder.tvComment.setText(beauty.getComment());
        holder.tvDesc.setText(beauty.getDesc());
        UIUtil.setAvatar(beauty.getImgUrl(), holder.cover, 540, 405);
        holder.itemView.setTag(beauty);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        Log.i(" ", "adpter view " + v.toString());
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, ((Beauty) v.getTag()).getTheme());
        }
    }

    // 对外暴露方法
    public void setOnItemClickListener(OnRecycleViewItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvData;
        TextView tvTheme;
        TextView tvAuthor;
        TextView tvDesc;
        TextView tvComment;
        HWRatioImageView cover;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvTheme = (TextView) itemView.findViewById(R.id.tv_theme);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            cover = (HWRatioImageView) itemView.findViewById(R.id.riv_image);
        }
    }

    public static interface OnRecycleViewItemClickListener {
        void onItemClick(View view, String data);
    }

}

