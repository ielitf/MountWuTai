package com.bupt.mountwutai.ui.activity.guide;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.DetailData;
import com.bupt.mountwutai.util.LogUtil;


public class DetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView titileTextView;//标题
    private ImageView showImageView;//详情页图片
    private TextView contentTextView;//显示内容

    int id = 1;//1 为五爷庙，2为塔院寺，3为游客中心

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra(CodeConstants.ID, 1);
        LogUtil.w(TAG,"id="+id);
        titileTextView = (TextView) findViewById(R.id.detail_title);
        showImageView = (ImageView) findViewById(R.id.detail_imageview);
        contentTextView = (TextView) findViewById(R.id.detail_contenet);
        showImageView.setBackgroundResource(DetailData.images[id-1]);
        titileTextView.setText(DetailData.titles[id-1]);
        contentTextView.setText(DetailData.contonts[id-1]);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "详情";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_play://播放按钮
                showToast("======");
                break;
        }
    }
}
