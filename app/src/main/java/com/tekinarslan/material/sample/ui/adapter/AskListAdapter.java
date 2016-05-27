package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.ResultAskList;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/5/27.
 */
public class AskListAdapter extends RecyclerView.Adapter<AskListAdapter.ViewHolder> {
    private Context context;
    public List<ResultAskList.DataEntity> datas = null;

    public AskListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ResultAskList.DataEntity> datas) {
        this.datas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ask, parent, false);
        ViewHolder vh = new ViewHolder(view);
//        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ResultAskList.DataEntity entity = datas.get(position);

        viewHolder.tvContent.setText(entity.getAsk());
        viewHolder.tvTime.setText(entity.getCreate_time());
        viewHolder.tvMessage.setText(entity.getStatus());

        viewHolder.itemView.setTag(entity);
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        TextView tvTime;
        TextView tvMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}
