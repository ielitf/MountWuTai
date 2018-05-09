package com.bupt.mountwutai.ui.activity.vr;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.util.ToastUtil;

public class VRFragment extends BaseFragment implements View.OnClickListener{
    private Context context;
    private RelativeLayout topPublishLayout;
    private ListView vrListView;

    public static VRFragment newFragment() {
        VRFragment vrFragment = new VRFragment();
        return vrFragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_vr);
        context =getActivity();
        initViews();
        topPublishLayout.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        topPublishLayout = (RelativeLayout) findViewById(R.id.top_publish_layout);
        topPublishLayout.setOnClickListener(this);
        vrListView = (ListView) findViewById(R.id.vr_listview);
    }
    @Override
    public void onClick(View v) {
        ToastUtil.show(context,"小心，我要发布VR视频啦~~~");
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
        return "VR";
    }

}
