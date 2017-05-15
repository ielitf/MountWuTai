package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonFragment;
import com.bupt.mountwutai.ui.activity.service.ComplaintFragment;
import com.bupt.mountwutai.ui.activity.service.GovernFragment;
import com.bupt.mountwutai.ui.activity.service.MedicalRescueFragment;
import com.bupt.mylibrary.SmartTab.SmartTabLayout;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItemAdapter;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItems;

public class BuddhistActivity extends BaseActivity {
    private String[] buddhisrTitles = {"佛事活动", "佛教知识", "在线礼佛"};//标题内容
    private String[] serviceTitles = {"政府机构", "医疗救援", "投诉建议"};

    private String[] titles = new String[3];
    private String type;
    private ViewGroup tab;//导航条
    private ViewPager viewPager;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_buddhist);
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(CodeConstants.TYPE);
        tab = (ViewGroup) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.buddhist_viewpager);
        tab.addView(LayoutInflater.from(activity).inflate(R.layout.tab_top_layout, tab, false));

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(activity);
        switch (type) {
            case CodeConstants.BUDDHISTACTION:
                titles = buddhisrTitles;
                pages.add(FragmentPagerItem.of(titles[0], BuddhistActivitiesFragment.class));
                pages.add(FragmentPagerItem.of(titles[1], BuddhistKnowledgeFragment.class));
                Bundle args = new Bundle();
                args.putString(CodeConstants.TYPE, CodeConstants.BUDDHA_ONLINE);
                pages.add(FragmentPagerItem.of(titles[2], CommonFragment.class, args));
                break;

            case CodeConstants.SERVICE:
                titles = serviceTitles;
                pages.add(FragmentPagerItem.of(titles[0], GovernFragment.class));
                pages.add(FragmentPagerItem.of(titles[1], MedicalRescueFragment.class));
                pages.add(FragmentPagerItem.of(titles[2], ComplaintFragment.class));
                break;
            case CodeConstants.SERVICE2:
                titles = serviceTitles;
                pages.add(FragmentPagerItem.of(titles[0], GovernFragment.class));
                pages.add(FragmentPagerItem.of(titles[1], MedicalRescueFragment.class));
                pages.add(FragmentPagerItem.of(titles[2], ComplaintFragment.class));
                break;
        }
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        if (type.equals(CodeConstants.SERVICE2)) {
            viewPager.setCurrentItem(1);
        }
        viewPager.setOffscreenPageLimit(3);

        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        if (type.equals(CodeConstants.SERVICE)) {
            return "服务";
        }else if(type.equals(CodeConstants.SERVICE2)){
            return "服务";
        }
        return "佛事";
    }
}
