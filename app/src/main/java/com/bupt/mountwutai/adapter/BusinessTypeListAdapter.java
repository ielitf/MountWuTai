package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.mian.BusinessTypeListBean;

import java.util.List;

/**
 * Created by Wyf on 2017/5/23.
 */

public class BusinessTypeListAdapter extends MyBaseAdapter<BusinessTypeListBean> {

    private LayoutInflater inflater;

    public BusinessTypeListAdapter(Context context, List<BusinessTypeListBean> mData) {
        super(context, mData);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holder = new ViewHolder();
        View convertView = inflater.inflate(R.layout.item_businesstype_list,null,false);
        holder.titleImage = (ImageView) convertView.findViewById(R.id.comm_image);
        holder.title = (TextView) convertView.findViewById(R.id.comm_title);
        holder.brief = (TextView) convertView.findViewById(R.id.comm_brief);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, BusinessTypeListBean model) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(model.getTitle());
        holder.titleImage.setBackgroundResource(model.getIcon());
        holder.brief.setVisibility(View.GONE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView titleImage;
        TextView title;
        TextView brief;
    }
}
