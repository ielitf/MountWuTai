package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CustomAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.entity.CustomBean;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

import static com.bupt.mountwutai.util.ChString.type;


/**
 * 自定义界面
 */
public class CustomActivity extends BaseActivity {

    private RelativeLayout customTop;
    private TextView customName;
    private ImageButton custombutton;
    private ArrayList<CustomBean> mData;
    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        customTop = (RelativeLayout) findViewById(R.id.custom_fragment_top);
        customName = (TextView) findViewById(R.id.top_name_text);
        custombutton= (ImageButton) findViewById(R.id.top_back_btn);
        custombutton.setVisibility(View.VISIBLE);
        custombutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        customTop.setVisibility(View.VISIBLE);
        customName.setText("自定义");
        listView = (ListView) findViewById(R.id.custom_list);
        addData();
        adapter = new CustomAdapter(CustomActivity.this, mData);
        listView.setAdapter(adapter);
    }

    private void addData() {
        mData=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i%2==0){
                mData.add(new CustomBean(R.mipmap.amap_bus, "呵呵", true));
            }else{
                mData.add(new CustomBean(R.mipmap.amap_bus, "呵呵", false));
            }
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_custom);
    }

}
