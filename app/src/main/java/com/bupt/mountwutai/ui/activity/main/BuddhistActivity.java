package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.util.ToastUtil;

/**
 * 佛事
 */
public class BuddhistActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout[] relativeLayouts = new RelativeLayout[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_buddhist);
    }

    @Override
    protected void initView() {
        relativeLayouts[0] = (RelativeLayout) findViewById(R.id.buddhist_active);
        relativeLayouts[1] = (RelativeLayout) findViewById(R.id.buddhist_knowledge);
        relativeLayouts[2] = (RelativeLayout) findViewById(R.id.buddhist_online);
        for (int i = 0; i < relativeLayouts.length; i++) {
            relativeLayouts[i].setOnClickListener(this);
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return MainData.buddhist;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buddhist_active://佛事活动
                ToastUtil.show(this, "佛事活动");
                break;
            case R.id.buddhist_knowledge://佛教知识
                ToastUtil.show(this, "佛教知识");
                break;
            case R.id.buddhist_online://在线礼佛
                ToastUtil.show(this, "在线礼佛");
                break;
        }
    }
}
