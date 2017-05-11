package com.bupt.mountwutai.ui.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.SummaryData;

public class CommonDetailActivity extends BaseActivity {
    private TextView textView_title;
    private ImageView[] imageView = new ImageView[6];
    private TextView[] textView = new TextView[6];

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_common_detail);
    }
    @Override
    protected void initView() {
        initViews();
        addData();
    }

    private void initViews() {
        imageView[0] = (ImageView) findViewById(R.id.common_web_imag);
        imageView[1] = (ImageView) findViewById(R.id.common_web_imag2);
        imageView[2] = (ImageView) findViewById(R.id.common_web_imag3);
        imageView[3] = (ImageView) findViewById(R.id.common_web_imag4);
        imageView[4] = (ImageView) findViewById(R.id.common_web_imag5);
        imageView[5] = (ImageView) findViewById(R.id.common_web_imag6);
        textView_title = (TextView) findViewById(R.id.common_web_tvContent_title);
        textView[0] = (TextView) findViewById(R.id.common_web_tvContent);
        textView[1] = (TextView) findViewById(R.id.common_web_tvContent2);
        textView[2] = (TextView) findViewById(R.id.common_web_tvContent3);
        textView[3] = (TextView) findViewById(R.id.common_web_tvContent4);
        textView[4] = (TextView) findViewById(R.id.common_web_tvContent5);
        textView[5] = (TextView) findViewById(R.id.common_web_tvContent6);
    }

    private void addData() {
        imageView[0].setImageResource(SummaryData.historic_legends_img[0]);
        textView[0].setText(SummaryData.historic_legends_text[0]);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "历史传说";
    }
}
