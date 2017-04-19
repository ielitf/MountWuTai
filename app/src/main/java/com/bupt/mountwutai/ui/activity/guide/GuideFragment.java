package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.PopAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CallBack;
import com.bupt.mountwutai.util.Utils;
import com.bupt.mountwutai.widget.NoScrollListView;

import java.util.ArrayList;

/**
 * 导游
 */

public class GuideFragment extends BaseFragment {

    //下拉按钮
    TextView myButton;
    ImageView popImage;
    LinearLayout mypoplayout;
    Boolean isup = true;

    ArrayList<String> list;
    //当前选中的列表项位置
    int clickPsition = -1;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_guide);
        initView();
    }

    private void initView() {
        mypoplayout = (LinearLayout) findViewById(R.id.mypoplayout);
        myButton = (TextView) findViewById(R.id.myButton);
        popImage = (ImageView) findViewById(R.id.popimg);
        //获得要显示的数据
        list = getList();
        //设置默认显示的Text
        myButton.setText(list.get(0));
        mypoplayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isup) {
                    isup = false;
                    popImage.setImageResource(R.mipmap.spinner_bg);
                }

                Utils.showPopupwindow(activity, list, inflater,
                        mypoplayout, new CallBack() {
                            @Override
                            public void itemClick(int position) {
                                myButton.setText(list.get(position));
                                if (clickPsition != position) {
                                    clickPsition = position;
                                }
                            }

                            @Override
                            public void dismiss() {
                                isup = true;
                                popImage.setImageResource(R.mipmap.spinner_bg_press);
                            }
                        });

            }
        });
    }

    /**
     * 得到list集合的方法
     *
     * @return
     */
    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("行程规划");
        list.add("交通指南");
        list.add("酒店");
        list.add("五台食谱");
        list.add("朝台攻略");
        return list;
    }
}
