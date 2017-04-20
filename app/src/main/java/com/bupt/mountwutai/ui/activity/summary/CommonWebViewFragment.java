package com.bupt.mountwutai.ui.activity.summary;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.CommonAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.consts.CodeConstants;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.entity.CommonBean;
import com.bupt.mountwutai.util.LogUtil;

import java.util.ArrayList;

/**
 * 概览 -- 地方风情，佛教圣地，历史传说
 * Created by litf on 2017/4/20.
 */

public class CommonWebViewFragment extends BaseFragment {
    private Context context;
    private String type;//代表当前是哪一个，比如地方风情，佛教圣地，历史传说，以添加不同的数据
    private ImageView imageView;
    private TextView textView;
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
        LogUtil.d("=====","进入寺庙一览");
        type = getArguments().getString(CodeConstants.TYPE);
        imageView = (ImageView) findViewById(R.id.common_web_imag);
        textView = (TextView) findViewById(R.id.common_web_tvContent);
        addData();
    }
    private void addData() {
        switch (type){
            case CodeConstants.LOCAL_CUSTOM://地方风情
                imageView.setImageResource(SummaryData.localCustom_img[0]);
                textView.setText(SummaryData.localCustom_text[0]);
                break;
            case CodeConstants.BUDDHIST_HOLY_LAND://佛教圣地
                imageView.setImageResource(SummaryData.buddhist_holy_land_img[0]);
                textView.setText(SummaryData.buddhist_holy_land_text[0]);
                break;
            case CodeConstants.HISTORIC_LEGENDS://历史传说
                imageView.setImageResource(SummaryData.historic_legends_img[0]);
                textView.setText(SummaryData.historic_legends_text[0]);
                break;
            default:
                break;
        }
    }
}
