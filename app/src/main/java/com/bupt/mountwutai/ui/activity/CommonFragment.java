package com.bupt.mountwutai.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;

/**
 * 图文列表：寺庙一览等
 * Created by litf on 2017/4/19.
 */

public class CommonFragment extends BaseFragment {
    private Context context;
    private ArrayList<CommonBean> mData;
    private ListView listView;
    private CommonAdapter adapter;
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_common);
        context = getActivity();
        listView = (ListView) findViewById(R.id.common_list);
        addData();
        adapter = new CommonAdapter(context,mData) ;
    }

    private void addData() {
        mData = new ArrayList<>();
        mData.add(new CommonBean(R.mipmap.ic_launcher,"国安酒店1","未成年人禁止入内1"));
        mData.add(new CommonBean(R.mipmap.ic_launcher,"国安酒店2","未成年人禁止入内2"));
        mData.add(new CommonBean(R.mipmap.ic_launcher,"国安酒店3","未成年人禁止入内3"));
        mData.add(new CommonBean(R.mipmap.ic_launcher,"国安酒店4","未成年人禁止入内4"));
        mData.add(new CommonBean(R.mipmap.ic_launcher,"国安酒店5","未成年人禁止入内5"));
        mData.add(new CommonBean(R.mipmap.ic_launcher,"国安酒店6","未成年人禁止入内6"));

    }
}
