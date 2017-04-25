package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;

/**
 *
 */

public class BuddhistChildFragment extends BaseFragment {

    private WebView webView;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_buddhist_child);
        webView = (WebView) findViewById(R.id.webviewId);
        WebSettings wSet = webView.getSettings();
        wSet.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/img/imgShow.html");
    }

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
}
