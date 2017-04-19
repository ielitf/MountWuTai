package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CallBack;
import com.bupt.mountwutai.util.Utils;

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

    TrafficGuideFragment trafficGuideFragment = null;

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
                    myButton.setBackgroundResource(R.mipmap.sanjiao);
                    popImage.setImageResource(R.mipmap.up);
                }

                Utils.showPopupwindow(activity, list, inflater,
                        mypoplayout, new CallBack() {
                            @Override
                            public void itemClick(int position) {
                                myButton.setText(list.get(position));
                                switch (position){
                                    case 0://行程规划

                                        break;

                                    case 1://交通指南

                                        break;

                                    case 2://酒店

                                        break;

                                    case 3://五台食谱

                                        break;

                                    case 4://朝台攻略

                                        break;
                                }
                                if (clickPsition != position) {
                                    clickPsition = position;
                                }
                            }

                            @Override
                            public void dismiss() {
                                isup = true;
                                myButton.setBackgroundResource(R.color.transparent);
                                popImage.setImageResource(R.mipmap.down);
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
