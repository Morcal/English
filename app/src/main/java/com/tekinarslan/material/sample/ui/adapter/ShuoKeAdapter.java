package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Podcasts;
import com.tekinarslan.material.sample.ui.module.community.OnLoadMoreListener;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.weight.CircleImageView;
import com.tekinarslan.material.sample.weight.HWRatioImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/3/19.
 */
public class ShuoKeAdapter extends RecyclerView.Adapter<ShuoKeAdapter.PodcastViewHolder> {
    private Context context;
    public List<Podcasts.PodcastsEntity> list = new ArrayList<>();

    public ShuoKeAdapter(Context context, List<Podcasts.PodcastsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public PodcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_shuoke,parent,false);
        PodcastViewHolder itemViewHolder=new PodcastViewHolder(view);
        return itemViewHolder;    }

    @Override
    public void onBindViewHolder(PodcastViewHolder holder, int position) {
        Podcasts.PodcastsEntity podcast = list.get(position);
        holder.title.setText(podcast.getTitle());
        holder.episodes.setText(podcast.getEpisodesCount() + "节目");
        holder.subscribes.setText(podcast.getSubscribesCount() + "订阅");
        UIUtil.setAvatar(podcast.getBackgroundImage(), holder.avatar);
        UIUtil.setAvatar(podcast.getUser().getAvatar(), holder.cover);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PodcastViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.civ_avatar)
        CircleImageView avatar;
        @Bind(R.id.hw_image)
        HWRatioImageView cover;
        @Bind(R.id.tv_title)
        TextView title;
        @Bind(R.id.tv_episodesCount)
        TextView episodes;
        @Bind(R.id.tv_subscribesCount)
        TextView subscribes;


        public PodcastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            }
        }
}