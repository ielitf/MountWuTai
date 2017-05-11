package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.bupt.mountwutai.entity.Consumption;
import com.bupt.mountwutai.widget.CardRecordHolder;

import cn.lemon.view.adapter.BaseViewHolder;
import cn.lemon.view.adapter.RecyclerAdapter;


public  class CardRecordAdapter extends RecyclerAdapter<Consumption> {

    public CardRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<Consumption> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new CardRecordHolder(parent);
    }
}