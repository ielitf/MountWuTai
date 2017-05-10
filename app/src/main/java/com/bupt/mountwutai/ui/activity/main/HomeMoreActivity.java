package com.bupt.mountwutai.ui.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.LocalProductsData;
import com.bupt.mountwutai.customdata.OnlineData;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.customdata.WutaiData;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;

public class HomeMoreActivity extends BaseActivity {
    private ArrayList<CommonBean> mData;
    private ListView listView;
    private CommonAdapter adapter;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_home_more);
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.common_list);
        addData();
        adapter = new CommonAdapter(activity, mData);
        listView.setAdapter(adapter);
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return "推荐";
    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < SummaryData.temple_icon.length; i++) {
            mData.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i], SummaryData.temple_content[i]));
        }
    }
}
