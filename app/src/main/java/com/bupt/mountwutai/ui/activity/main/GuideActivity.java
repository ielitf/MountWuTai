package com.bupt.mountwutai.ui.activity.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.ui.activity.guide.TicketlistActivity;

/**
 * 导游
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout[] relativeLayouts = new RelativeLayout[6];
    private TextView[] textViews = new TextView[6];
    private String[] contents = {MainData.TRAVELPLAN, MainData.TICKETLIST, MainData.HOTLERESVER,
            MainData.WUTAIRECIPES, MainData.TRAFFICGUIDE, MainData.TRAVELSTRATEGY};

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
        textViews[0] = (TextView) findViewById(R.id.travel_guide_text);
        textViews[1] = (TextView) findViewById(R.id.ticket_list_text);
        textViews[2] = (TextView) findViewById(R.id.hotel_reserve_text);
        textViews[3] = (TextView) findViewById(R.id.recipes_text);
        textViews[4] = (TextView) findViewById(R.id.traffic_guide_text);
        textViews[5] = (TextView) findViewById(R.id.travel_strategy_text);
        for (int i = 0; i < relativeLayouts.length; i++) {
            textViews[i].setText(contents[i]);
            textViews[i].setTypeface(Typeface.createFromAsset(getAssets(), "font/li2.ttf"));
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
                bundle.putString(CodeConstants.TYPE, CodeConstants.TRAVEL_PLAN);
                bundle.putString(CodeConstants.ID, MainData.TRAVELPLAN);
                break;

            case R.id.hotel_reserve://酒店预订
                bundle = new Bundle();
                bundle.putString(CodeConstants.TYPE, CodeConstants.HOTLE_RESVER);
                bundle.putString(CodeConstants.ID, MainData.HOTLERESVER);
                break;

            case R.id.recipes://五台食谱
                bundle = new Bundle();
                bundle.putString(CodeConstants.TYPE, CodeConstants.WUTAI_RECIPES);
                bundle.putString(CodeConstants.ID, MainData.WUTAIRECIPES);
                break;

            case R.id.traffic_guide://交通指南
                bundle = new Bundle();
                bundle.putString(CodeConstants.TYPE, CodeConstants.TRAFFIC_GUIDE);
                bundle.putString(CodeConstants.ID, MainData.TRAFFICGUIDE);
                break;

            case R.id.travel_strategy://朝台攻略
                bundle = new Bundle();
                bundle.putString(CodeConstants.TYPE, CodeConstants.TRAVEL_STRATEGY);
                bundle.putString(CodeConstants.ID, MainData.TRAVELSTRATEGY);
                break;
            case R.id.ticket_list://门票一览
                break;
        }
        if (bundle != null) {
            intent2Activity(TravelPlanActivity.class, bundle);
        } else {
            intent2Activity(TicketlistActivity.class);
        }
    }
}
