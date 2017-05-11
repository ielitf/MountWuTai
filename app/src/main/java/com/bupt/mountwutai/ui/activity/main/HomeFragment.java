package com.bupt.mountwutai.ui.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.HomeListAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.entity.CommonBean;
import com.bupt.mountwutai.entity.mian.CustomBean;
import com.bupt.mountwutai.entity.mian.SlidesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by litf on 2017/5/10.
 */

public class HomeFragment extends BaseFragment {
    private Context context;
    private ListView listView;
    private List<String> nameList = new ArrayList<>();
    List<ArrayList<CommonBean>> homeLists = new ArrayList<>();
    ArrayList<CommonBean> commonBeanLists1 = new ArrayList<>();
    ArrayList<CommonBean> commonBeanLists2 = new ArrayList<>();
    ArrayList<CommonBean> commonBeanLists3 = new ArrayList<>();
    private HomeListAdapter homeListAdapter;
    HeaderPanel headerPanel;
    SlidesPanel slidesPanel;
    List<CustomBean> headers = new ArrayList<>();

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
        context = getActivity();
        listView = (ListView) findViewById(R.id.home_list);
        homeListAdapter = new HomeListAdapter(context, homeLists, nameList);
        listView.setAdapter(homeListAdapter);
        addData();
        List<SlidesEntity> datas = new ArrayList<>();
        datas.add(new SlidesEntity("", "title1"));
        datas.add(new SlidesEntity("", "title2"));
        datas.add(new SlidesEntity("", "title3"));
        datas.add(new SlidesEntity("", "title4"));
        slidesPanel = new SlidesPanel(activity);
        slidesPanel.setData(datas);
        slidesPanel.startLoop(3000);
        initheader();
        headerPanel = new HeaderPanel(activity);
        headerPanel.setData(headers);
        listView.addHeaderView(slidesPanel.getContentView());
        listView.addHeaderView(headerPanel.getContentView());
    }

    private void initheader() {
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.summary, true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.guide, true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.buddhist, true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, "服务", false));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, "土特产", false));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, "景区直播", true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, "广电中心", true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, "政务公开", true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, "政民互动", true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.forestFire, true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.relicsProtect, true));
        headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.religiousAffairs, true));
    }

    private void addData() {
        nameList.add("推荐");
        nameList.add("佛事");
        for (int i = 0; i < 3; i++) {
            commonBeanLists1.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i], SummaryData.temple_content[i]));
        }
        for (int i = 0; i < 3; i++) {
            commonBeanLists2.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i], SummaryData.temple_content[i]));
        }
        homeLists.add(commonBeanLists1);
        homeLists.add(commonBeanLists2);
    }
    @Override
    public void onPause() {
        super.onPause();
        slidesPanel.stopLoop();
    }

    @Override
    public void onResume() {
        super.onResume();
        slidesPanel.startLoop(3000);
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
        return "智慧五台山";
    }

}
