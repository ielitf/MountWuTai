package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

import java.util.regex.Pattern;

/**
 * 交通指南
 */

public class TrafficGuideFragment extends BaseFragment implements View.OnClickListener {

    private TextView button1, button2;
    private RelativeLayout traffic_layout1, traffic_layout2, traffic_layout3;
    private TextView traffic_content1, traffic_content2, traffic_content3;
    private ImageView traffic_img1, traffic_img2, traffic_img3;
    private TextView traffic_text1, traffic_text2, traffic_text3;
    private int typeid = 0;
    private Boolean spread1 = true, spread2 = false, spread3 = false;
    private Pattern pattern;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_guide_traffic);
        initView();
    }

    private void initView() {
        button1 = (TextView) findViewById(R.id.traffic_button1);
        button2 = (TextView) findViewById(R.id.traffic_button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        traffic_layout1 = (RelativeLayout) findViewById(R.id.traffic_layout1);
        traffic_layout2 = (RelativeLayout) findViewById(R.id.traffic_layout2);
        traffic_layout3 = (RelativeLayout) findViewById(R.id.traffic_layout3);
        traffic_layout1.setOnClickListener(this);
        traffic_layout2.setOnClickListener(this);
        traffic_layout3.setOnClickListener(this);
        traffic_content1 = (TextView) findViewById(R.id.traffic_content1);
        traffic_content2 = (TextView) findViewById(R.id.traffic_content2);
        traffic_content3 = (TextView) findViewById(R.id.traffic_content3);
        traffic_img1 = (ImageView) findViewById(R.id.traffic_img1);
        traffic_img2 = (ImageView) findViewById(R.id.traffic_img2);
        traffic_img3 = (ImageView) findViewById(R.id.traffic_img3);
        traffic_text1 = (TextView) findViewById(R.id.traffic_text1);
        traffic_text2 = (TextView) findViewById(R.id.traffic_text2);
        traffic_text3 = (TextView) findViewById(R.id.traffic_text3);

        traffic_content1.setText("位于忻州市定襄县的五台山机场，计划在2015年年底运行。如今外省游客可以先飞到省会太原，再换乘大巴前往五台山。\n" + "太原武宿机场，距离太原市区约18公里，与全国各主要大中城市都有直航，并有部分国际航线。咨询电话：0351-7286462。\n" + "机场有大巴直达市区和太原汽车客运东站（有发往五台山的班车），车票13-30元不等，大约30分钟一班。");
        traffic_content2.setText("火车");
        traffic_content3.setText("长途汽车");
        pattern = Pattern.compile("[0-9-()（）]{7,18}");
        Linkify.addLinks(traffic_content1, pattern, "tel:");
        Linkify.addLinks(traffic_content2, pattern, "tel:");
        Linkify.addLinks(traffic_content3, pattern, "tel:");
        traffic_content1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.traffic_button1://到达与离开
                typeid = 0;
                button1.setBackgroundResource(R.drawable.corners_left_selected);
                button2.setBackgroundResource(R.drawable.corners_right_unselected);
                traffic_text1.setText("飞机");
                traffic_text2.setText("火车");
                traffic_text3.setText("长途汽车");
                traffic_content1.setText("位于忻州市定襄县的五台山机场，计划在2015年年底运行。如今外省游客可以先飞到省会太原，再换乘大巴前往五台山。\n" + "太原武宿机场，距离太原市区约18公里，与全国各主要大中城市都有直航，并有部分国际航线。咨询电话：0351-7286462。\n" + "机场有大巴直达市区和太原汽车客运东站（有发往五台山的班车），车票13-30元不等，大约30分钟一班。");
                traffic_content2.setText("火车");
                traffic_content3.setText("长途汽车");
                Linkify.addLinks(traffic_content1, pattern, "tel:");
                Linkify.addLinks(traffic_content2, pattern, "tel:");
                Linkify.addLinks(traffic_content3, pattern, "tel:");
                break;
            case R.id.traffic_button2://内部交通
                typeid = 1;
                button1.setBackgroundResource(R.drawable.corners_left_unselected);
                button2.setBackgroundResource(R.drawable.corners_right_selected);
                traffic_text1.setText("观光车");
                traffic_text2.setText("登台车");
                traffic_text3.setText("出租车");
                traffic_content1.setText("景区内观光车来往于各主要景点，车票50元／人，和进山费联票出售，无论是否乘坐都必须购买（淡季部分时间内不售票，但游客也可以免费乘坐），在景区内可多次乘坐，大致运营时间7:00-19:00。\n" +
                        "目前观光车共有4条线路，有些线路的等靠站点会重合，在淡季期间有些线路可能会停运。");
                traffic_content2.setText("登顶五台，需在黛螺顶下的停车站乘坐登台车前往，单台60-80元／人（往返），五台联票350元，大致运营时间7:30-15:30。冬季会暂停运营。");
                traffic_content3.setText("出租车");
                Linkify.addLinks(traffic_content1, pattern, "tel:");
                Linkify.addLinks(traffic_content2, pattern, "tel:");
                Linkify.addLinks(traffic_content3, pattern, "tel:");
                break;
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
        if (type == 1) {//交通方式1
            if (spread) {
                traffic_content1.setVisibility(View.VISIBLE);
                traffic_img1.setBackgroundResource(R.mipmap.up);
            } else {
                traffic_content1.setVisibility(View.GONE);
                traffic_img1.setBackgroundResource(R.mipmap.down);
            }


        } else if (type == 2) {//交通方式2
            if (spread) {
                traffic_content2.setVisibility(View.VISIBLE);
                traffic_img2.setBackgroundResource(R.mipmap.up);
            } else {
                traffic_content2.setVisibility(View.GONE);
                traffic_img2.setBackgroundResource(R.mipmap.down);
            }

        } else {//交通方式3
            if (spread) {
                traffic_content3.setVisibility(View.VISIBLE);
                traffic_img3.setBackgroundResource(R.mipmap.up);
            } else {
                traffic_content3.setVisibility(View.GONE);
                traffic_img3.setBackgroundResource(R.mipmap.down);
            }
        }
    }
}
