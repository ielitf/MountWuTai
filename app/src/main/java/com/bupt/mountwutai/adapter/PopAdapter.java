package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bupt.mountwutai.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/19.
 */

public class PopAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private ArrayList<String> list;



    public PopAdapter(Context context, ArrayList<String> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lv_items, null);
        }
        TextView tv = (TextView)convertView.findViewById(R.id.text);
        tv.setText(list.get(position));

        return convertView;
    }

}
