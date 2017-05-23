package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.mian.AddrInforBean;

import java.util.List;

/**
 *
 */

public class AddrInfoAdapter extends MyBaseAdapter<AddrInforBean>{

    private LayoutInflater inflater;

    public AddrInfoAdapter(Context context, List<AddrInforBean> mData) {
        super(context, mData);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holder = new ViewHolder();
        View convertView = inflater.inflate(R.layout.item_business_addr, null,false);
        holder.imageView= (ImageView) convertView.findViewById(R.id.addr_img);
            holder.nameText = (TextView) convertView.findViewById(R.id.name);
            holder.phoneText= (TextView)convertView.findViewById(R.id.phone);
            holder.addrText = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, AddrInforBean model) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.nameText.setText(model.getTitle());
        holder.phoneText.setText("电话："+model.getPhone());
        holder.addrText.setText("地址："+model.getAddress());
        holder.imageView.setBackgroundResource(model.getIcon());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
         ImageView imageView;
         TextView nameText;
         TextView addrText;
         TextView phoneText;
    }
}
