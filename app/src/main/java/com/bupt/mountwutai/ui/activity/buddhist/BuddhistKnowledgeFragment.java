package com.bupt.mountwutai.ui.activity.buddhist;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mountwutai.entity.mian.SlidesEntity;
import com.bupt.mountwutai.ui.activity.main.HeaderPanel;
import com.bupt.mountwutai.ui.activity.main.SlidesPanel;
import com.bupt.mountwutai.widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛教知识
 */

public class BuddhistKnowledgeFragment extends BaseFragment {

    private NoScrollListView listView;
    private BuddhistKnowledgeAdapter adapter;
    private String[] contents = {"1.请香：在别的地方买东西可以称作买，但到了寺院买香请称作“请”；",
            "2.手心：磕拜的时候，手心得向上，那是对佛祖的尊敬。手心向下是拜祖先的；",
            "3.香火：去专用的点香处点燃香火，可别去插香的地方取火，也不要图方便用自己的打火机点火；",
            "4.左右：请用左手请香火，佛教认为左手是最干净的，然后，恭恭敬敬的用右手插在香炉中；",
            "5.许愿：通常你要记得告诉佛祖你叫什么，家住何处，有何事相求，事成后何处还原；大多数信士不管来到哪个殿前直接就磕头许愿，实不知佛菩萨有求必应，有求才有应。如果问们都不知道自己求的是哪位破菩萨，那么佛菩萨该怎么应呢？所以当自己跪拜时请先了解佛菩萨名号；",
            "6.法器：佛门中有很多法器，诸如木鱼，凳几，拜完佛祖后，可以稍稍抚摸，以求点佛气。不多千万注意，别摸在我佛贵体上哦。"};

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_buddhist_knowledge);
        listView = (NoScrollListView) findViewById(R.id.buddhist_knowledge_listview);
        adapter = new BuddhistKnowledgeAdapter();
        listView.setAdapter(adapter);
        List<SlidesEntity> datas = new ArrayList<>();
        datas.add(new SlidesEntity("","hehe1"));
        datas.add(new SlidesEntity("","hehe2"));
        datas.add(new SlidesEntity("","hehe3"));
        datas.add(new SlidesEntity("","hehe4"));
        SlidesPanel slidesPanel = new SlidesPanel(activity);
        slidesPanel.setData(datas);
        slidesPanel.startLoop(3000);
        HeaderPanel headerPanel = new HeaderPanel(activity);
        headerPanel.setData(datas);
        listView.addHeaderView(slidesPanel.getContentView());
        listView.addHeaderView(headerPanel.getContentView());
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

    public class BuddhistKnowledgeAdapter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return contents.length;
        }

        @Override
        public Object getItem(int position) {
            return contents[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(activity).inflate(R.layout.buddhist_item, null);
                holder.showContent = (TextView) convertView.findViewById(R.id.showcontent);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SpannableStringBuilder builder = new SpannableStringBuilder(contents[position]);
            ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.top_back));
            builder.setSpan(span, 2, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.showContent.setText(builder);
            return convertView;
        }

        public class ViewHolder {
            private TextView showContent;
        }
    }
}
