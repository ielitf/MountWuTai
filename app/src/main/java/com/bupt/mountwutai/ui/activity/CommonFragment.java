package com.bupt.mountwutai.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.entity.CommonBean;
import com.bupt.mountwutai.util.LogUtil;

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
    private String type;//代表当前是哪一个，比如寺庙还是美食，以添加不同的数据

    public CommonFragment(){
    }

    public static CommonFragment newFragment(String type){
        CommonFragment commonFragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putString(CodeConstants.TYPE,type);
        commonFragment.setArguments(args);
        return commonFragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_common);
        context = getActivity();
        LogUtil.d("=====","进入寺庙一览");
        type = getArguments().getString(CodeConstants.TYPE);
        listView = (ListView) findViewById(R.id.common_list);
        addData();
        adapter = new CommonAdapter(context,mData) ;
        listView.setAdapter(adapter);
    }
    private void addData() {
        mData = new ArrayList<>();
        switch (type){
            case CodeConstants.TEPMLE_SUMMARY://寺庙一览
                for (int i = 0; i< SummaryData.icon.length; i++){
                    mData.add(new CommonBean(SummaryData.icon[i],SummaryData.title[i],SummaryData.content[i]));
                }
                LogUtil.d("=====",mData.toString());
                break;
            default:
                break;
        }
    }
}
