package com.bupt.mountwutai.ui.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.HomeListAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.entity.CommonBean;

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

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
        context = getActivity();
        listView = (ListView) findViewById(R.id.home_list);
        homeListAdapter = new HomeListAdapter(context,homeLists,nameList);
        listView.setAdapter(homeListAdapter);
        addData();
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
