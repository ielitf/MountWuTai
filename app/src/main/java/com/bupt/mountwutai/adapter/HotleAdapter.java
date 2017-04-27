package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.HotleBean;

import java.util.ArrayList;

/**
 * 酒店listview的适配器
 */

public class HotleAdapter extends BaseAdapter {
    private ArrayList<HotleBean> mData;
    private LayoutInflater inflater;

    public HotleAdapter(Context context, ArrayList<HotleBean> mData) {
        this.mData = mData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holderView;
        if (convertView == null) {
            holderView = new ViewHolder();
            convertView = inflater.inflate(R.layout.hotel_item, null, false);
            holderView.title = (TextView) convertView.findViewById(R.id.hotle_title);
            holderView.content = (TextView) convertView.findViewById(R.id.hotle_detail);
            holderView.address = (TextView) convertView.findViewById(R.id.hotle_address);
            holderView.imageView = (ImageView) convertView.findViewById(R.id.hotle_img);
            convertView.setTag(holderView);
        } else {
            holderView = (ViewHolder) convertView.getTag();
        }
        holderView.title.setText(mData.get(position).getTitle());
        holderView.content.setText(mData.get(position).getContent());
        holderView.address.setText(mData.get(position).getAddress());
        holderView.imageView.setImageResource(mData.get(position).getIcon());
        return convertView;
    }

    class ViewHolder {
        private TextView title, content,address;
        private ImageView imageView;
    }
}
