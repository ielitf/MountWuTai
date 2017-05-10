package com.bupt.mountwutai.ui.activity.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.MyBaseAdapter;
import com.bupt.mountwutai.base.BasePanel;
import com.bupt.mountwutai.entity.CommonBean;
import com.bupt.mountwutai.entity.mian.SlidesEntity;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mountwutai.widget.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 九宫格
 * Created by Wyf on 2017/5/10.
 */

public class HeaderPanel extends BasePanel {

    private NoScrollGridView noScrollGrideView;
    HeaderPanelAdapter adapter;

    public HeaderPanel(Context context) {
        super(context);
        setContentView(R.layout.main_header_gride);
        noScrollGrideView = (NoScrollGridView) findViewById(R.id.main_header_gridId);
        List<SlidesEntity> datas = new ArrayList<>();
        adapter = new HeaderPanelAdapter(context,datas);

        noScrollGrideView.setAdapter(adapter);
    }

    /**
     * 设置展示数据
     *
     * @param infoList List<SlidesEntity>
     */
    public void setData(List<SlidesEntity> infoList) {
        adapter.addCollection(infoList);
        adapter.notifyDataSetChanged();
    }

    private class HeaderPanelAdapter extends MyBaseAdapter<SlidesEntity> {

        public HeaderPanelAdapter(Context context, List<SlidesEntity> mData) {
            super(context, mData);
        }

        @Override
        protected View newView(Context context, int position, ViewGroup parentView) {
            View view = LayoutInflater.from(context).inflate(R.layout.main_header_grid_item, null);
            ItemHolder holder = new ItemHolder();
            holder.title = findViewById(view, R.id.title);
            holder.image = findViewById(view, R.id.show);
            holder.item = findViewById(view, R.id.griditem);
            view.setTag(holder);
            return view;
        }

        @Override
        protected void bindView(final Context context, View view, int position, final SlidesEntity model) {
            ItemHolder holder = (ItemHolder) view.getTag();
            if (position == getCount() - 1) {
                holder.image.setBackgroundResource(R.mipmap.ic_channel_add);
                holder.title.setText("自定义");
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.show(context,"next");
                    }
                });
            } else {
                holder.image.setBackgroundResource(R.mipmap.ic_launcher_round);
                holder.title.setText(model.getDesc());
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.show(context,model.getDesc());
                    }
                });
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return super.getCount() + 1;
        }

        class ItemHolder {
            ImageView image;
            TextView title;
            RelativeLayout item;
        }
    }

}
