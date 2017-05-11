package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.util.ToastUtil;

/**
 * 导游
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout[] relativeLayouts = new RelativeLayout[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initView() {
        relativeLayouts[0] = (RelativeLayout) findViewById(R.id.travel_guide);
        relativeLayouts[1] = (RelativeLayout) findViewById(R.id.ticket_list);
        relativeLayouts[2] = (RelativeLayout) findViewById(R.id.hotel_reserve);
        relativeLayouts[3] = (RelativeLayout) findViewById(R.id.recipes);
        relativeLayouts[4] = (RelativeLayout) findViewById(R.id.traffic_guide);
        relativeLayouts[5] = (RelativeLayout) findViewById(R.id.travel_strategy);
        for (int i = 0; i < relativeLayouts.length; i++) {
            relativeLayouts[i].setOnClickListener(this);
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return MainData.guide;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = null;
        switch (v.getId()) {
            case R.id.travel_guide://行程规划
                bundle = new Bundle();
                bundle.putString(CodeConstants.TYPE,CodeConstants.TRAVEL_PLAN);
                bundle.putString(CodeConstants.ID,"行程规划");
                break;

            case R.id.ticket_list://门票一览
                ToastUtil.show(this, "门票一览");
                break;

            case R.id.hotel_reserve://酒店预订
                bundle = new Bundle();
                bundle.putString(CodeConstants.TYPE,CodeConstants.HOTLE_RESVER);
                bundle.putString(CodeConstants.ID,"酒店预订");
                break;

            case R.id.recipes://五台食谱
                ToastUtil.show(this, "五台食谱");
                break;

            case R.id.traffic_guide://交通指南
                ToastUtil.show(this, "交通指南");
                break;

            case R.id.travel_strategy://朝台攻略
                ToastUtil.show(this, "朝台攻略");
                break;
        }
        intent2Activity(TravelPlanActivity.class, bundle);
    }
}
