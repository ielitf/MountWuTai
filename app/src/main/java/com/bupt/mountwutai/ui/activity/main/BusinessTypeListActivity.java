package com.bupt.mountwutai.ui.activity.main;

import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.BusinessTypeListAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.BroadcastingCenterDate;
import com.bupt.mountwutai.entity.mian.BusinessTypeListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 电视业务
 */

public class BusinessTypeListActivity extends BaseActivity {

    private ListView businessTypeListView;
    private BusinessTypeListAdapter adapter;
    private int[] icons = {R.mipmap.buiness_tv1, R.mipmap.buiness_tv2, R.mipmap.buiness_tv3};

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_addr_info);
    }

    @Override
    protected void initView() {
        businessTypeListView = (ListView) findViewById(R.id.addr_info_listview);
        List<BusinessTypeListBean> beanList = new Gson().fromJson(BroadcastingCenterDate.tv_service, new TypeToken<List<BusinessTypeListBean>>() {
        }.getType());
        for (int i = 0; i < beanList.size(); i++) {
            beanList.get(i).setIcon(icons[i]);
        }
        adapter = new BusinessTypeListAdapter(BusinessTypeListActivity.this, beanList);
        businessTypeListView.setAdapter(adapter);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "电视业务";
    }
}
