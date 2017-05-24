package com.bupt.mountwutai.ui.activity.main;

import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.BusinessTypeListAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.BroadcastingCenterDate;
import com.bupt.mountwutai.entity.mian.BusinessTypeListBean;
import com.bupt.mountwutai.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 新装业务、优惠活动、宽带业务
 */

public class ServiceCommonListActivity extends BaseActivity {
    private String type;
    private String title = "";
    private ListView businessTypeListView;
    private BusinessTypeListAdapter adapter;
    private int[] icons = {R.mipmap.buiness_tv1, R.mipmap.buiness_tv2, R.mipmap.buiness_tv3};
    private List<BusinessTypeListBean> beanList;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_service_comomlist);
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(CodeConstants.TYPE);
        businessTypeListView = (ListView) findViewById(R.id.addr_info_listview);
        switch (type){
            case CodeConstants.BUSINESS_NEW://新装业务
                title = "新装业务";
                beanList = (List<BusinessTypeListBean>)
                        Utils.parseData(BroadcastingCenterDate.new_clothes_business, new Gson(),
                                new TypeToken<List<BusinessTypeListBean>>() {
                                });
                break;
            case CodeConstants.BUSINESS_SALES://优惠活动
                title = "优惠活动";
                beanList = (List<BusinessTypeListBean>)
                        Utils.parseData(BroadcastingCenterDate.preferential_activities, new Gson(),
                                new TypeToken<List<BusinessTypeListBean>>() {
                                });
                break;
            case CodeConstants.BUSINESS_MAC://宽带业务
                title = "宽带业务";
                beanList = (List<BusinessTypeListBean>)
                        Utils.parseData(BroadcastingCenterDate.broadband_business, new Gson(),
                                new TypeToken<List<BusinessTypeListBean>>() {
                                });
                break;
        }
//        for (int i = 0; i < beanList.size(); i++) {
//            beanList.get(i).setIcon(icons[i]);
//        }
        adapter = new BusinessTypeListAdapter(ServiceCommonListActivity.this, beanList);
        businessTypeListView.setAdapter(adapter);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return title;
    }
}
