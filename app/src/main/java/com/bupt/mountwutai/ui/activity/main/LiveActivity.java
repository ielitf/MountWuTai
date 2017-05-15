package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CardRecordAdapter;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.entity.Consumption;
import com.bupt.mountwutai.util.ToastUtil;
import com.bupt.mylibrary.utils.ViewUtils;

import cn.lemon.view.RefreshRecyclerView;
import cn.lemon.view.adapter.Action;

public class LiveActivity extends BaseActivity {

    private RefreshRecyclerView mRecyclerView;
    private CardRecordAdapter mAdapter;
    private Handler mHandler;
    private int page = 1;

    private RelativeLayout liveTop;
    private TextView liveName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        liveTop = (RelativeLayout) findViewById(R.id.live_top);
        liveName = (TextView) findViewById(R.id.top_name_text);
        liveTop.setVisibility(View.VISIBLE);
        liveName.setText("景区直播");


        mHandler = new Handler();
        mAdapter = new CardRecordAdapter(this);
        //添加Header
        final TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(activity,48)));
        textView.setTextSize(16);
        textView.setGravity(Gravity.CENTER);
        textView.setText("下拉刷新");
        //mAdapter.setHeader(textView);
        //添加footer
        final TextView footer = new TextView(this);
        footer.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(activity,48)));
        footer.setTextSize(16);
        footer.setGravity(Gravity.CENTER);
        footer.setText("上拉加载");
        //mAdapter.setFooter(footer);
        mRecyclerView = (RefreshRecyclerView) findViewById(R.id.recycler_view);
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

    public void getData(final boolean isRefresh) {
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
        }, 1500);
    }


    public Consumption[] getVirtualData() {
        return new Consumption[]{
                new Consumption(R.mipmap.tayuansi,"塔院寺"),
                new Consumption(R.mipmap.wuyemiao,"五爷庙"),
                new Consumption(R.mipmap.tayuansi,"塔院寺"),
                new Consumption(R.mipmap.wuyemiao,"五爷庙"),
                new Consumption(R.mipmap.tayuansi,"塔院寺"),
                new Consumption(R.mipmap.wuyemiao,"五爷庙"),
                new Consumption(R.mipmap.tayuansi,"塔院寺"),
                new Consumption(R.mipmap.wuyemiao,"五爷庙"),
                new Consumption(R.mipmap.tayuansi,"塔院寺"),
                new Consumption(R.mipmap.wuyemiao,"五爷庙")
        };
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
        setContentView(R.layout.activity_live);
    }
}
