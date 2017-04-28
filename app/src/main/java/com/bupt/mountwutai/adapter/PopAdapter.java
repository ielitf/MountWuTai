package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bupt.mountwutai.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/19.
 */

public class PopAdapter extends MyBaseAdapter<String> {

    private LayoutInflater inflater;

    public PopAdapter(Context context, ArrayList<String> list) {
        super(context,list);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holder = new ViewHolder();
        View view = inflater.inflate(R.layout.lv_items, null);
        holder.show = (TextView) view.findViewById(R.id.text);
        view.setTag(holder);
        return view;
    }

    @Override
    protected void bindView(Context context, View view, int position, String model) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.show.setText(model);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        private TextView show;
    }

}
