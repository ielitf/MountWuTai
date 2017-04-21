package com.bupt.mountwutai.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.buddhist.BuddhistFragment;
import com.bupt.mountwutai.ui.activity.guide.GuideFragment;
import com.bupt.mountwutai.ui.activity.summary.SummaryFragment;
import com.bupt.mountwutai.util.LogUtil;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    private TextView[] textViews = new TextView[5];// 图标下面的文字
    private ImageView[] imageButtons = new ImageView[5];// 显示图标

    private FragmentManager fragmentManager;
    private SummaryFragment summaryFragment = null;
    private GuideFragment guideFragment = null;
    private BuddhistFragment buddhistFragment = null,serviceFragment = null;
    private ImageView start_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate");
    }

    public void navigation(View view) {
        int id = view.getId();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        showFragment(transaction, id);
    }

    // 设置导航条的背景颜色
    private void setBack(int x) {
        for (int i = 0; i < textViews.length; i++) {
            int color;
            if (i == x) {
                color = R.color.navigation_select;
            } else {
                color = R.color.navigation_unselect;
            }
            textViews[i].setTextColor(getResources().getColor(
                    color));
        }
    }

    @Override
    protected void initView() {
        start_img= (ImageView) findViewById(R.id.start_img);
        textViews[0] = (TextView) findViewById(R.id.text1);
        textViews[1] = (TextView) findViewById(R.id.text2);
        textViews[2] = (TextView) findViewById(R.id.text3);
        textViews[3] = (TextView) findViewById(R.id.text4);
        textViews[4] = (TextView) findViewById(R.id.text5);
        imageButtons[0] = (ImageView) findViewById(R.id.rb1);
        imageButtons[1] = (ImageView) findViewById(R.id.rb2);
        imageButtons[2] = (ImageView) findViewById(R.id.rb3);
        imageButtons[3] = (ImageView) findViewById(R.id.rb4);
        imageButtons[4] = (ImageView) findViewById(R.id.rb5);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //showFragment(transaction, R.id.summary);
    }

    private void showFragment(FragmentTransaction transaction, int tag) {
        LogUtil.d(TAG, "showFragment is called and tag=" + tag + "transaction==null is " + (transaction == null));
        hideFragments(transaction);
        start_img.setVisibility(View.GONE);

        switch (tag) {
            case R.id.summary://概述
                setBack(0);
                imageButtons[0].setBackgroundResource(R.mipmap.summary_selected);
                imageButtons[1].setBackgroundResource(R.mipmap.guide_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.buddhist_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.specialty_normal);
                imageButtons[4].setBackgroundResource(R.mipmap.service_normal);
                if (summaryFragment == null) {
                    summaryFragment = new SummaryFragment();
                    transaction.add(R.id.container, summaryFragment);
                } else {
                    transaction.show(summaryFragment);
                }
                break;

            case R.id.guide://导游
                setBack(1);
                imageButtons[0].setBackgroundResource(R.mipmap.summary_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.guide_selected);
                imageButtons[2].setBackgroundResource(R.mipmap.buddhist_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.specialty_normal);
                imageButtons[4].setBackgroundResource(R.mipmap.service_normal);
                if (guideFragment == null) {
                    guideFragment = new GuideFragment();
                    transaction.add(R.id.container, guideFragment);
                } else {
                    transaction.show(guideFragment);
                }
                break;

            case R.id.buddhist://佛事
                setBack(2);
                imageButtons[0].setBackgroundResource(R.mipmap.summary_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.guide_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.buddhist_selected);
                imageButtons[3].setBackgroundResource(R.mipmap.specialty_normal);
                imageButtons[4].setBackgroundResource(R.mipmap.service_normal);
                if (buddhistFragment == null) {
                    buddhistFragment = BuddhistFragment.newFragment(CodeConstants.BUDDHISTACTION);
                    transaction.add(R.id.container, buddhistFragment);
                } else {
                    transaction.show(buddhistFragment);
                }
                break;

            case R.id.specialty://土特产
                setBack(3);
                imageButtons[0].setBackgroundResource(R.mipmap.summary_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.guide_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.buddhist_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.specialty_selected);
                imageButtons[4].setBackgroundResource(R.mipmap.service_normal);
                break;

            case R.id.service://服务
                setBack(4);
                imageButtons[0].setBackgroundResource(R.mipmap.summary_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.guide_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.buddhist_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.specialty_normal);
                imageButtons[4].setBackgroundResource(R.mipmap.service_selected);
                if (serviceFragment == null) {
                    serviceFragment = BuddhistFragment.newFragment(CodeConstants.SERVICE);
                    transaction.add(R.id.container, serviceFragment);
                } else {
                    transaction.show(serviceFragment);
                }
                break;

            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        LogUtil.i(TAG, "hideFragments is called");
        if (summaryFragment != null) {
            transaction.hide(summaryFragment);
        }
        if (guideFragment != null) {
            transaction.hide(guideFragment);
        }
        if (buddhistFragment != null) {
            transaction.hide(buddhistFragment);
        }
        if (serviceFragment != null) {
            transaction.hide(serviceFragment);
        }
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }
}
