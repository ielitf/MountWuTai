package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.BuddhistViewpagerAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonFragment;
import com.bupt.mountwutai.ui.activity.service.GovernFragment;
import com.bupt.mountwutai.ui.activity.service.MedicalRescueFragment;
import com.bupt.mountwutai.ui.activity.service.ComplaintFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛事,服务
 */

public class BuddhistFragment extends BaseFragment implements View.OnClickListener {

    private TextView titleTextView;
    private TextView[] xians = new TextView[3];//导航条
    private TextView[] titleTextViews = new TextView[3];//标题
    private String[] buddhisrTitles = {"佛教活动", "佛教知识", "在线礼佛"};//标题内容
    private String[] serviceTitles = {"政府机构", "医疗救援", "投诉建议"};

    private String[] titles = new String[3];

    private ViewPager viewPager;

    private List<Fragment> fragments;//用于存储页面
    private BuddhistViewpagerAdapter viewpagerAdapter;

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
        type=getArguments().getString(CodeConstants.TYPE);
        switch (type) {

            case CodeConstants.BUDDHISTACTION:
                titles = buddhisrTitles;
                fragments = new ArrayList<>();
                fragments.add(new BuddhistActivitiesFragment());
                fragments.add(new BuddhistKnowledgeFragment());
                fragments.add(CommonFragment.newFragment(CodeConstants.BUDDHA_ONLINE));
                break;

            case CodeConstants.SERVICE:
                titles = serviceTitles;
                fragments = new ArrayList<>();
                fragments.add(new GovernFragment());
                fragments.add(new MedicalRescueFragment());
                fragments.add(new ComplaintFragment());
                break;
        }
    }

    /**
     * 为true时，回到父类showPopWindow方法，在子类中重写该方法
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

        titleTextView = (TextView) findViewById(R.id.top_name_text);
        viewPager = (ViewPager) findViewById(R.id.buddhist_viewpager);
        xians[0] = (TextView) findViewById(R.id.buddhist_xian1);
        xians[1] = (TextView) findViewById(R.id.buddhist_xian2);
        xians[2] = (TextView) findViewById(R.id.buddhist_xian3);
        titleTextViews[0] = (TextView) findViewById(R.id.buddhist_na1);
        titleTextViews[1] = (TextView) findViewById(R.id.buddhist_na2);
        titleTextViews[2] = (TextView) findViewById(R.id.buddhist_na3);
        for (int i = 0; i < titleTextViews.length; i++) {
            titleTextViews[i].setText(titles[i]);
            titleTextViews[i].setOnClickListener(this);
        }

        viewpagerAdapter = new BuddhistViewpagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewpagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setXianBack(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buddhist_na1:
                viewPager.setCurrentItem(0);
                setXianBack(0);
                break;

            case R.id.buddhist_na2:
                viewPager.setCurrentItem(1);
                setXianBack(1);
                break;

            case R.id.buddhist_na3:
                viewPager.setCurrentItem(2);
                setXianBack(2);
                break;
        }
    }

    private void setXianBack(int i) {
        titleTextView.setText(titles[i]);
        for (int j = 0; j < xians.length; j++) {
            if (i == j) {
                xians[j].setBackgroundColor(getResources().getColor(R.color.top_back));
            } else {
                xians[j].setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        }
    }
}
