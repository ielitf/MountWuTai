package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CardRecordAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.entity.Consumption;

import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;

public class RequstTvActivity extends BaseActivity {

    private RefreshRecyclerView mRecyclerView;
    private CardRecordAdapter mAdapter;
    private Handler mHandler;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_requst_tv);
    }

    @Override
    protected void initView() {
        mHandler = new Handler();
        mAdapter = new CardRecordAdapter(this);
        initRecycleView();
    }

    private void initRecycleView() {
        mRecyclerView = (RefreshRecyclerView) findViewById(R.id.requst_tv_view);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setRefreshAction(new Action() {
            @Override
            public void onAction() {
                getData(true);
            }
        });

        mRecyclerView.setLoadMoreAction(new Action() {
            @Override
            public void onAction() {
                getData(false);
                page++;
            }

        });

        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.showSwipeRefresh();
                getData(true);
            }
        });
    }

    private void getData(final boolean isRefresh) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRefresh) {
                    page = 1;
                    mAdapter.clear();
                    mAdapter.addAll(getVirtualData());
                    mRecyclerView.dismissSwipeRefresh();
                    mRecyclerView.getRecyclerView().scrollToPosition(0);
                } else {
                    mAdapter.addAll(getVirtualData());
                    if (page >= 3) {
                        mRecyclerView.showNoMore();
                    }
                }
            }
        }, 1000);
    }

    public Consumption[] getVirtualData() {
        return new Consumption[]{
                new Consumption(R.mipmap.requst_tv_1,""),
                new Consumption(R.mipmap.requst_tv_2,""),
                new Consumption(R.mipmap.requst_tv_3,""),
                new Consumption(R.mipmap.requst_tv_4,""),
                new Consumption(R.mipmap.requst_tv_5,""),
                new Consumption(R.mipmap.requst_tv_2,""),
                new Consumption(R.mipmap.requst_tv_4,""),
                new Consumption(R.mipmap.requst_tv_1,"")
        };
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return "景区点播";
    }
}
