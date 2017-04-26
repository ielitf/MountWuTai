package com.bupt.mountwutai.ui.activity.buddhist;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * 在线礼佛
 */
public class OnlineSurveyFragment extends BaseFragment {


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_online_survey);
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
