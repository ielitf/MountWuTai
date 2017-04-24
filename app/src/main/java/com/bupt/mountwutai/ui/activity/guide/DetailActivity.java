package com.bupt.mountwutai.ui.activity.guide;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.WutaiData;

public class DetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView titileTextView;//标题
    private ImageView showImageView;//详情页图片
    private TextView contentTextView;//显示内容

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initView() {
        findViewById(R.id.top_back).setVisibility(View.VISIBLE);
        findViewById(R.id.top_back).setOnClickListener(this);
        findViewById(R.id.detail_play).setOnClickListener(this);
        findViewById(R.id.popimg).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.myButton)).setText("详情");
        titileTextView = (TextView) findViewById(R.id.detail_title);
        showImageView = (ImageView) findViewById(R.id.detail_imageview);
        contentTextView = (TextView) findViewById(R.id.detail_contenet);
        contentTextView.setText(WutaiData.wutai_content[0] + WutaiData.wutai_content[0] + WutaiData.wutai_content[0]
                + WutaiData.wutai_content[0] + WutaiData.wutai_content[0] + WutaiData.wutai_content[0]);
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.detail_play://播放按钮
                showToast("======");
                break;
        }
    }
}
