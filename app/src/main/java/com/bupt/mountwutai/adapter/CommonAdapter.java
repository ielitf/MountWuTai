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

public class CommonAdapter extends android.widget.BaseAdapter {

    private ArrayList<CommonBean> mData;
    private LayoutInflater inflater;

    public CommonAdapter(Context context, ArrayList<CommonBean> mData) {
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
            convertView = inflater.inflate(R.layout.comm_item2, null, false);
            holderView.title = (TextView) convertView.findViewById(R.id.comm_title);
            holderView.content = (TextView) convertView.findViewById(R.id.comm_detail);
            holderView.imageView = (ImageView) convertView.findViewById(R.id.comm_img);
            convertView.setTag(holderView);
        } else {
            holderView = (ViewHolder) convertView.getTag();
        }
        holderView.title.setText(mData.get(position).getTitle());
        holderView.content.setText(mData.get(position).getContent());
        holderView.imageView.setImageResource(mData.get(position).getIcon());
        return convertView;
    }

    class ViewHolder {
        private TextView title, content;
        private ImageView imageView;
    }
}
