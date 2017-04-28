package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.HotleBean;

import java.util.ArrayList;

/**
 * 酒店listview的适配器
 */

public class HotleAdapter extends MyBaseAdapter<HotleBean> {
    private LayoutInflater inflater;

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.hotel_item, null, false);
        holderView.title = (TextView) convertView.findViewById(R.id.hotle_title);
        holderView.content = (TextView) convertView.findViewById(R.id.hotle_detail);
        holderView.address = (TextView) convertView.findViewById(R.id.hotle_address);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.hotle_img);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, HotleBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.title.setText(model.getTitle());
        holderView.content.setText(model.getContent());
        holderView.address.setText(model.getAddress());
        holderView.imageView.setImageResource(model.getIcon());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView title, content, address;
        private ImageView imageView;
    }

    public HotleAdapter(Context context, ArrayList<HotleBean> mData) {
        super(context, mData);
        this.inflater = LayoutInflater.from(context);
    }

}
