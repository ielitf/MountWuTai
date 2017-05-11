package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.HotleBean;
import com.bupt.mountwutai.entity.mian.BuddhismActivityBean;

import java.util.ArrayList;

/**
 * 佛事活动
 * Created by Administrator on 2017/5/10.
 */

public class BuddhismActivityAdapter extends MyBaseAdapter<BuddhismActivityBean> {
    private LayoutInflater inflater;
    public BuddhismActivityAdapter(Context context, ArrayList<BuddhismActivityBean> mData) {
        super(context, mData);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.comm_item3, null, false);
        holderView.title = (TextView) convertView.findViewById(R.id.comm_title);
        holderView.isBegin = (TextView) convertView.findViewById(R.id.start_or_not);
        holderView.imageView = (ImageView) convertView.findViewById(R.id.comm_img);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, BuddhismActivityBean model) {
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.title.setText(model.getTitle());
        holderView.imageView.setImageResource(model.getIcon());
        if (!model.isBegin()){
            holderView.isBegin.setText("即将开始");
        }else{
            holderView.isBegin.setText("已结束");
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView title, isBegin;
        private ImageView imageView;
    }
}
