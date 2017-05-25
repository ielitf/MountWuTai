package com.bupt.mountwutai.ui.activity.main;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.LiveAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.entity.Consumption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wyf on 2017/5/24.
 */

public class LiveContentActivity extends BaseActivity {

    private ListView listView;
    LiveAdapter adapter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_addr_info);
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.addr_info_listview);
        List<Consumption> consumptions = new ArrayList<>();
        consumptions.add(new Consumption(R.mipmap.logo, "五台山一套"));
        consumptions.add(new Consumption(R.mipmap.logo, "五台山二套"));
        adapter = new LiveAdapter(LiveContentActivity.this, consumptions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent2Activity(LivingActivity.class);
            }
        });
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "电视直播";
    }
}
