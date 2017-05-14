package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.MainData;


/**
 * 门票一览
 */
public class TicketlistActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout traffic_layout1, traffic_layout2, traffic_layout3;
    private ImageView traffic_content1, traffic_content2, traffic_content3;
    private ImageView traffic_img1, traffic_img2, traffic_img3;
    private TextView traffic_text1, traffic_text2, traffic_text3;
    private int typeid = 0;
    private Boolean spread1 = true, spread2 = false, spread3 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_ticketlist);
    }

    @Override
    protected void initView() {
        traffic_layout1 = (RelativeLayout) findViewById(R.id.traffic_layout1);
        traffic_layout2 = (RelativeLayout) findViewById(R.id.traffic_layout2);
        traffic_layout3 = (RelativeLayout) findViewById(R.id.traffic_layout3);
        traffic_layout1.setOnClickListener(this);
        traffic_layout2.setOnClickListener(this);
        traffic_layout3.setOnClickListener(this);
        traffic_content1 = (ImageView) findViewById(R.id.traffic_content1);
        traffic_content2 = (ImageView) findViewById(R.id.traffic_content2);
        traffic_content3 = (ImageView) findViewById(R.id.traffic_content3);
        traffic_img1 = (ImageView) findViewById(R.id.traffic_img1);
        traffic_img2 = (ImageView) findViewById(R.id.traffic_img2);
        traffic_img3 = (ImageView) findViewById(R.id.traffic_img3);
        traffic_text1 = (TextView) findViewById(R.id.traffic_text1);
        traffic_text2 = (TextView) findViewById(R.id.traffic_text2);
        traffic_text3 = (TextView) findViewById(R.id.traffic_text3);

        traffic_content1.setVisibility(View.VISIBLE);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return MainData.TICKETLIST;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.traffic_layout1://交通方式1
                if (spread1) {
                    spread1 = false;
                } else {
                    spread1 = true;
                }
                show(1, spread1);
                break;
            case R.id.traffic_layout2://交通方式2
                if (spread2) {
                    spread2 = false;
                } else {
                    spread2 = true;
                }
                show(2, spread2);
                break;
            case R.id.traffic_layout3://交通方式3
                if (spread3) {
                    spread3 = false;
                } else {
                    spread3 = true;
                }
                show(3, spread3);
                break;
        }
    }
    private void show(int type, Boolean spread) {
        if (type == 1) {//进山票
            if (spread) {
                traffic_content1.setVisibility(View.VISIBLE);
                traffic_img1.setBackgroundResource(R.mipmap.trafficup);
            } else {
                traffic_content1.setVisibility(View.GONE);
                traffic_img1.setBackgroundResource(R.mipmap.trafficdown);
            }


        } else if (type == 2) {//索道票
            if (spread) {
                traffic_content2.setVisibility(View.VISIBLE);
                traffic_img2.setBackgroundResource(R.mipmap.trafficup);
            } else {
                traffic_content2.setVisibility(View.GONE);
                traffic_img2.setBackgroundResource(R.mipmap.trafficdown);
            }

        } else {//演出票
            if (spread) {
                traffic_content3.setVisibility(View.VISIBLE);
                traffic_img3.setBackgroundResource(R.mipmap.trafficup);
            } else {
                traffic_content3.setVisibility(View.GONE);
                traffic_img3.setBackgroundResource(R.mipmap.trafficdown);
            }
        }
    }
}
