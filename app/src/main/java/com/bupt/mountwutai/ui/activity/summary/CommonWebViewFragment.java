package com.bupt.mountwutai.ui.activity.summary;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.SummaryData;

/**
 * 概览 -- 地方风情，佛教圣地，历史传说
 * Created by litf on 2017/4/20.
 */

public class CommonWebViewFragment extends BaseFragment {
    private Context context;
    private String type;//代表当前是哪一个，比如地方风情，佛教圣地，历史传说，以添加不同的数据
    private TextView textView_title;
    private ImageView[] imageView = new ImageView[6];
    private TextView[] textView = new TextView[6];
    public CommonWebViewFragment(){
    }

    public static CommonWebViewFragment newFragment(String type){
        CommonWebViewFragment commonFragment = new CommonWebViewFragment();
        Bundle args = new Bundle();
        args.putString(CodeConstants.TYPE,type);
        commonFragment.setArguments(args);
        return commonFragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_common_webview);
        context = getActivity();
        type = getArguments().getString(CodeConstants.TYPE);
        initViews();
        addData();
    }

    /**
     * 为true时，回到父类showPopWindow方法，在子类中重写该方法
     * @return
     */
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

    private void initViews() {
        imageView[0] = (ImageView) findViewById(R.id.common_web_imag);
        imageView[1] = (ImageView) findViewById(R.id.common_web_imag2);
        imageView[2] = (ImageView) findViewById(R.id.common_web_imag3);
        imageView[3] = (ImageView) findViewById(R.id.common_web_imag4);
        imageView[4] = (ImageView) findViewById(R.id.common_web_imag5);
        imageView[5] = (ImageView) findViewById(R.id.common_web_imag6);
        textView_title = (TextView) findViewById(R.id.common_web_tvContent_title);
        textView[0] = (TextView) findViewById(R.id.common_web_tvContent);
        textView[1] = (TextView) findViewById(R.id.common_web_tvContent2);
        textView[2] = (TextView) findViewById(R.id.common_web_tvContent3);
        textView[3] = (TextView) findViewById(R.id.common_web_tvContent4);
        textView[4] = (TextView) findViewById(R.id.common_web_tvContent5);
        textView[5] = (TextView) findViewById(R.id.common_web_tvContent6);
    }

    private void addData() {
        switch (type){
            case CodeConstants.LOCAL_CUSTOM://地方风情
                imageView[0].setImageResource(SummaryData.localCustom_img[0]);
                textView[0].setText(SummaryData.localCustom_text[0]);
                break;
            case CodeConstants.BUDDHIST_HOLY_LAND://佛教圣地
                textView_title.setVisibility(View.VISIBLE);
                imageView[0].setImageResource(SummaryData.buddhist_holy_land_img[0]);
                textView[0].setText(SummaryData.buddhist_holy_land_text[0]);
                for (int i = 1;i<=imageView.length-1;i++){
                    imageView[i].setVisibility(View.VISIBLE);
                    textView[i].setVisibility(View.VISIBLE);
                    imageView[i].setImageResource(SummaryData.buddhist_holy_land_img[i]);
                    textView[i].setText(SummaryData.buddhist_holy_land_text[i]);
                }
                break;
            case CodeConstants.HISTORIC_LEGENDS://历史传说
                imageView[0].setImageResource(SummaryData.historic_legends_img[0]);
                textView[0].setText(SummaryData.historic_legends_text[0]);
                break;
            default:
                break;
        }
    }
}
