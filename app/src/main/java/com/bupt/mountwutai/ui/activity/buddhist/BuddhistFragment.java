package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.BuddhistChildFragment;
import com.bupt.mountwutai.adapter.BuddhistViewpagerAdapter;
import com.bupt.mountwutai.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛事
 */

public class BuddhistFragment extends BaseFragment implements View.OnClickListener{

    private TextView titleTextView;
    private TextView[] xians = new TextView[3];//导航条
    private TextView[] titleTextViews = new TextView[3];//标题
    private String[] titles = {"佛教活动","佛教知识","在线礼佛"};//标题内容
    private ViewPager viewPager;

    private List<Fragment> fragments;//用于存储页面
    private BuddhistViewpagerAdapter viewpagerAdapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_buddhist);
        initView();
    }

    private void initView() {
        titleTextView = (TextView) findViewById(R.id.myButton);
        titleTextView.setText("佛事活动");
        viewPager = (ViewPager) findViewById(R.id.buddhist_viewpager);
        findViewById(R.id.popimg).setVisibility(View.GONE);
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

        fragments = new ArrayList<>();
        fragments.add(new BuddhistChildFragment());
        fragments.add(new BuddhistChildFragment());
        fragments.add(new BuddhistChildFragment());
        viewpagerAdapter = new BuddhistViewpagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewpagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        switch (v.getId()){
            case R.id.buddhist_na1:
                setXianBack(0);
                break;

            case R.id.buddhist_na2:
                setXianBack(1);
                break;

            case R.id.buddhist_na3:
                setXianBack(2);
                break;
        }
    }

    private void setXianBack(int i) {
        for (int j = 0; j < xians.length; j++) {
            if (i == j){
                xians[j].setBackgroundColor(getResources().getColor(R.color.top_back));
            }else {
                xians[j].setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        }
    }
}
