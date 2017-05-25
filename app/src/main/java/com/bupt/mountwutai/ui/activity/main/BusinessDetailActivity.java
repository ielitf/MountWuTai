package com.bupt.mountwutai.ui.activity.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.entity.mian.BusinessTypeListBean;
import com.bupt.mountwutai.util.ToastUtil;

/**
 * 业务详情页面
 */
public class BusinessDetailActivity extends BaseActivity {

    private TextView priceText;
    private TextView desText;
    private ImageView productImage;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_business_detail);
    }

    @Override
    protected void initView() {
        priceText = (TextView) findViewById(R.id.product_price);
        desText = (TextView) findViewById(R.id.product_description);
        productImage = (ImageView) findViewById(R.id.product_pic);
        findViewById(R.id.order_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(BusinessDetailActivity.this,"该功能暂未开放");
            }
        });
        priceText.setText(bean.getFee());
        desText.setText(bean.getContent());
        productImage.setBackgroundResource(bean.getIcon());
    }

    BusinessTypeListBean bean;

    @Override
    protected void initArgs() {
        bean = getIntent().getExtras().getParcelable(CodeConstants.ID);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return bean.getTitle();
    }
}
