package com.bupt.mountwutai.ui.activity.buddhist;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuddhistChildFragment3 extends BaseFragment {


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_buddhist_child_fragment3);
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
