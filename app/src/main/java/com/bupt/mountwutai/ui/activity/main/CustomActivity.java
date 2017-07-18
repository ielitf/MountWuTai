package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CustomAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.entity.mian.CustomBean;
import com.bupt.mylibrary.utils.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * 自定义界面
 */
public class CustomActivity extends BaseActivity {

    private RelativeLayout customTop;
    private TextView customName;
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
        customTop.setVisibility(View.VISIBLE);
        customName.setText("自定义");
        listView = (ListView) findViewById(R.id.custom_list);
        addData();
        adapter = new CustomAdapter(CustomActivity.this, mData);
        listView.setAdapter(adapter);
    }

    private void addData() {
        mData=new ArrayList<>();
        try {
            for (int i = 0; i <13 ; i++) {
                JSONObject oj=new JSONObject(ViewUtils.getData(activity,i+""));
                mData.add(new CustomBean(oj.getInt("picture"),oj.getString("title"),oj.getBoolean("isadd")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
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
