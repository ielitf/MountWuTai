package com.bupt.mountwutai.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonActivity;
import com.bupt.mountwutai.customdata.MainData;

/**
 * 概览
 */
public class SummaryActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout textView1,textView2,textView3,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_summary);
    }

    @Override
    protected void initView() {
        textView1 = (RelativeLayout) findViewById(R.id.buddhist_holy_land);
        textView2 = (RelativeLayout) findViewById(R.id.temple);
        textView3 = (RelativeLayout) findViewById(R.id.localCustom);
        textView4 = (RelativeLayout) findViewById(R.id.historic_legends);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return MainData.summary;
    }

    @Override
    public void onClick(View v) {
        Intent intent  = new Intent();
        switch (v.getId()){
            case R.id.buddhist_holy_land://佛教圣地
                intent.setClass(this, CommonDetailActivity.class);
                intent.putExtra(CodeConstants.TYPE,CodeConstants.BUDDHIST_HOLY_LAND);
                break;
            case R.id.temple://寺庙一览
                intent.setClass(this, CommonActivity.class);
                intent.putExtra(CodeConstants.TYPE,CodeConstants.TEPMLE_SUMMARY);
                break;
            case R.id.localCustom://地方风情
                intent.setClass(this, CommonDetailActivity.class);
                intent.putExtra(CodeConstants.TYPE,CodeConstants.LOCAL_CUSTOM);
                break;
            case R.id.historic_legends://历史传说
                intent.setClass(this, CommonDetailActivity.class);
                intent.putExtra(CodeConstants.TYPE,CodeConstants.HISTORIC_LEGENDS);
                break;
        }
        startActivity(intent);
    }

}
