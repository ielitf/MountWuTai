package com.bupt.mountwutai.ui.activity.summary;

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
import com.bupt.mountwutai.widget.NoScrollListView;

import java.util.ArrayList;

/**
 * Created by Wyf on 2017/4/18.
 */

public class SummaryFragment extends BaseFragment {

    //下拉按钮
    TextView myButton;
    ImageView popImage;
    LinearLayout mypoplayout;
    Boolean isup = true;
    //PopupWindow对象声明
    PopupWindow pw;

    ArrayList<String> list;
    //当前选中的列表项位置
    int clickPsition = -1;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_summary);
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

                //通过布局注入器，注入布局给View对象
                View myView = inflater.inflate(R.layout.pop, null);
                //通过view 和宽·高，构造PopopWindow
                pw = new PopupWindow(myView, 300, LinearLayout.LayoutParams.WRAP_CONTENT, true);

                pw.setBackgroundDrawable(getResources().getDrawable(
                        //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
                        R.color.blue));
                //设置焦点为可点击
                pw.setFocusable(true);//可以试试设为false的结果
                //将window视图显示在myButton下面
                pw.showAsDropDown(mypoplayout);

                NoScrollListView lv = (NoScrollListView) myView.findViewById(R.id.lv_pop);
                lv.setAdapter(new PopAdapter(getActivity(), list));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        myButton.setText(list.get(position));
                        if (clickPsition != position) {
                            clickPsition = position;
                        }
                        pw.dismiss();
                    }
                });
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
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
        list.add("寺庙一览");
        list.add("地方风情");
        list.add("佛教圣地");
        list.add("历史传说");
        return list;
    }
}
