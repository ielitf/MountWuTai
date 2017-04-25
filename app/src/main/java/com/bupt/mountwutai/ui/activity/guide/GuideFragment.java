package com.bupt.mountwutai.ui.activity.guide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CallBack;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonFragment;
import com.bupt.mountwutai.util.LogUtil;
import com.bupt.mountwutai.util.Utils;

import java.util.ArrayList;

/**
 * 导游
 */

public class GuideFragment extends BaseFragment {

    //下拉按钮
//    TextView myButton;
//    ImageView popImage;
//    LinearLayout mypoplayout;
    Boolean isup = true;

    ArrayList<String> list;
    //当前选中的列表项位置
    int clickPsition = -1;

    private FragmentManager fragmentManager;
    private TrafficGuideFragment trafficGuideFragment = null;//旅游规划
    private TravelPlanFragment travelPlanFragment = null;//交通指南
    private TravelStrategyFragment travelStrategyFragment = null;//朝台攻略
    private CommonFragment commonFragment = null;//五台食谱

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_guide);
        initView();
    }

    @Override
    protected boolean hasPopWindow() {
        return true;
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return getList().get(0);
    }

    @Override
    protected void showPopWindow(LinearLayout myPopLayout,final TextView titleText, final ImageView popImage) {
        super.showPopWindow(myPopLayout, titleText, popImage);
        if (isup) {
            isup = false;
            titleText.setBackgroundResource(R.mipmap.sanjiao);
            popImage.setImageResource(R.mipmap.up);
        }

        Utils.showPopupwindow(activity, list, inflater,
                myPopLayout, new CallBack() {
                    @Override
                    public void itemClick(int position) {
                        titleText.setText(list.get(position));
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        hideFragments(transaction);
                        switch (position){
                            case 0://行程规划
                                if (travelPlanFragment == null) {
                                    travelPlanFragment = new TravelPlanFragment();
                                    transaction.add(R.id.guide_container, travelPlanFragment);
                                } else {
                                    transaction.show(travelPlanFragment);
                                }
                                break;

                            case 1://交通指南
                                if (trafficGuideFragment == null) {
                                    trafficGuideFragment = new TrafficGuideFragment();
                                    transaction.add(R.id.guide_container, trafficGuideFragment);
                                } else {
                                    transaction.show(trafficGuideFragment);
                                }
                                break;

                            case 2://酒店

                                break;

                            case 3://五台食谱
                                if (commonFragment == null) {
                                    commonFragment = CommonFragment.newFragment(CodeConstants.WUTAI_RECIPES);
                                    transaction.add(R.id.guide_container, commonFragment);
                                } else {
                                    transaction.show(commonFragment);
                                }
                                break;

                            case 4://朝台攻略
                                if (travelStrategyFragment == null) {
                                    travelStrategyFragment = new TravelStrategyFragment();
                                    transaction.add(R.id.guide_container, travelStrategyFragment);
                                } else {
                                    transaction.show(travelStrategyFragment);
                                }
                                break;
                        }
                        transaction.commitAllowingStateLoss();
                        if (clickPsition != position) {
                            clickPsition = position;
                        }
                    }

                    @Override
                    public void dismiss() {
                        isup = true;
                        titleText.setBackgroundResource(R.color.transparent);
                        popImage.setImageResource(R.mipmap.down);
                    }
                });
    }

    private void hideFragments(FragmentTransaction transaction) {
        LogUtil.i(TAG, "hideFragments is called");
        if (trafficGuideFragment != null) {
            transaction.hide(trafficGuideFragment);
        }
        if (travelPlanFragment != null) {
            transaction.hide(travelPlanFragment);
        }
        if (travelStrategyFragment != null) {
            transaction.hide(travelStrategyFragment);
        }
        if (commonFragment != null) {
            transaction.hide(commonFragment);
        }
    }

    private void initView() {
        fragmentManager = getChildFragmentManager();
//        mypoplayout = (LinearLayout) findViewById(R.id.my_pop_layout);
//        myButton = (TextView) findViewById(R.id.top_name_text);
//        popImage = (ImageView) findViewById(R.id.poping_image);
        //获得要显示的数据
        list = getList();
        //设置默认显示的Text
//        myButton.setText(list.get(0));
        //初始化显示的页面
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        travelPlanFragment = new TravelPlanFragment();
        transaction.add(R.id.guide_container, travelPlanFragment);
        transaction.commitAllowingStateLoss();
//
//        mypoplayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.w(TAG,"onRequestPermissionsResult");
        getChildFragmentManager().getFragments().get(0).onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
