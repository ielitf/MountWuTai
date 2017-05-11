package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.guide.HotleFragment;
import com.bupt.mountwutai.ui.activity.guide.TravelPlanFragment;

/**
 * 行程规划
 */
public class TravelPlanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_travel_plan);
    }

    private String title;

    TravelPlanFragment travelPlanFragment = null;
    HotleFragment hotleFragment = null;

    @Override
    protected void initView() {
        Bundle extras = getIntent().getExtras();
        String type = extras.getString(CodeConstants.TYPE);
        title = extras.getString(CodeConstants.ID);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (type){
            case CodeConstants.TRAVEL_PLAN:
                travelPlanFragment = new TravelPlanFragment();
                transaction.add(R.id.travel_plan_container, travelPlanFragment);
                break;

            case CodeConstants.HOTLE_RESVER:
                hotleFragment = new HotleFragment();
                transaction.add(R.id.travel_plan_container, hotleFragment);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return title;
    }
}
