package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;

public class PartyAdapter extends MyBaseAdapter<CommonBean> {

    private LayoutInflater inflater;

    private boolean isOnline = false;//判断是不是在线礼佛

    public PartyAdapter(Context context, ArrayList<CommonBean> mData) {
        super(context, mData);
        inflater = LayoutInflater.from(context);
    }

    //设置是否为在线礼佛
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.party_item, null, false);
        holderView.content = (TextView) convertView.findViewById(R.id.party_item_content);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.party_item_img);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, CommonBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.content.setText(model.getContent());
        holderView.imageView.setImageResource(model.getIcon());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView  content;
        private ImageView imageView;
    }
}
