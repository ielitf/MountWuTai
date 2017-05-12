package com.bupt.mountwutai.ui.activity.classification;


import android.os.Bundle;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.ClassificationAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.entity.classification.ClassificationBean;
import com.bupt.mountwutai.entity.mian.PoliticsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 */
public class ClassificationFragment extends BaseFragment {

    private ListView listView;
    private List<ClassificationBean> classificationBeanList;
    private ClassificationAdapter classificationAdapter;

    private String[] services = {"天气预报", "汽车时刻", "火车时刻",
            "航班动态", "酒店预订", "医疗救援", "门票预售", "行程规划", "佛事活动"};
    private int[] servicesIcon = {R.mipmap.classificate_weather, R.mipmap.classificate_bus,
            R.mipmap.classificate_train, R.mipmap.classificate_fly, R.mipmap.classificate_hotel,
            R.mipmap.classificate_help, R.mipmap.classificate_ticket, R.mipmap.classificate_guide,
            R.mipmap.classificate_buddhist_active};

    private String[] others = {"政务信息", "五台食谱", "景点咨询"};
    private int[] othersicon = {R.mipmap.classificate_politic, R.mipmap.classificate_shipopu,
            R.mipmap.classificate_sence};

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_classification);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.class_listview);
        classificationBeanList = new ArrayList<>();
        initData();
        classificationAdapter = new ClassificationAdapter(activity, classificationBeanList);
        listView.setAdapter(classificationAdapter);
    }

    private void initData() {
        ClassificationBean bean = new ClassificationBean();
        List<PoliticsBean> beanList = new ArrayList<>();
        for (int i = 0; i < services.length; i++) {
            beanList.add(new PoliticsBean(servicesIcon[i], services[i]));
        }
        bean.setTitle("便民服务");
        bean.setBeanList(beanList);
        classificationBeanList.add(bean);

        ClassificationBean otherbean = new ClassificationBean();
        List<PoliticsBean> otherbeanList = new ArrayList<>();
        for (int i = 0; i < others.length; i++) {
            otherbeanList.add(new PoliticsBean(othersicon[i], others[i]));
        }
        otherbean.setTitle("其他");
        otherbean.setBeanList(otherbeanList);
        classificationBeanList.add(otherbean);
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
        return "分类";
    }
}
