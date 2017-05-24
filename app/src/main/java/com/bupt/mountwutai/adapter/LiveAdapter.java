package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.Consumption;

import java.util.List;

/**
 * 电视直播
 */

public class LiveAdapter extends MyBaseAdapter<Consumption> {

    private LayoutInflater inflater;

    public LiveAdapter(Context context, List<Consumption> mData) {
        super(context, mData);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holder = new ViewHolder();
        View view = inflater.inflate(R.layout.live_item, null, false);
        holder.imageView = (ImageView) view.findViewById(R.id.live_img);
        holder.nameText = (TextView) view.findViewById(R.id.live_text_desc);
        view.setTag(holder);
        return view;
    }

    @Override
    protected void bindView(Context context, View view, int position, Consumption model) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.imageView.setBackgroundResource(model.getLivepic());
        holder.nameText.setText(model.getLivetext());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView nameText;
    }
}
