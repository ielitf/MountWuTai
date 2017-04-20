package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * 朝台攻略
 */

public class TravelStrategyFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout centerRelativeLayout;
    private TextView centerarrow;
    private boolean isCenterarrowup;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_travel_strategy);

        initView();
    }

    private void initView() {
        centerRelativeLayout = (RelativeLayout) findViewById(R.id.center);
        centerarrow = (TextView) findViewById(R.id.center_arrow);
        centerRelativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center:
                if (isCenterarrowup) {
                    centerarrow.setBackgroundResource(R.mipmap.down);
                    isCenterarrowup = false;
                } else {
                    centerarrow.setBackgroundResource(R.mipmap.up);
                    isCenterarrowup = true;
                }
                break;
        }
    }
}
