package com.bupt.mountwutai.ui.activity.me;


import android.os.Bundle;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.MeAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.entity.me.MeItemBean;
import com.bupt.mountwutai.widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的
 */
public class MeFragment extends BaseFragment {

    private NoScrollListView listView;
    private MeAdapter adapter;
    private List<MeItemBean> datas;

    private String[] titles = {"消息推送", "我的监控", "我的收藏", "清除缓存", "用户反馈", "版本信息", "关于我们"};
    private int[] icons = {R.mipmap.message, R.mipmap.monitor,
            R.mipmap.collect, R.mipmap.clear, R.mipmap.userback,
            R.mipmap.version, R.mipmap.aboutus};

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_me);
        initView();
    }

    private void initView() {
        listView = (NoScrollListView) findViewById(R.id.me_listview);
        datas = new ArrayList<>();
        getdata();
        adapter = new MeAdapter(activity, datas);
        listView.setAdapter(adapter);
    }

    private void getdata() {
        for (int i = 0; i < titles.length; i++) {
            datas.add(new MeItemBean(icons[i], titles[i]));
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
        return "我的";
    }

}
