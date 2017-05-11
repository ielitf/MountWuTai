package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mountwutai.consts.CodeConstants;

/**
 * 宗教事务
 */
public class ReligiousAffairsActivity extends BaseActivity implements View.OnClickListener {

    private TextView titleTextView, contentTextView;
    private RelativeLayout centerRelativeLayout;
    private TextView centerarrow;
    private LinearLayout centertxtLinearLayout;
    private boolean isCenterarrowup;
    private String content, title, controlTitle;
    private String[] controls = {"五台山风景名胜区宗教事务局","五台山风景名胜区文物和遗产保护局"
    ,"五台山国家森林管理处"};
    private String forest_fire_content =
            "        负责制止各种破坏森林和野生动植物资源的违法行为，协助配合有关部门开展执法活动。     \n" +
            "\n" +
            "        负责巡护森林、森林防火安全宣传，管理野外用火，及时报告火情，扑救森林火灾，协助有关机关调查森林火灾案件。定期进行森林防火培训和演练。    \n";
    private String relics_Protect_content = "一、主要职能    \n" +
            "\n" +
            "        （一）贯彻执行党和国家以及省有关文化、文物保护、体育、新闻出版和著作权管理、文化遗产保护等方面的法律法规、规章和方针政策，研究起草规范性文件并组织实施。    \n" +
            "\n" +
            "        （二）按照授权和委托，负责景区相关行政许可事项的办理和监督管理。    \n" +
            "\n" +
            "        （三）负责景区文物保护、考古和博物馆业务管理工作，组织协调重大文物保护和考古项目的实施。 \n" +
            "\n" +
            "        （四）负责文学艺术事业、社会文化事业、体育事业、文化市场管理工作，管理重大文化活动，组织实施对外文化交流活动。\n" +
            "\n" +
            "        （五）组织实施非物质文化遗产保护和优秀民族文化的传承普及。    \n" +
            "\n" +
            "        （六）负责新闻出版和著作权管理。    \n" +
            "\n" +
            "        （七）组织协调“扫黄打非”工作；负责世界文化遗产的规划、保护和管理工作；承担忻州市五台山世界遗产保护工作领导组日常工作。\n" +
            "\n" +
            "二、内设机构　　\n" +
            "\n" +
            "        根据以上职责，五台山风景名胜区文物和遗产保护局设2个职能股（室）。\n" +
            "\n" +
            "        （一）文化股\n" +
            "\n" +
            "        贯彻执行党和国家以及省有关文化、体育、新闻出版和著作权管理等方面的法律法规、规章和方针政策，研究起草规范性文件并组织实施；负责文化艺术事业、社会文化事业、体育事业、文化市场管理工作，管理重大文化活动，组织实施对外文化交流活动；负责新闻出版和著作权管理；组织实施优秀民族文化传承普及；组织协调“扫黄打非”工作。\n" +
            "\n" +
            "        （二）文物遗产保护股\n" +
            "\n" +
            "        贯彻执行党和国家以及省有关文物保护、文化遗产保护等方面的法律法规、规章和方针政策，研究起草规范性文件并组织实施；负责景区文物保护、考古和博物馆业务管理工作，组织协调重大文物保护、非物质文化遗产保护和考古项目的实施；负责世界文化遗产规划、保护和管理工作；承担忻州市五台山世界遗产保护工作领导组日常工作。\n";
    private String religious_affairs_content = "一、主要职能　　\n" +
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
        title = getIntent().getStringExtra(CodeConstants.ID);
        String type = getIntent().getStringExtra(CodeConstants.TYPE);
        switch (type) {
            case CodeConstants.RELIGIOUSAFFAIRS:
                controlTitle = controls[0];
                content = religious_affairs_content;
                break;

            case CodeConstants.RELICSPROTECT:
                controlTitle = controls[1];
                content = relics_Protect_content;
                break;

            case CodeConstants.FORESTFIRE:
                controlTitle = controls[2];
                content = forest_fire_content;
                break;
        }
        titleTextView = (TextView) findViewById(R.id.travel_strategy_title);
        centertxtLinearLayout = (LinearLayout) findViewById(R.id.center_txt);
        centerRelativeLayout = (RelativeLayout) findViewById(R.id.center);
        centerarrow = (TextView) findViewById(R.id.center_arrow);
        titleTextView.setText(controlTitle);
        centerRelativeLayout.setOnClickListener(this);
        contentTextView = (TextView) findViewById(R.id.traffic_content1);
        contentTextView.setText(content);
        setViewVisible(findViewById(R.id.traffic_title1), false);
        setViewVisible(findViewById(R.id.traffic_title2), false);
        setViewVisible(findViewById(R.id.traffic_content2), false);
    }

    @Override
    protected boolean isNeedInitBack() {
        return true;
    }

    @Override
    protected String getTopbarTitle() {
        return title;
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
