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

    private RelativeLayout centerRelativeLayout;
    private TextView centerarrow;
    private TextView classic_scenic_TextView, hot_line_TextView, best_times_TextView;

    private LinearLayout centertxtLinearLayout;
    private boolean isCenterarrowup;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_travel_strategy);

        initView();
    }

    private void initView() {
        centertxtLinearLayout = (LinearLayout) findViewById(R.id.center_txt);
        centerRelativeLayout = (RelativeLayout) findViewById(R.id.center);
        centerarrow = (TextView) findViewById(R.id.center_arrow);
        classic_scenic_TextView = (TextView) findViewById(R.id.classic_scenic_text);
        hot_line_TextView = (TextView) findViewById(R.id.hot_line_text);
        best_times_TextView = (TextView) findViewById(R.id.best_times_text);
        centerRelativeLayout.setOnClickListener(this);
        classic_scenic_TextView.setOnClickListener(this);
        hot_line_TextView.setOnClickListener(this);
        best_times_TextView.setOnClickListener(this);
    }

    private void setTextColor(TextView view, boolean isWhite) {
        view.setTextColor(getResources().getColor(isWhite ? R.color.white : R.color.black));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center:
                if (isCenterarrowup) {
                    centerarrow.setBackgroundResource(R.mipmap.down);
                    isCenterarrowup = false;
                    centertxtLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    centerarrow.setBackgroundResource(R.mipmap.up);
                    isCenterarrowup = true;
                    centertxtLinearLayout.setVisibility(View.GONE);
                }
                break;

            case R.id.classic_scenic_text://经典景色按钮
                classic_scenic_TextView.setBackgroundResource(R.drawable.corners_left_selected);
                setTextColor(classic_scenic_TextView,true);
                hot_line_TextView.setBackgroundResource(R.drawable.corners_center_unselected);
                setTextColor(hot_line_TextView,false);
                best_times_TextView.setBackgroundResource(R.drawable.corners_right_unselected);
                setTextColor(best_times_TextView,false);
                break;

            case R.id.hot_line_text://热门路线按钮
                classic_scenic_TextView.setBackgroundResource(R.drawable.corners_left_unselected);
                setTextColor(classic_scenic_TextView,false);
                hot_line_TextView.setBackgroundResource(R.drawable.corners_center_selected);
                setTextColor(hot_line_TextView,true);
                best_times_TextView.setBackgroundResource(R.drawable.corners_right_unselected);
                setTextColor(best_times_TextView,false);
                break;

            case R.id.best_times_text://最佳时间按钮
                classic_scenic_TextView.setBackgroundResource(R.drawable.corners_left_unselected);
                setTextColor(classic_scenic_TextView,false);
                hot_line_TextView.setBackgroundResource(R.drawable.corners_center_unselected);
                setTextColor(hot_line_TextView,false);
                best_times_TextView.setBackgroundResource(R.drawable.corners_right_selected);
                setTextColor(best_times_TextView,true);
                break;
        }
    }
}
