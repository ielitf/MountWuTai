package com.bupt.mountwutai.ui.activity.main;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;





public class FastFaultRepairFragment extends BaseFragment {

    LinearLayout layout;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_fast);
    }

    @Override
    protected boolean hasPopWindow() {
        return true;
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
