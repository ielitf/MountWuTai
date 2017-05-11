package com.bupt.mountwutai.ui.activity.buddhist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;

public class BuddhismDetailActivity extends BaseActivity {

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_buddhism_detail);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }
}
