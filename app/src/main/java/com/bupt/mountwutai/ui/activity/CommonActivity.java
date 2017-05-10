package com.bupt.mountwutai.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.LocalProductsData;
import com.bupt.mountwutai.customdata.OnlineData;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.customdata.WutaiData;
import com.bupt.mountwutai.entity.CommonBean;

import java.util.ArrayList;

public class CommonActivity extends AppCompatActivity {
    private RelativeLayout top;
    private TextView titleName;
    private ArrayList<CommonBean> mData;
    private ListView listView;
    private CommonAdapter adapter;
    private String type;//代表当前是哪一个，比如寺庙还是美食，以添加不同的数据
    private boolean isOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        type = getIntent().getStringExtra(CodeConstants.TYPE);
        listView = (ListView) findViewById(R.id.common_list);
        top = (RelativeLayout) findViewById(R.id.common_fragment_top);
        titleName = (TextView) findViewById(R.id.top_name_text);
        addData();
        adapter = new CommonAdapter(this, mData);
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
                top.setVisibility(View.VISIBLE);
                titleName.setText("土特产");
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
}
