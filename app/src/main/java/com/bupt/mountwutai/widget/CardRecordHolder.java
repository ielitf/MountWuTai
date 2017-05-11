package com.bupt.mountwutai.widget;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.Consumption;

import cn.lemon.view.adapter.BaseViewHolder;


public class CardRecordHolder extends BaseViewHolder<Consumption> {

    private ImageView live_img;
    private TextView live_text;

    public CardRecordHolder(ViewGroup parent) {
        super(parent, R.layout.holder_consume);
    }

    @Override
    public void setData(final Consumption object) {
        super.setData(object);
        live_img.setImageResource(object.getLivepic());
        live_text.setText(object.getLivetext());
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        live_img = findViewById(R.id.live_pic);
        live_text = findViewById(R.id.live_text);
    }

    @Override
    public void onItemViewClick(Consumption object) {
        super.onItemViewClick(object);
        //点击事件

    }
}