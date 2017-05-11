package com.bupt.mountwutai.ui.activity.buddhist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.BuddhismActivityAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.customdata.BuddhismData;
import com.bupt.mountwutai.entity.mian.BuddhismActivityBean;
import java.util.ArrayList;

/**
 * 佛事活动
 */

public class BuddhistActivitiesFragment extends BaseFragment {
    private Context context;
    private ListView listView;
    private ArrayList<BuddhismActivityBean> mData;
    private BuddhismActivityAdapter adapter;
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_buddhist_activities);
        context = getActivity();
        listView = (ListView) findViewById(R.id.common_list);
        addDatas();
        adapter = new BuddhismActivityAdapter(context,mData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void addDatas() {
        mData = new ArrayList<>();
        for (int i = 0; i < BuddhismData.buddhism_activity_icon.length; i++) {
            mData.add(new BuddhismActivityBean(BuddhismData.buddhism_activity_icon[i], BuddhismData.buddhism_activity_title[i], BuddhismData.buddhism_activity_isBegin[i]));
        }
    }

    @Override
    protected boolean hasPopWindow() {
        return false;
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }
}
