package com.bupt.mountwutai.ui.activity.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.adapter.HomeListAdapter;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.customdata.MainData;
import com.bupt.mountwutai.customdata.SummaryData;
import com.bupt.mountwutai.entity.CommonBean;
import com.bupt.mountwutai.entity.mian.CustomBean;
import com.bupt.mountwutai.entity.mian.SlidesEntity;
import com.bupt.mylibrary.utils.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by litf on 2017/5/10.
 */

public class HomeFragment extends BaseFragment {
    private Context context;
    private ListView listView;
    private List<String> nameList = new ArrayList<>();
    List<ArrayList<CommonBean>> homeLists = new ArrayList<>();
    ArrayList<CommonBean> commonBeanLists1 = new ArrayList<>();
    ArrayList<CommonBean> commonBeanLists2 = new ArrayList<>();
    ArrayList<CommonBean> commonBeanLists3 = new ArrayList<>();
    private HomeListAdapter homeListAdapter;
    HeaderPanel headerPanel;
    SlidesPanel slidesPanel;
    List<CustomBean> headers = new ArrayList<>();

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        Log.i("share","onCreateView");
        setContentView(R.layout.fragment_home);
        context = getActivity();
        listView = (ListView) findViewById(R.id.home_list);
        homeListAdapter = new HomeListAdapter(context, homeLists, nameList);
        listView.setAdapter(homeListAdapter);
        addData();
        List<SlidesEntity> datas = new ArrayList<>();
        datas.add(new SlidesEntity("", "title1"));
        datas.add(new SlidesEntity("", "title2"));
        datas.add(new SlidesEntity("", "title3"));
        datas.add(new SlidesEntity("", "title4"));
        slidesPanel = new SlidesPanel(activity);
        slidesPanel.setData(datas);
        slidesPanel.startLoop(3000);
        initheader();
        headerPanel = new HeaderPanel(activity);
        headerPanel.setData(headers);
        listView.addHeaderView(slidesPanel.getContentView());
        listView.addHeaderView(headerPanel.getContentView());
    }

    private void initheader() {
        if (ViewUtils.getData(activity,"0").equals("hehe")){
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.summary, true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.guide, true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, MainData.buddhist, true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "服务", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "土特产", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "景区直播", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "广电中心", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "政务公开", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "政民互动", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "森林防火", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "文物保护", true));
            headers.add(new CustomBean(R.mipmap.ic_launcher_round, "宗教事务", true));
            JSONArray array=new JSONArray();
            for (int i = 0; i <headers.size() ; i++) {
                JSONObject observeO= new JSONObject();
                try {
                    observeO.put("title",headers.get(i).getTitle());
                    observeO.put("isadd",headers.get(i).getIsadd());
                    observeO.put("picture",headers.get(i).getPicture());
                    ViewUtils.setData(activity,""+i,observeO.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                array.put(observeO);
            }
        }else{
            for (int i = 0; i <12 ; i++) {
                JSONObject oj= null;
                try {
                    oj = new JSONObject(ViewUtils.getData(activity,i+""));
                    headers.add(new CustomBean(oj.getInt("picture"),oj.getString("title"),oj.getBoolean("isadd")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    private void addData() {
        nameList.add("推荐");
        nameList.add("佛事");
        for (int i = 0; i < 3; i++) {
            commonBeanLists1.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i], SummaryData.temple_content[i]));
        }
        for (int i = 0; i < 3; i++) {
            commonBeanLists2.add(new CommonBean(SummaryData.temple_icon[i], SummaryData.temple_title[i], SummaryData.temple_content[i]));
        }
        homeLists.add(commonBeanLists1);
        homeLists.add(commonBeanLists2);
    }
    @Override
    public void onPause() {
        super.onPause();
        slidesPanel.stopLoop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!ViewUtils.getData(activity,"0").equals("hehe")){
            headers.clear();
            for (int i = 0; i <12 ; i++) {
                JSONObject oj= null;
                try {
                    oj = new JSONObject(ViewUtils.getData(activity,i+""));
                    headers.add(new CustomBean(oj.getInt("picture"),oj.getString("title"),oj.getBoolean("isadd")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            headerPanel.setData(headers);
        }
        slidesPanel.startLoop(3000);
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
        return "智慧五台山";
    }

}