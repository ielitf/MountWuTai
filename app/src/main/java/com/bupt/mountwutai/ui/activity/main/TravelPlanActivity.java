package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonFragment;
import com.bupt.mountwutai.ui.activity.guide.HotleFragment;
import com.bupt.mountwutai.ui.activity.guide.TrafficGuideFragment;
import com.bupt.mountwutai.ui.activity.guide.TravelPlanFragment;
import com.bupt.mountwutai.ui.activity.guide.TravelStrategyFragment;

/**
 * 行程规划等页面
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

    private TravelStrategyFragment travelStrategyFragment = null;
    private TrafficGuideFragment trafficGuideFragment = null;
    private TravelPlanFragment travelPlanFragment = null;
    private HotleFragment hotleFragment = null;
    private CommonFragment commonFragment = null;//五台食谱
    private CommonFragment localFragment = null;//土特产

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

            case CodeConstants.WUTAI_RECIPES:
                commonFragment = CommonFragment.newFragment(CodeConstants.WUTAI_RECIPES);
                transaction.add(R.id.travel_plan_container, commonFragment);
                break;

            case CodeConstants.TRAFFIC_GUIDE:
                trafficGuideFragment = new TrafficGuideFragment();
                transaction.add(R.id.travel_plan_container, trafficGuideFragment);
                break;

            case CodeConstants.TRAVEL_STRATEGY:
                travelStrategyFragment = new TravelStrategyFragment();
                transaction.add(R.id.travel_plan_container, travelStrategyFragment);
                break;

            case CodeConstants.LOCAL_PRODUCTS:
                localFragment = CommonFragment.newFragment(CodeConstants.LOCAL_PRODUCTS);
                transaction.add(R.id.travel_plan_container, localFragment);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //travelPlanFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
         //getSupportFragmentManager().getFragments().get(0).onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
