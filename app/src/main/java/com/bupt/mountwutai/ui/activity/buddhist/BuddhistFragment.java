package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonFragment;
import com.bupt.mountwutai.ui.activity.service.GovernFragment;
import com.bupt.mountwutai.ui.activity.service.MedicalRescueFragment;
import com.bupt.mountwutai.ui.activity.service.ComplaintFragment;
import com.bupt.mylibrary.SmartTab.SmartTabLayout;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItemAdapter;
import com.bupt.mylibrary.SmartTab.UtilsV4.v4.FragmentPagerItems;

/**
 * 佛事,服务
 */

public class BuddhistFragment extends BaseFragment {

    private TextView titleTextView;
    private String[] buddhisrTitles = {"佛教活动", "佛教知识", "在线礼佛"};//标题内容
    private String[] serviceTitles = {"政府机构", "医疗救援", "投诉建议"};

    private String[] titles = new String[3];

    ViewGroup tab;//导航条
    private ViewPager viewPager;

    public BuddhistFragment() {
    }

    public static BuddhistFragment newFragment(String type) {
        BuddhistFragment fragment = new BuddhistFragment();
        Bundle args = new Bundle();
        args.putString(CodeConstants.TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    String type;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_buddhist);
        initView();
    }

    @Override
    protected void initArgs() {
        super.initArgs();
        type = getArguments().getString(CodeConstants.TYPE);
    }

    /**
     * 为true时，回到父类showPopWindow方法，在子类中重写该方法
     *
     * @return
     */
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
        return titles[0];
    }

    private void initView() {
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
                getChildFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPagerTab.setViewPager(viewPager);
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

}
