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
import com.bupt.mountwutai.entity.mian.CustomBean;
import com.bupt.mylibrary.utils.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomAdapter extends MyBaseAdapter<CustomBean> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<CustomBean> mData) {
        super(context, mData);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder holderView = new ViewHolder();
        View convertView = inflater.inflate(R.layout.custom_item, null, false);
        holderView.custom_title = (TextView) convertView.findViewById(R.id.custom_title);
        holderView.custom_imageView = (ImageView) convertView.findViewById(R.id.custom_img);
        holderView.custom_button= (Button) convertView.findViewById(R.id.custom_button);
        convertView.setTag(holderView);
        return convertView;
    }

    @Override
    protected void bindView(final Context context, View view, final int position, final CustomBean model) {
        final ViewHolder holderView = (ViewHolder) view.getTag();
        holderView.custom_title.setText(model.getTitle());
        holderView.custom_imageView.setImageResource(model.getPicture());
        if (model.getIsadd()){
            holderView.custom_button.setText("关闭");
        }else{
            holderView.custom_button.setText("开启");
        }
        holderView.custom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getIsadd()){
                    holderView.custom_button.setText("开启");
                    model.setIsadd(false);
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("title",model.getTitle());
                        jsonObject.put("isadd",false);
                        jsonObject.put("picture",model.getPicture());
                        ViewUtils.setData(context,position+"",jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    holderView.custom_button.setText("关闭");
                    model.setIsadd(true);
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("title",model.getTitle());
                        jsonObject.put("isadd",true);
                        jsonObject.put("picture",model.getPicture());
                        ViewUtils.setData(context,position+"",jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

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
