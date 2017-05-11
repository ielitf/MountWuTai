package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.mian.PoliticsBean;

import java.util.List;

/**
 * 九宫格适配器
 * Created by Wyf on 2017/5/11.
 */

public class PoliticsAdapter extends MyBaseAdapter<PoliticsBean> {

    public PoliticsAdapter(Context context, List<PoliticsBean> mData) {
        super(context, mData);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_header_grid_item, null, false);
        ItemHolder holder = new ItemHolder();
        holder.title = findViewById(view, R.id.title);
        holder.image = findViewById(view, R.id.show);
        holder.item = findViewById(view, R.id.griditem);
        view.setTag(holder);
        return view;
    }

    @Override
    protected void bindView(final Context context, View view, int position, final PoliticsBean model) {
        ItemHolder holder = (ItemHolder) view.getTag();
        holder.image.setBackgroundResource(model.getPicture());
        holder.title.setText(model.getTitle());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    class ItemHolder {
        ImageView image;
        TextView title;
        RelativeLayout item;
    }
}
