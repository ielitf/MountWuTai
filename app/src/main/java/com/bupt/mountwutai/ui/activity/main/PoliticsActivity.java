package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.PoliticsAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.entity.mian.PoliticsBean;
import com.bupt.mountwutai.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 政务公开，政民互动，广电中心
 */
public class PoliticsActivity extends BaseActivity {

    private GridView gridView;
    List<PoliticsBean> politicsBeanList;
    PoliticsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_politics);
    }

    String type;

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(CodeConstants.TYPE);
        gridView = (GridView) findViewById(R.id.politics_grid);
        politicsBeanList = new ArrayList<>();
        adapter = new PoliticsAdapter(this, politicsBeanList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (adapter.getList().get(position).getTitle()) {

                    case "营业网点":
                        intent2Activity(AddrInfoActivity.class);
                        break;

                    case "电视业务":
                        intent2Activity(BusinessTypeListActivity.class);
                        break;

                    case "故障报修":
                        intent2Activity(FaultRepairActivity.class);
                        break;

                    default:
                        ToastUtil.show(PoliticsActivity.this, adapter.getList().get(position).getTitle());
                        break;
                }
            }
        });
        initData();
    }

    String[] politis_open = {"大事记", "行政职能", "公告公示", "法律法规", "政府信息"};
    int[] open = {R.mipmap.memorabilia, R.mipmap.function, R.mipmap.notice, R.mipmap.law, R.mipmap.inforopen};

    String[] politis_interaction = {"领导信箱", "投诉举报", "建言献策", "留言咨询"};
    int[] interaction = {R.mipmap.leader, R.mipmap.report, R.mipmap.advice, R.mipmap.seek};

    String[] boradcast_center = {"电视直播", "电视点播", "营业网点", "电视业务", "新装业务",
            "优惠活动", "宽带业务", "故障报修", "在线调查", "节目预告"};
    int[] center = {R.mipmap.tvlive, R.mipmap.tvask, R.mipmap.businesshall, R.mipmap.business_tv_icon,
            R.mipmap.business_new_icon, R.mipmap.business_hui_icon, R.mipmap.business_mac_icon,
            R.mipmap.business_fix_icon, R.mipmap.business_survey_icon, R.mipmap.business_preview_icon};

    String[] titles;
    int[] icons;

    private void initData() {
        switch (type) {
            case CodeConstants.POLITICS_OPEN:
                titles = politis_open;
                icons = open;
                break;
            case CodeConstants.POLITICS_INTERACTION:
                titles = politis_interaction;
                icons = interaction;
                break;
            case CodeConstants.BROADCAST_CENTER:
                titles = boradcast_center;
                icons = center;
                break;
        }
        politicsBeanList.clear();
        for (int i = 0; i < titles.length; i++) {
            politicsBeanList.add(new PoliticsBean(icons[i], titles[i]));
        }
        adapter.clear();
        adapter.addCollection(politicsBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return getIntent().getStringExtra(CodeConstants.ID);
    }
}
