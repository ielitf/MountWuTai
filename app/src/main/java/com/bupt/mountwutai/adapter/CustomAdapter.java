package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.CustomBean;

import java.util.ArrayList;

public class CustomAdapter extends MyBaseAdapter<CustomBean> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<CustomBean> mData) {
        super(context, mData);
        Log.i("xas","csad0"+mData.toString());
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        Log.i("xas","csad1");
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.custom_item, null, false);
        holderView.custom_title = (TextView) convertView.findViewById(R.id.custom_title);
        holderView.custom_imageView = (ImageView) convertView.findViewById(R.id.custom_img);
        holderView.custom_button= (Button) convertView.findViewById(R.id.custom_button);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(Context context, View view, int position, CustomBean model) {
        Log.i("xas","csad2");
        ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.custom_title.setText(model.getTitle());
        holderView.custom_imageView.setImageResource(model.getPicture());
        if (model.getIsadd()){
            holderView.custom_button.setText("关闭");
        }else{
            holderView.custom_button.setText("开启");
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        private TextView custom_title;
        private ImageView custom_imageView;
        private Button custom_button;
    }
}
