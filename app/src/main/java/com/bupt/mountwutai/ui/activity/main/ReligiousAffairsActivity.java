package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.customdata.MainData;

/**
 * 宗教事务
 */
public class ReligiousAffairsActivity extends BaseActivity implements View.OnClickListener {

    private TextView titleTextView,contentTextView;
    private RelativeLayout centerRelativeLayout;
    private TextView centerarrow;
    private LinearLayout centertxtLinearLayout;
    private boolean isCenterarrowup;
    private String content = "一、主要职能　　\n" +
            "\n" +
            "        （一）贯彻执行党和国家、省有关民族、宗教、统战、台湾事务等方面的法律法规、规章和方针政策，研究起草规范性文件并组织实施。    \n" +
            "\n" +
            "        （二）按照授权和委托，负责景区相关行政许可事项的办理和监督管理。    \n" +
            "\n" +
            "        （三）负责景区内民族、宗教事务管理工作。    \n" +
            "\n" +
            "        （四）依法保护少数民族合法权益，协调处理民族关系中的重大事项。    \n" +
            "\n" +
            "        （五）负责宗教活动场所的登记和管理工作。    \n" +
            "\n" +
            "        （六）指导宗教团体和宗教人员依法依章开展活动，维护宗教团体和宗教人员的合法权益。   \n" +
            "\n" +
            "        （七）处理宗教突发事件，防范利用宗教进行的非法、违法活动，抵御境外利用宗教进行的渗透活动。    \n" +
            "\n" +
            "        （八）负责统一战线和台湾事务工作。\n" +
            "\n" +
            "二、内设机构　　\n" +
            "\n" +
            "        根据以上职责，五台山风景名胜区宗教事务局设3个职能股（室）。    \n" +
            "\n" +
            "        （一）办公室　　\n" +
            "\n" +
            "        负责机关文电、会务、督查督办、机要保密、档案和应急值班、信访工作；负责机关政府信息公开工作和对内对外宣传报道工作；承担机关对外交往工作；承担宗教界人士出境办理港澳台通行证、护照的初审工作；承担机关行政管理、后勤保障、安全保卫等工作。    \n" +
            "\n" +
            "        （二）民族宗教股　　\n" +
            "\n" +
            "        贯彻执行党和国家、省有关民族、宗教等方面的法律法规、规章和方针政策，研究起草规范性文件并组织实施；负责景区内民族、宗教事务管理工作；依法保护少数民族合法权益，协调处理民族关系中的重大事项；负责宗教活动场所的登记和管理工作；指导宗教团体和宗教人员依法依章开展活动，维护宗教团体和宗教人员的合法权益；处理宗教突发事件，防范利用宗教进行的非法、违法活动，抵御境外利用宗教进行的渗透活动。    \n" +
            "\n" +
            "        （三）统战股　　\n" +
            "\n" +
            "        贯彻执行党和国家、省有关统战、台湾事务等方面的法律法规、规章和方针政策，研究起草规范性文件并组织实施；负责统一战线和台湾事务工作；负责联系景区宗教界人士的政治安排和培养、选拔、考察、推荐等工作；负责宗教界后备干部和新的代表人物队伍建设。\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_religious_affairs);
    }

    @Override
    protected void initView() {
        titleTextView = (TextView) findViewById(R.id.travel_strategy_title);
        centertxtLinearLayout = (LinearLayout) findViewById(R.id.center_txt);
        centerRelativeLayout = (RelativeLayout) findViewById(R.id.center);
        centerarrow = (TextView) findViewById(R.id.center_arrow);
        titleTextView.setText("五台山风景名胜区宗教事务局");
        centerRelativeLayout.setOnClickListener(this);
        contentTextView = (TextView) findViewById(R.id.traffic_content1);
        contentTextView.setText(content);
        setViewVisible(findViewById(R.id.traffic_title1),false);
        setViewVisible(findViewById(R.id.traffic_title2),false);
        setViewVisible(findViewById(R.id.traffic_content2),false);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return MainData.ReligiousAffairs;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center:
                if (isCenterarrowup) {
                    centerarrow.setBackgroundResource(R.mipmap.trafficup);
                    isCenterarrowup = false;
                    setViewVisible(centertxtLinearLayout, true);
                } else {
                    centerarrow.setBackgroundResource(R.mipmap.trafficdown);
                    isCenterarrowup = true;
                    setViewVisible(centertxtLinearLayout, false);
                }
                break;
        }
    }

    private void setViewVisible(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
