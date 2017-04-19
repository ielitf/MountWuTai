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
    private int [] icon;
    private String [] title;
    private String [] content;
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_common);
        context = getActivity();
        listView = (ListView) findViewById(R.id.common_list);
        addData();
        adapter = new CommonAdapter(context,mData) ;
        listView.setAdapter(adapter);
    }

    private void addData() {
        mData = new ArrayList<>();
        icon = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        title = new String[]{"国安酒店1","国安酒店2","国安酒店3","国安酒店4"};
        content = new String[]{"未成年人禁止入内1","未成年人禁止入内2","未成年人禁止入内3","未成年人禁止入内4"};
        for (int i = 0;i<icon.length;i++){
            mData.add(new CommonBean(icon[i],title[i],content[i]));
        }
    }
}
