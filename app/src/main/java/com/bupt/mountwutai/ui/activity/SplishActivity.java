package com.bupt.mountwutai.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;

public class SplishActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "-----");
                intent2Activity(MainActivity.class);
                finish();
            }
        }).start();
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_splish);
    }

    @Override
    protected void initView() {

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
