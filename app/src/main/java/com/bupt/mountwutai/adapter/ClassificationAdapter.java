package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.entity.classification.ClassificationBean;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mountwutai.widget.NoScrollGridView;

import java.util.Collection;

/**
 * Created by Wyf on 2017/5/12.
 */

public class ClassificationAdapter extends MyBaseAdapter<ClassificationBean> {

    LayoutInflater inflater;

    public ClassificationAdapter(Context context, Collection<ClassificationBean> collection) {
        super(context, collection);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected View newView(Context context, int position, ViewGroup parentView) {
        ViewHolder viewHolder = new ViewHolder();
        View view = inflater.inflate(R.layout.class_listview_item, null, false);
        viewHolder.titleTextView = (TextView) view.findViewById(R.id.class_item_title);
        viewHolder.noScrollGridView = (NoScrollGridView) view.findViewById(R.id.class_item_gridview);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected void bindView(final Context context, View view, int position, final ClassificationBean model) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        PoliticsAdapter adapter = new PoliticsAdapter(context, model.getBeanList());
        viewHolder.noScrollGridView.setAdapter(adapter);
        viewHolder.noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show(context, model.getBeanList().get(position).getTitle());
            }
        });
        viewHolder.titleTextView.setText(model.getTitle());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        private TextView titleTextView;
        private NoScrollGridView noScrollGridView;
    }
}
