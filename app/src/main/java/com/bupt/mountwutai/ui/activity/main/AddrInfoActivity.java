package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.AddrInfoAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.BroadcastingCenterDate;
import com.bupt.mountwutai.entity.mian.AddrInforBean;
import com.bupt.mountwutai.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 营业网点
 */
public class AddrInfoActivity extends BaseActivity {

    private ListView addrListView;
    AddrInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_addr_info);
    }

    @Override
    protected void initView() {
        addrListView = (ListView) findViewById(R.id.addr_info_listview);
        Gson gson = new Gson();
        List<AddrInforBean> beanList = (List<AddrInforBean>)
                Utils.parseData(BroadcastingCenterDate.business_net, gson,
                        new TypeToken<List<AddrInforBean>>() {
                        });
        for (AddrInforBean bean : beanList
                ) {
            bean.setIcon(R.mipmap.default_pic);
        }
//        Log.i(TAG, "beanList: "+ beanList.toString());
        adapter = new AddrInfoAdapter(AddrInfoActivity.this, beanList);
        addrListView.setAdapter(adapter);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "营业网点";
    }
}
