package com.bupt.mountwutai.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.Consumption;
import com.bupt.mountwutai.ui.activity.MainActivity;
import com.bupt.mountwutai.ui.activity.main.LivingActivity;
import com.bupt.mountwutai.util.ToastUtil;

import cn.lemon.view.adapter.BaseViewHolder;


public class CardRecordHolder extends BaseViewHolder<Consumption> {

    private ImageView live_img;
    private TextView live_text;
    private Context context;

    public CardRecordHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.holder_consume);
        this.context=context;
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
        Intent intent=new Intent(context, LivingActivity.class);
        intent.putExtra("livingtitle",object.getLivetext());
        context.startActivity(intent);
    }
}