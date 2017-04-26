package com.bupt.mountwutai.ui.activity.service;


import android.os.Bundle;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * 政府机构
 */
public class GovernFragment extends BaseFragment {


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_govern);

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


}
