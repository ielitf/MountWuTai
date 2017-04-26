package com.bupt.mountwutai.ui.activity.service;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * 政府机构
 */
public class GovernFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout service_layout, service_layout2;
    private ImageView service_img, service_img2;
    private TextView service_content, service_content2;
    private Boolean isSelect1 = true, isSelect2 = true;


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_govern);
        initView();
    }

    private void initView() {
        service_layout = (RelativeLayout) findViewById(R.id.service_layout);
        service_layout2 = (RelativeLayout) findViewById(R.id.service_layout2);
        service_layout.setOnClickListener(this);
        service_layout2.setOnClickListener(this);
        service_img = (ImageView) findViewById(R.id.service_img);
        service_img2 = (ImageView) findViewById(R.id.service_img2);
        service_content = (TextView) findViewById(R.id.service_content);
        service_content2 = (TextView) findViewById(R.id.service_content2);
        service_content.setText("2015年11月26日，忻州市政府向山西省政府提出成立五台山" +
                "风景名胜区管理委员会的请示，经省政府第105次常务会认真研究，原则同意忻州" +
                "市人民政府提出的关于成立五台山风景名胜区管理委员会、撤销五台山风景名胜区" +
                "人民政府的建议。2016年1月20日，山西省十二届人大常委会第二十四次会议通过" +
                "废止《关于成立五台山风景名胜区人民政府的决定》，省政府责成忻州市政府依法" +
                "成立五台山风景名胜区管理委员会，并确保实现两种管理体制的平稳过渡，推进五" +
                "台山风景名胜区的严格保护、科学利用和有效管理。设立28年之久的五台山风景名" +
                "胜区人民政府已于2016年1月20日被撤销，五台山风景名胜区管委会同步成立。\n" +
                "2016年6月15日， 中共五台山风景名胜区工作委员会、五台山风景名胜区管理委员" +
                "会正式挂牌成立。忻州市委常委范波涛成为首任中共五台山风景名胜区工作委员会书" +
                "记、五台山风景名胜区管理委员会主任。");
        service_content2.setText("五台县，位于山西省东北部，北与繁峙县、代县相邻；南与孟" +
                "为界；东与河北省平定山、阜平两县毗连；西与定襄、原平接壤。五台西汉始置县，" +
                "已历时2000多年。辖五台山风景名胜区、驼梁景区、6镇、13乡、573行政村。总面" +
                "积2865平方公里，居山西省第三，总人口32.7万，其中农业人员28万。");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_layout://委员会
                if (isSelect1) {
                    isSelect1 = false;
                    service_content.setVisibility(View.GONE);
                    service_img.setBackgroundResource(R.mipmap.trafficdown);
                } else {
                    isSelect1 = true;
                    service_content.setVisibility(View.VISIBLE);
                    service_img.setBackgroundResource(R.mipmap.trafficup);
                }
                break;
            case R.id.service_layout2://政府
                if (isSelect2) {
                    isSelect2 = false;
                    service_content2.setVisibility(View.GONE);
                    service_img2.setBackgroundResource(R.mipmap.trafficdown);
                } else {
                    isSelect2 = true;
                    service_content2.setVisibility(View.VISIBLE);
                    service_img2.setBackgroundResource(R.mipmap.trafficup);
                }
                break;
        }
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
