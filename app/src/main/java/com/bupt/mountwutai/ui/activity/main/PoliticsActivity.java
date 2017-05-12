package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.widget.GridView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.PoliticsAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.entity.mian.PoliticsBean;

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
        initData();
        adapter = new PoliticsAdapter(this, politicsBeanList);
        gridView.setAdapter(adapter);
    }

    private void initData() {
        politicsBeanList.clear();
        switch (type) {
            case CodeConstants.POLITICS_OPEN:
                politicsBeanList.add(new PoliticsBean(R.mipmap.memorabilia, "大事记"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.function, "行政职能"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.notice, "公告公示"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.law, "法律法规"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.inforopen, "政府信息"));
                break;
            case CodeConstants.POLITICS_INTERACTION:
                politicsBeanList.add(new PoliticsBean(R.mipmap.leader, "领导信箱"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.report, "投诉举报"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.advice, "建言献策"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.seek, "留言咨询"));
                break;
            case CodeConstants.BROADCAST_CENTER:
                politicsBeanList.add(new PoliticsBean(R.mipmap.tvlive, "电视直播"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.tvask, "电视点播"));
                politicsBeanList.add(new PoliticsBean(R.mipmap.businesshall, "营业厅"));
                break;

        }
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
