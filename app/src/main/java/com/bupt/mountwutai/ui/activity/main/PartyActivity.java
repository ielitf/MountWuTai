package com.bupt.mountwutai.ui.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.PoliticsAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.entity.mian.PoliticsBean;
import com.bupt.mountwutai.widget.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

public class PartyActivity extends BaseActivity {

    private NoScrollGridView gridView;
    List<PoliticsBean> politicsBeanList;
    PoliticsAdapter adapter;
    ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_party);
    }

    @Override
    protected void initView() {
        gridView = (NoScrollGridView) findViewById(R.id.party_grid);
        imageView1 = (ImageView) findViewById(R.id.party_img1);
        imageView2 = (ImageView) findViewById(R.id.party_img2);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partyintent(MainData.party1construction1);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partyintent(MainData.party2construction2);
            }
        });
        politicsBeanList = new ArrayList<>();
        initData();
        adapter = new PoliticsAdapter(this, politicsBeanList);
        gridView.setAdapter(adapter);
    }

    private void initData() {
        politicsBeanList.clear();
        politicsBeanList.add(new PoliticsBean(R.mipmap.party_service, "党员服务"));
        politicsBeanList.add(new PoliticsBean(R.mipmap.party_materials, "党建资料"));
        politicsBeanList.add(new PoliticsBean(R.mipmap.advanced_commendation, "先进表彰"));
        politicsBeanList.add(new PoliticsBean(R.mipmap.party_channel, "党建频道"));
        politicsBeanList.add(new PoliticsBean(R.mipmap.party_pay, "交党费"));
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return getIntent().getStringExtra(CodeConstants.ID);
    }

    public void partyintent(String ids) {
        Intent localproductsintent = new Intent(this, Party2Activity.class);
        localproductsintent.putExtra(CodeConstants.ID, ids);
        startActivity(localproductsintent);
    }
}

