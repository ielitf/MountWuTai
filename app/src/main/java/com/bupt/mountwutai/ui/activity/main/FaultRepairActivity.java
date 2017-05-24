package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.service.ComplaintFragment;
import com.bupt.mountwutai.ui.activity.service.GovernFragment;
import com.bupt.mountwutai.ui.activity.service.MedicalRescueFragment;
import com.bupt.mylibrary.SmartTab.SmartTabLayout;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItemAdapter;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;



public class FaultRepairActivity extends BaseActivity {

    private String[] titles = {"常规保修","快速报修"};//标题内容
    ViewPager viewPager;
    private ViewGroup tab;//导航条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_fault_repair);
    }

    @Override
    protected void initView() {
        tab = (ViewGroup) findViewById(R.id.repair_tab);
        viewPager = (ViewPager) findViewById(R.id.repair_viewpager);
        tab.addView(LayoutInflater.from(activity).inflate(R.layout.tab_top_layout, tab, false));
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(activity);

        pages.add(FragmentPagerItem.of(titles[0], CommonFaultRepairFragment.class));
        pages.add(FragmentPagerItem.of(titles[1], FastFaultRepairFragment.class));
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "故障报修";
    }
}
