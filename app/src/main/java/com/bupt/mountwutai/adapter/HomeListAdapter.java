package com.bupt.mountwutai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.entity.CommonBean;
import com.bupt.mountwutai.ui.activity.main.CommonDetailActivity;
import com.bupt.mountwutai.ui.activity.main.HomeMoreActivity;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mountwutai.widget.NoScrollListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by litf on 2017/5/10.
 */
public class HomeListAdapter extends BaseAdapter {
    private static final String TAG = HomeListAdapter.class.getSimpleName();
    LayoutInflater inflater;
    viewHolder holder;
    private Context mContext;
    private List<ArrayList<CommonBean>> dataLists;
    private List<String> nameList;


    public HomeListAdapter(Context context, List<ArrayList<CommonBean>> lists,
                           List<String> nameList) {
        this.mContext = context;
        this.dataLists = lists;
        this.nameList = nameList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataLists.size();
    }

    @Override
    public Object getItem(int position) {
        return dataLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = null;
        if (convertView == null) {
            holder = new viewHolder();
            convertView = inflater.inflate(R.layout.home_list_item, null, false);
            holder.nameTextView = (TextView) convertView
                    .findViewById(R.id.home_name);
            holder.moreButton = (Button) convertView
                    .findViewById(R.id.home_more);
            holder.listView = (NoScrollListView) convertView
                    .findViewById(R.id.home_listview);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(nameList.get(position));
        CommonAdapter commonAdapter = new CommonAdapter(mContext,dataLists.get(position));
        holder.listView.setAdapter(commonAdapter);
        final List<CommonBean> list = dataLists.get(position);
        holder.listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p,
                                    long id) {
                Intent intent = new Intent();
                intent.setClass(mContext, CommonDetailActivity.class);
                intent.putExtra(CodeConstants.TYPE,CodeConstants.HISTORIC_LEGENDS);
                mContext.startActivity(intent);
            }
        });
        holder.moreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, HomeMoreActivity.class);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class viewHolder {
        TextView nameTextView;
        Button moreButton;
        NoScrollListView listView;
    }

}
