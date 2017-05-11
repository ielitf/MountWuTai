package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;

/**
 * 门票一览
 */
public class TicketlistActivity extends BaseActivity {

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

    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "门票一览";
    }
}
