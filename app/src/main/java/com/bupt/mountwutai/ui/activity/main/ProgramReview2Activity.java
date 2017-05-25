package com.bupt.mountwutai.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.PopAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mountwutai.util.Utils;

import java.util.ArrayList;

public class ProgramReview2Activity extends BaseActivity {

    private PopAdapter adapter;
    private ListView listView;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_program_review);
    }

    @Override
    protected void initView() {
        addDate();
        adapter = new PopAdapter(activity, list);
        listView = (ListView) findViewById(R.id.pro_review_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.show(ProgramReview2Activity.this,"频道正在布置中");
            }
        });
    }

    private void addDate() {
        list = new ArrayList<>();
        for (int i = 0; i <16 ; i++) {

            list.add("频道"+i);
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
