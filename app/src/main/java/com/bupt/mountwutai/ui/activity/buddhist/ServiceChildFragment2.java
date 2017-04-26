package com.bupt.mountwutai.ui.activity.buddhist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * .
 */
public class ServiceChildFragment2 extends BaseFragment implements View.OnClickListener{

    private RelativeLayout service2_layout,service2_layout2;
    private ImageView service2_img,service2_img2;
    private TextView service2_content,service2_content2;
    private Boolean isSelect1 = true,isSelect2 = true;


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_service_child_fragment2);
        initView();
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

    private void initView() {
        service2_layout = (RelativeLayout) findViewById(R.id.service2_layout);
        service2_layout2 = (RelativeLayout) findViewById(R.id.service2_layout2);
        service2_layout.setOnClickListener(this);
        service2_layout2.setOnClickListener(this);
        service2_img = (ImageView) findViewById(R.id.service2_img);
        service2_img2 = (ImageView) findViewById(R.id.service2_img2);
        service2_content = (TextView) findViewById(R.id.service2_content);
        service2_content2 = (TextView) findViewById(R.id.service2_content2);
        service2_content.setText("公安局\t\t\t0350—6542163\n\n" +
                "入山处\t\t\t0350—6548696\n\n" +
                "交警队\t\t\t0350—6548122\n\n" +
                "游客中心\t\t\t0350—6568178\n\n" +
                "消防大队\t\t\t0350—6548119");
        service2_content2.setText("五台山只有简单的卫生院，位置在五台县" +
                "台怀镇上，条件一般，但是处理一些小病小伤不是问题。\n\n" +
                "医疗急救电话 \t\t\t0350—6543120");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service2_layout://电话
                if (isSelect1) {
                    isSelect1=false;
                    service2_content.setVisibility(View.GONE);
                    service2_img.setBackgroundResource(R.mipmap.trafficdown);
                } else {
                    isSelect1=true;
                    service2_content.setVisibility(View.VISIBLE);
                    service2_img.setBackgroundResource(R.mipmap.trafficup);
                }
                break;
            case R.id.service2_layout2://医院
                if (isSelect2) {
                    isSelect2=false;
                    service2_content2.setVisibility(View.GONE);
                    service2_img2.setBackgroundResource(R.mipmap.trafficdown);
                } else {
                    isSelect2=true;
                    service2_content2.setVisibility(View.VISIBLE);
                    service2_img2.setBackgroundResource(R.mipmap.trafficup);
                }
                break;
        }
    }
}
