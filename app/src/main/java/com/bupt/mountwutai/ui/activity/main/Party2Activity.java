package com.bupt.mountwutai.ui.activity.main;


import android.os.Bundle;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.LocalProductsData;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;

public class Party2Activity extends BaseActivity {

    private ArrayList<CommonBean> mData;
    private ListView listView;
    private CommonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_party2);
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.party_list);
        addData();
        adapter = new CommonAdapter(activity, mData);
        listView.setAdapter(adapter);

    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < LocalProductsData.product_icon.length; i++) {
            mData.add(new CommonBean(LocalProductsData.product_icon[i], LocalProductsData.product_title[i], LocalProductsData.product_content[i]));
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return getIntent().getStringExtra(CodeConstants.ID);
    }
}
