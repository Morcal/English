package com.tekinarslan.material.sample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tekinarslan.material.sample.bean.Write;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by lyqdhgo on 2016/4/23.
 */
public class WriteListAdapter extends BaseAdapter {
    private Context context;
    private List<Write> list;

    public WriteListAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Write> list) {
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
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
            holder = new ViewHolder();
            holder.writeTitle = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            convertView.getTag();
        }

        String writeTitle = ((Write) getItem(position)).getTitle();
        holder.writeTitle.setText(writeTitle);
        return convertView;
    }

    static class ViewHolder {
        private TextView writeTitle;
    }
}
