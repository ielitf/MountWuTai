package com.bupt.mountwutai.ui.activity.service;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * 投诉建议
 */
public class ComplaintFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout service3_layout;
    private ImageView service3_img;
    private TextView service3_content;
    private Boolean isSelect = true;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_complaint);
        initView();
    }

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
        return null;
    }

    private void initView() {
        service3_layout = (RelativeLayout) findViewById(R.id.service3_layout);
        service3_layout.setOnClickListener(this);
        service3_img = (ImageView) findViewById(R.id.service3_img);
        service3_content = (TextView) findViewById(R.id.service3_content);
        service3_content.setText("旅游咨询电话\t\t\t400-0350-236\n\n" +
                "旅游投诉电话\t\t\t400-0350-226");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service3_layout://电话
                if (isSelect) {
                    isSelect=false;
                    service3_content.setVisibility(View.GONE);
                    service3_img.setBackgroundResource(R.mipmap.trafficdown);
                } else {
                    isSelect=true;
                    service3_content.setVisibility(View.VISIBLE);
                    service3_img.setBackgroundResource(R.mipmap.trafficup);
                }
                break;
        }
    }
}
