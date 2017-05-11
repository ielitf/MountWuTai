package com.bupt.mountwutai.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.MyBaseAdapter;
import com.bupt.mountwutai.base.BasePanel;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.entity.mian.CustomBean;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mountwutai.widget.NoScrollGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 九宫格
 * Created by Wyf on 2017/5/10.
 */

public class HeaderPanel extends BasePanel {
    List<CustomBean> customlist=new ArrayList<>();
    private NoScrollGridView noScrollGrideView;
    HeaderPanelAdapter adapter;

    public HeaderPanel(final Context context) {
        super(context);
        setContentView(R.layout.main_header_gride);
        noScrollGrideView = (NoScrollGridView) findViewById(R.id.main_header_gridId);
        List<CustomBean> datas = new ArrayList<>();
        adapter = new HeaderPanelAdapter(context, datas);

        noScrollGrideView.setAdapter(adapter);
        noScrollGrideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == adapter.getCount() - 1) {
                    Intent intent = new Intent(context, CustomActivity.class);
                    intent.putExtra("customdate", (Serializable) customlist);
                    context.startActivity(intent);
                } else {
                    switch (adapter.getList().get(position).getTitle()) {
                        case MainData.summary:
                            context.startActivity(new Intent(context, SummaryActivity.class));
                            break;

                        case MainData.guide:
                            context.startActivity(new Intent(context, GuideActivity.class));
                            break;

                        case MainData.buddhist:
                            context.startActivity(new Intent(context, BuddhistActivity.class));
                            break;

                        default:
                            ToastUtil.show(context, adapter.getList().get(position).getTitle());
                            break;
                    }
                }
            }
        });
    }

    /**
     * 设置展示数据
     *
     * @param infoList List<CustomBean>
     */
    public void setData(List<CustomBean> infoList) {
        //跳转传递的数据
        //本页面显示数据
        for (int i = 0; i < infoList.size(); i++) {
            customlist.add(infoList.get(i));
            if (!infoList.get(i).getIsadd()) {
                infoList.remove(i);
                i--;
            }
        }
        adapter.clear();
        adapter.addCollection(infoList);
        adapter.notifyDataSetChanged();
    }

    private class HeaderPanelAdapter extends MyBaseAdapter<CustomBean> {

        public HeaderPanelAdapter(Context context, List<CustomBean> mData) {
            super(context, mData);
        }

        @Override
        protected View newView(Context context, int position, ViewGroup parentView) {
            View view = LayoutInflater.from(context).inflate(R.layout.main_header_grid_item, null, false);
            ItemHolder holder = new ItemHolder();
            holder.title = findViewById(view, R.id.title);
            holder.image = findViewById(view, R.id.show);
            holder.item = findViewById(view, R.id.griditem);
            view.setTag(holder);
            return view;
        }

        @Override
        protected void bindView(final Context context, View view, int position, final CustomBean model) {
            ItemHolder holder = (ItemHolder) view.getTag();
            if (position == getCount() - 1) {
                holder.image.setBackgroundResource(R.mipmap.ic_channel_add);
                holder.title.setText("自定义");
            } else {
                holder.image.setBackgroundResource(model.getPicture());
                holder.title.setText(model.getTitle());
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
