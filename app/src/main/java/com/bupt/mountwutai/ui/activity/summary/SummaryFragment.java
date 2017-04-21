package com.bupt.mountwutai.ui.activity.summary;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CallBack;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.ui.activity.CommonFragment;
import com.bupt.mountwutai.util.Utils;
import java.util.ArrayList;

/**
 * 概览
 */

public class SummaryFragment extends BaseFragment {

    //下拉按钮
    TextView myButton;
    ImageView popImage;
    LinearLayout mypoplayout;
    Boolean isdown = true;
    //PopupWindow对象声明
    PopupWindow pw;

    ArrayList<String> list;
    //当前选中的列表项位置
    int clickPsition = -1;

    private FrameLayout frameLayout;
    private CommonFragment frgment1;
    private CommonWebViewFragment frgment2,frgment3,frgment4;
    private FragmentManager fManager;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_summary);
        fManager = getChildFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        frameLayout = (FrameLayout) findViewById(R.id.fragment_summary);
        initView();
        if(!frgment4.isAdded()){
            fTransaction.add(R.id.fragment_summary,frgment4);
        }else{
            fTransaction.show(frgment4);
        }
        fTransaction.commitAllowingStateLoss();
    }

    private void initView() {
        frgment1 = CommonFragment.newFragment(CodeConstants.TEPMLE_SUMMARY);
        frgment2 = CommonWebViewFragment.newFragment(CodeConstants.LOCAL_CUSTOM);
        frgment3 = CommonWebViewFragment.newFragment(CodeConstants.BUDDHIST_HOLY_LAND);
        frgment4 = CommonWebViewFragment.newFragment(CodeConstants.HISTORIC_LEGENDS);

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
                showPopWindow();
            }
        });
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(frgment1.isAdded())fragmentTransaction.hide(frgment1);
        if(frgment2.isAdded())fragmentTransaction.hide(frgment2);
        if(frgment3.isAdded())fragmentTransaction.hide(frgment3);
        if(frgment4.isAdded())fragmentTransaction.hide(frgment4);
    }

    //展示popwindow
    private void showPopWindow() {
        if (isdown) {
            isdown = false;
            myButton.setBackgroundResource(R.mipmap.sanjiao);
            popImage.setImageResource(R.mipmap.up);
        }
        Utils.showPopupwindow(activity, list, inflater,
                mypoplayout, new CallBack() {
                    @Override
                    public void itemClick(int position) {
                        myButton.setText(list.get(position));
                        FragmentTransaction fTransaction = fManager.beginTransaction();
                        hideAllFragment(fTransaction);
                        switch (position) {
                            case 0://历史传说
                                if(!frgment4.isAdded()){
                                    fTransaction.add(R.id.fragment_summary,frgment4);
                                }else{
                                    fTransaction.show(frgment4);
                                }
                                break;

                            case 1://地方风情
                                if(!frgment2.isAdded()){
                                    fTransaction.add(R.id.fragment_summary,frgment2);
                                }else{
                                    fTransaction.show(frgment2);
                                }
                                break;

                            case 2://佛教圣地
                                if(!frgment3.isAdded()){
                                    fTransaction.add(R.id.fragment_summary,frgment3);
                                }else{
                                    fTransaction.show(frgment3);
                                }
                                break;

                            case 3://寺庙一览
                                if(!frgment1.isAdded()){
                                    fTransaction.add(R.id.fragment_summary,frgment1);
                                }else{
                                    fTransaction.show(frgment1);
                                }
                                break;
                            default:
                                break;
                        }
                        if (clickPsition != position) {
                            clickPsition = position;
                        }
                        fTransaction.commitAllowingStateLoss();
                    }

                    @Override
                    public void dismiss() {
                        isdown = true;
                        myButton.setBackgroundResource(R.color.transparent);
                        popImage.setImageResource(R.mipmap.down);
                    }
                });

    }

    //得到list集合的方法
    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("历史传说");
        list.add("地方风情");
        list.add("佛教圣地");
        list.add("寺庙一览");
        return list;
    }
}
