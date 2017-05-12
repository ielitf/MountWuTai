package com.bupt.mountwutai.ui.activity.buddhist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.BuddhismData;

/**
 * 佛事活动详情
 */
public class BuddhismDetailActivity extends BaseActivity {
    private String title;
    private ImageView imageView;
    private TextView title1,title2,content1,content2;
    private int position;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_buddhism_detail);
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.common_deail_imag);
        title1 = (TextView) findViewById(R.id.common_deail_title1);
        title2 = (TextView) findViewById(R.id.common_deail_title2);
        content1 = (TextView) findViewById(R.id.common_deail_tvContent1);
        content2 = (TextView) findViewById(R.id.common_deail_tvContent2);
        position = getIntent().getIntExtra("position",0);
        switch (position){
            case 0:
                imageView.setImageResource(BuddhismData.buddhism_detail_icon[0]);
                title1.setText(BuddhismData.buddhism_detail_content[0]);
                content1.setText(BuddhismData.buddhism_detail_content[1]);
                break;
            case 1:
                imageView.setImageResource(BuddhismData.buddhism_detail_icon[1]);
                title1.setText(BuddhismData.buddhism_detail_content2[0]);
                content1.setText(BuddhismData.buddhism_detail_content2[1]);
                title2.setText(BuddhismData.buddhism_detail_content2[2]);
                content2.setText(BuddhismData.buddhism_detail_content2[3]);
                break;
            case 2:
                break;
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "详情";
    }
}
