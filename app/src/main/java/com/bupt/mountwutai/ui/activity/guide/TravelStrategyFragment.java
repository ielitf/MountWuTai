package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 * 朝台攻略
 */

public class TravelStrategyFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout centerRelativeLayout,classiclineRelativeLayout,bestsuitRelativeLayout;
    private TextView centerarrow,hot_line_arrow,best_times_arrow;
    private TextView classic_scenic_TextView, hot_line_TextView, best_times_TextView;
    private LinearLayout classic_scenic_LinearLayout, hot_line_LinearLayout, best_times_LinearLayout;

    private LinearLayout centertxtLinearLayout,hotlinetxtLinearLayout,besttimestxtLinearLayout;
    private boolean isCenterarrowup,isHotlinearrowup,isBesttimesarrow;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_travel_strategy);

        initView();
    }

    @Override
    protected boolean hasPopWindow() {
        return false;
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }

    private void initView() {
        centertxtLinearLayout = (LinearLayout) findViewById(R.id.center_txt);//经典景色-中心区必有景点-内容
        hotlinetxtLinearLayout = (LinearLayout) findViewById(R.id.hot_line__txt);//热门路线-经典路线-内容
        besttimestxtLinearLayout = (LinearLayout) findViewById(R.id.best_times_txt);//最佳时间-适宜气候-内容

        centerRelativeLayout = (RelativeLayout) findViewById(R.id.center);//经典景色-中心区必有景点
        classiclineRelativeLayout = (RelativeLayout) findViewById(R.id.classic_line);//热门路线-经典路线
        bestsuitRelativeLayout = (RelativeLayout) findViewById(R.id.best_times_suit);//最佳时间-适宜气候

        centerarrow = (TextView) findViewById(R.id.center_arrow);//经典景色-中心区必有景点-箭头
        hot_line_arrow = (TextView) findViewById(R.id.hot_line_arrow);//热门路线-经典路线-箭头
        best_times_arrow = (TextView) findViewById(R.id.best_times_arrow);//最佳时间-适宜气候-箭头

        classic_scenic_TextView = (TextView) findViewById(R.id.classic_scenic_text);//经典景色
        hot_line_TextView = (TextView) findViewById(R.id.hot_line_text);//热门路线
        best_times_TextView = (TextView) findViewById(R.id.best_times_text);//最佳时间

        classic_scenic_LinearLayout = (LinearLayout) findViewById(R.id.classic_scenic);//经典景色大的布局
        hot_line_LinearLayout = (LinearLayout) findViewById(R.id.hot_line);//热门路线大的布局
        best_times_LinearLayout = (LinearLayout) findViewById(R.id.best_times);//最佳时间大的布局

        bestsuitRelativeLayout.setOnClickListener(this);
        classiclineRelativeLayout.setOnClickListener(this);
        centerRelativeLayout.setOnClickListener(this);
        classic_scenic_TextView.setOnClickListener(this);
        hot_line_TextView.setOnClickListener(this);
        best_times_TextView.setOnClickListener(this);
    }

    private void setTextColor(TextView view, boolean isWhite) {
        view.setTextColor(getResources().getColor(isWhite ? R.color.white : R.color.black));
    }

    private void setViewVisible(View view, boolean isViible) {
        view.setVisibility(isViible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center:
                if (isCenterarrowup) {
                    centerarrow.setBackgroundResource(R.mipmap.trafficup);
                    isCenterarrowup = false;
                    setViewVisible(centertxtLinearLayout,true);
                } else {
                    centerarrow.setBackgroundResource(R.mipmap.trafficdown);
                    isCenterarrowup = true;
                    setViewVisible(centertxtLinearLayout,false);
                }
                break;

            case R.id.classic_line:
                if (isHotlinearrowup) {
                    hot_line_arrow.setBackgroundResource(R.mipmap.trafficup);
                    isHotlinearrowup = false;
                    setViewVisible(hotlinetxtLinearLayout,true);
                } else {
                    hot_line_arrow.setBackgroundResource(R.mipmap.trafficdown);
                    isHotlinearrowup = true;
                    setViewVisible(hotlinetxtLinearLayout,false);
                }
                break;

            case R.id.best_times_suit:
                if (isBesttimesarrow) {
                    best_times_arrow.setBackgroundResource(R.mipmap.trafficup);
                    isBesttimesarrow = false;
                    setViewVisible(besttimestxtLinearLayout,true);
                } else {
                    best_times_arrow.setBackgroundResource(R.mipmap.trafficdown);
                    isBesttimesarrow = true;
                    setViewVisible(besttimestxtLinearLayout,false);
                }
                break;

            case R.id.classic_scenic_text://经典景色按钮
                classic_scenic_TextView.setBackgroundResource(R.drawable.corners_left_selected);
                setTextColor(classic_scenic_TextView, true);
                setViewVisible(classic_scenic_LinearLayout,true);
                hot_line_TextView.setBackgroundResource(R.drawable.corners_center_unselected);
                setViewVisible(hot_line_LinearLayout,false);
                setTextColor(hot_line_TextView, false);
                best_times_TextView.setBackgroundResource(R.drawable.corners_right_unselected);
                setViewVisible(best_times_LinearLayout,false);
                setTextColor(best_times_TextView, false);
                break;

            case R.id.hot_line_text://热门路线按钮
                classic_scenic_TextView.setBackgroundResource(R.drawable.corners_left_unselected);
                setTextColor(classic_scenic_TextView, false);
                setViewVisible(classic_scenic_LinearLayout,false);
                hot_line_TextView.setBackgroundResource(R.drawable.corners_center_selected);
                setTextColor(hot_line_TextView, true);
                setViewVisible(hot_line_LinearLayout,true);
                best_times_TextView.setBackgroundResource(R.drawable.corners_right_unselected);
                setViewVisible(best_times_LinearLayout,false);
                setTextColor(best_times_TextView, false);
                break;

            case R.id.best_times_text://最佳时间按钮
                classic_scenic_TextView.setBackgroundResource(R.drawable.corners_left_unselected);
                setTextColor(classic_scenic_TextView, false);
                setViewVisible(classic_scenic_LinearLayout,false);
                hot_line_TextView.setBackgroundResource(R.drawable.corners_center_unselected);
                setTextColor(hot_line_TextView, false);
                setViewVisible(hot_line_LinearLayout,false);
                best_times_TextView.setBackgroundResource(R.drawable.corners_right_selected);
                setViewVisible(best_times_LinearLayout,true);
                setTextColor(best_times_TextView, true);
                break;
        }
    }
}
