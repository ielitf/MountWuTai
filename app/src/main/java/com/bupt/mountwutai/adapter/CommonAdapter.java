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

public class CommonAdapter extends MyBaseAdapter<CommonBean> {

    private LayoutInflater inflater;
    private boolean isOnline = false;//判断是不是在线礼佛

    public CommonAdapter(Context context, ArrayList<CommonBean> mData) {
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
        View convertView = inflater.inflate(R.layout.comm_item2, null, false);
        holderView.title = (TextView) convertView.findViewById(R.id.comm_title);
        holderView.content = (TextView) convertView.findViewById(R.id.comm_detail);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.comm_img);
        holderView.price = (TextView) convertView.findViewById(R.id.comm_price);
        holderView.more = (TextView) convertView.findViewById(R.id.comm_more);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, CommonBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        if (isOnline) {
            holderView.more.setVisibility(View.GONE);
            holderView.price.setVisibility(View.VISIBLE);
            holderView.price.setText("￥" + model.getPrice());
        }
        holderView.title.setText(model.getTitle());
        holderView.content.setText(model.getContent());
        holderView.imageView.setImageResource(model.getIcon());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView title, content, price, more;
        private ImageView imageView;
    }
}
