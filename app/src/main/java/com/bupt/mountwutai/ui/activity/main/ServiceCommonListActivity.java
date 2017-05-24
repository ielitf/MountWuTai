package com.bupt.mountwutai.ui.activity.main;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.BusinessTypeListAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.BroadcastingCenterDate;
import com.bupt.mountwutai.entity.mian.BusinessTypeListBean;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mountwutai.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 新装业务、优惠活动、宽带业务
 */

public class ServiceCommonListActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private String type;
    private String title = "";
    private ListView businessTypeListView;
    private BusinessTypeListAdapter adapter;
    private int[] icons_y = {R.mipmap.y_1, R.mipmap.y_2};
    private int[] icons_x = {R.mipmap.x_4, R.mipmap.x_5, R.mipmap.x_6, R.mipmap.x_7};
    private int[] icons_k = {R.mipmap.k_1, R.mipmap.k_2, R.mipmap.k_3, R.mipmap.k_4, R.mipmap.k_5, R.mipmap.k_6, R.mipmap.k_7, R.mipmap.k_8, R.mipmap.k_9};
    private List<BusinessTypeListBean> beanList;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_service_comomlist);
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(CodeConstants.TYPE);
        businessTypeListView = (ListView) findViewById(R.id.addr_info_listview);
        switch (type) {
            case CodeConstants.BUSINESS_NEW://新装业务
                title = "新装业务";
                beanList = (List<BusinessTypeListBean>)
                        Utils.parseData(BroadcastingCenterDate.new_clothes_business, new Gson(),
                                new TypeToken<List<BusinessTypeListBean>>() {
                                });
                for (int i = 0; i < beanList.size(); i++) {
                    beanList.get(i).setIcon(icons_x[i]);
                }
                break;
            case CodeConstants.BUSINESS_SALES://优惠活动
                title = "优惠活动";
                beanList = (List<BusinessTypeListBean>)
                        Utils.parseData(BroadcastingCenterDate.preferential_activities, new Gson(),
                                new TypeToken<List<BusinessTypeListBean>>() {
                                });
                for (int i = 0; i < beanList.size(); i++) {
                    beanList.get(i).setIcon(icons_y[i]);
                }
                break;
            case CodeConstants.BUSINESS_MAC://宽带业务
                title = "宽带业务";
                beanList = (List<BusinessTypeListBean>)
                        Utils.parseData(BroadcastingCenterDate.broadband_business, new Gson(),
                                new TypeToken<List<BusinessTypeListBean>>() {
                                });
                for (int i = 0; i < beanList.size(); i++) {
                    beanList.get(i).setIcon(icons_k[i]);
                }
                break;
        }
        adapter = new BusinessTypeListAdapter(ServiceCommonListActivity.this, beanList);
        businessTypeListView.setAdapter(adapter);
        businessTypeListView.setOnItemClickListener(this);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return title;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(ServiceCommonListActivity.this,"进入详情页\n此功能稍后上线");
    }
}
