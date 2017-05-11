package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class BuddhismActivity2 extends BaseActivity {
    private TextView titleTextView;
    private String[] buddhisrTitles = {"佛事活动", "佛教知识", "在线礼佛"};//标题内容
    private String[] serviceTitles = {"政府机构", "医疗救援", "投诉建议"};

    private String[] titles = new String[3];
    private String type;
    private int buddhist_action_type;
    private ViewGroup tab;//导航条
    private ViewPager viewPager;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_buddhist2);
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(CodeConstants.TYPE);
        buddhist_action_type = getIntent().getIntExtra(CodeConstants.BUDDHISTACTIONTYPE,1);
        tab = (ViewGroup) findViewById(R.id.tab);
        titleTextView = (TextView) findViewById(R.id.top_name_text);
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
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPagerTab.setViewPager(viewPager);
        switch (buddhist_action_type){
            case 1:
                viewPager.setCurrentItem(0,true);
                break;
            case 2:
                viewPager.setCurrentItem(1,true);
                break;
            case 3:
                viewPager.setCurrentItem(2,true);
                break;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleTextView.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "佛事";
    }
}
