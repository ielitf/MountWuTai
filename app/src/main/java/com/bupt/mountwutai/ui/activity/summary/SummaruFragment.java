package com.bupt.mountwutai.ui.activity.summary;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.util.LogUtil;

/**
 * Created by Wyf on 2017/4/18.
 */

public class SummaruFragment extends BaseFragment {

    private Spinner topSpinner;
    private boolean isSelect;
    private TextView arrawTextView;
    private static final String[] name = {"寺庙一览", "地方风情", "佛教圣地", "历史传说"};

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_summary);
        initView();
    }

    private void initView() {
        topSpinner = (Spinner) findViewById(R.id.topspinner);
        arrawTextView = (TextView) findViewById(R.id.arraw);
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, R.layout.spinner_item, name);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        topSpinner.setAdapter(arrayAdapter);

        topSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    LogUtil.i(TAG, "========");
                    if (isSelect) {
                        arrawTextView.setBackgroundResource(R.mipmap.geren_normal);
                        isSelect = false;
                    } else {
                        arrawTextView.setBackgroundResource(R.mipmap.geren_selected);
                        isSelect = true;
                    }
                }
                return false;
            }
        });

        topSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrawTextView.setBackgroundResource(R.mipmap.geren_normal);
                isSelect = false;
                showToast(name[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtil.i(TAG,"nothing");
                arrawTextView.setBackgroundResource(R.mipmap.geren_normal);
                isSelect = false;
            }
        });

    }
}
