package com.bupt.mountwutai.ui.activity.main;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.Switch;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.LocalProductsData;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.customdata.PartyDate1;
import com.bupt.mountwutai.customdata.PartyDate2;
import com.bupt.mountwutai.customdata.PartyDate3;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;
import java.util.List;

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
        switch (getIntent().getStringExtra(CodeConstants.ID)) {
            case MainData.party1construction1:
                for (int i = 0; i < PartyDate1.party_icon.length; i++) {
                    mData.add(new CommonBean(PartyDate1.party_icon[i], PartyDate1.party_title[i], PartyDate1.party_content[i]));
                }
                break;
            case MainData.party2construction2:
                for (int i = 0; i < PartyDate2.party2_icon.length; i++) {
                    mData.add(new CommonBean(PartyDate2.party2_icon[i], PartyDate2.party2_title[i], PartyDate2.party2_content[i]));
                }
                break;
            case MainData.party1:
                for (int i = 0; i < PartyDate3.party3_icon1.length; i++) {
                    mData.add(new CommonBean(PartyDate3.party3_icon1[i], PartyDate3.party3_title1[i], PartyDate3.party3_content1[i]));
                }
                break;
            case MainData.party2:
                for (int i = 0; i < PartyDate3.party3_icon2.length; i++) {
                    mData.add(new CommonBean(PartyDate3.party3_icon2[i], PartyDate3.party3_title2[i], PartyDate3.party3_content2[i]));
                }
                break;
            case MainData.party3:
                for (int i = 0; i < PartyDate3.party3_icon3.length; i++) {
                    mData.add(new CommonBean(PartyDate3.party3_icon3[i], PartyDate3.party3_title3[i], PartyDate3.party3_content3[i]));
                }
                break;
            case MainData.party4:
                for (int i = 0; i < PartyDate3.party3_icon4.length; i++) {
                    mData.add(new CommonBean(PartyDate3.party3_icon4[i], PartyDate3.party3_title4[i], PartyDate3.party3_content4[i]));
                }
                break;
            case MainData.party5:
                for (int i = 0; i < PartyDate1.party_icon.length; i++) {
                    mData.add(new CommonBean(PartyDate1.party_icon[i], PartyDate1.party_title[i], PartyDate1.party_content[i]));
                }
                break;
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
