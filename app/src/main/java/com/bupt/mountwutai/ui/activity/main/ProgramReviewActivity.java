package com.bupt.mountwutai.ui.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.PopAdapter;
import com.bupt.mountwutai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ProgramReviewActivity extends BaseActivity {

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
    }

    private void addDate() {
        list = new ArrayList<>();
        list.add("央视");
        list.add("卫视");
        list.add("高清");
        list.add("山西");
        list.add("五台山");
        list.add("其他");
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "节目预告";
    }
}
