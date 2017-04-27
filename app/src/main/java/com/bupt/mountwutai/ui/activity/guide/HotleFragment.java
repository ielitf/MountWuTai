package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.HotleAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.customdata.HotelData;
import com.bupt.mountwutai.entity.HotleBean;

import java.util.ArrayList;

/**
 * 酒店
 */

public class HotleFragment extends BaseFragment {
    private ListView listView;
    private HotleAdapter adapter;
    private ArrayList<HotleBean> mData;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_hotel);
        listView = (ListView) findViewById(R.id.hotel_list);
        addData();
        adapter = new HotleAdapter(activity, mData);
        listView.setAdapter(adapter);
    }

    private void addData() {
        mData = new ArrayList<>();
        for (int i = 0; i < HotelData.titles.length; i++) {
            mData.add(new HotleBean(HotelData.show_imgs[i], HotelData.titles[i],
                    HotelData.contents[i], HotelData.addresses[i]));
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
