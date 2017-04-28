package com.bupt.mountwutai.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.LocalProductsData;
import com.bupt.mountwutai.customdata.OnlineData;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.customdata.WutaiData;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;

/**
 * 图文列表：寺庙一览等
 * Created by litf on 2017/4/19.
 */

public class CommonFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private RelativeLayout fragmentTop;
    private TextView fragmentTopName;
    private ArrayList<CommonBean> mData;
    private ListView listView;
    private CommonAdapter adapter;
    private String type;//代表当前是哪一个，比如寺庙还是美食，以添加不同的数据
    private boolean isOnline;

    public static CommonFragment newFragment(String type) {
        CommonFragment commonFragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putString(CodeConstants.TYPE, type);
        commonFragment.setArguments(args);
        return commonFragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_common);
        type = getArguments().getString(CodeConstants.TYPE);
        fragmentTop = (RelativeLayout) findViewById(R.id.common_fragment_top);
        fragmentTopName = (TextView) findViewById(R.id.top_name_text);
        listView = (ListView) findViewById(R.id.common_list);
        addData();
        adapter = new CommonAdapter(activity, mData);
        if (type.equals(CodeConstants.BUDDHA_ONLINE)) {
            adapter.setOnline(true);
        }
        listView.setAdapter(adapter);
    }

    private void addData() {
        mData = new ArrayList<>();
        switch (type) {
            case CodeConstants.TEPMLE_SUMMARY://寺庙一览
                for (int i = 0; i < SummaryData.temple_icon.length; i++) {
                    mData.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i], SummaryData.temple_content[i]));
                }
                break;

            case CodeConstants.WUTAI_RECIPES://五台食谱
                for (int i = 0; i < WutaiData.wutai_icon.length; i++) {
                    mData.add(new CommonBean(WutaiData.wutai_icon[i], WutaiData.wutai_title[i], WutaiData.wutai_content[i]));
                }
                break;
            case CodeConstants.LOCAL_PRODUCTS://土特产
                fragmentTop.setVisibility(View.VISIBLE);
                fragmentTopName.setText("土特产");
                for (int i = 0; i < LocalProductsData.product_icon.length; i++) {
                    mData.add(new CommonBean(LocalProductsData.product_icon[i], LocalProductsData.product_title[i], LocalProductsData.product_content[i]));
                }
                break;
            case CodeConstants.BUDDHA_ONLINE://在线礼佛
                isOnline = true;
                for (int i = 0; i < OnlineData.icons.length; i++) {
                    mData.add(new CommonBean(OnlineData.icons[i], OnlineData.titles[i], OnlineData.contonts[i], OnlineData.prices[i]));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
